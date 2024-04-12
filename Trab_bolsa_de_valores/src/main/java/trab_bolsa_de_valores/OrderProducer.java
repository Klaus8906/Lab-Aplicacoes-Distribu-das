package trab_bolsa_de_valores;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.MessageProperties;

public class OrderProducer {
    private static final String QUEUE_NAME = "BROKER"; // Nome da fila para receber operações dos clientes

    public static void main(String[] args) {
        String rabbitMqServerAddress = "seu-endereco-do-rabbitmq-na-nuvem"; // Passe o endereço como argumento

        try {
            RabbitMQConnection rabbitMQConnection = new RabbitMQConnection(rabbitMqServerAddress, 5672, "admin", "admin");
            Connection connection = rabbitMQConnection.createConnection();

            // Crie um canal
            Channel channel = connection.createChannel();

            // Declare a fila (caso ainda não exista)
            channel.queueDeclare(QUEUE_NAME, true, false, false, null);

            // Exemplo de mensagem (ordem de compra)
            String orderMessage = "COMPRA: Ação PETR4, Quantidade: 100, Preço: R$ 30.50";

            // Envie a mensagem para a fila
            channel.basicPublish("", QUEUE_NAME, MessageProperties.PERSISTENT_TEXT_PLAIN, orderMessage.getBytes());

            System.out.println("Mensagem enviada: " + orderMessage);

            // Feche o canal e a conexão
            channel.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
