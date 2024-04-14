package trab_bolsa_de_valores;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.*;

public class OrderConsumer {
    private static final String EXCHANGE_NAME = "BOLSADEVALORES"; // Nome da exchange para pub/sub

    public static void main(String[] args) {
        String rabbitMqServerAddress = "localhost"; // Passe o endereço como argumento

        try {
            RabbitMQConnection rabbitMQConnection = new RabbitMQConnection(rabbitMqServerAddress, 5672, "guest", "guest");
            Connection connection = rabbitMQConnection.createConnection();

            // Crie um canal
            Channel channel = connection.createChannel();

            // Declare a exchange (exchange de tópicos)
            channel.exchangeDeclare(EXCHANGE_NAME, "fanout");

            // Crie uma fila temporária (sem nome) exclusiva para este consumidor
            String queueName = channel.queueDeclare().getQueue();

            // Associe a fila à exchange
            channel.queueBind(queueName, EXCHANGE_NAME, "");

            System.out.println("Aguardando notificações... Para sair: CTRL + C");

            // Crie um consumidor
            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                String message = new String(delivery.getBody(), "UTF-8");
                System.out.println("Notificação recebida: " + message);

                // Aqui nós deserializamos a mensagem JSON para uma instância de Order
            ObjectMapper mapper = new ObjectMapper();
            Order order = mapper.readValue(message, Order.class);
            OrderBook orderBook = new OrderBook();

            // Processar a ordem baseado no seu tipo
            if (order.getOper().equals("COMPRA")) {
                // lógica para processar uma ordem de compra
                orderBook.addBuyOrder(order);;
            } else if (order.getOper().equals("VENDA")) {
                // lógica para processar uma ordem de venda
                orderBook.addSellOrder(order);
            }
            };

            // Registre o consumidor na fila
            channel.basicConsume(queueName, true, deliverCallback, consumerTag -> {});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
