package trab_bolsa_de_valores;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.MessageProperties;
import java.util.Scanner;

public class OrderProducer {
    private static final String QUEUE_NAME = "BROKER"; // Nome da fila para receber operações dos clientes

    public static void main(String[] args) {
        String rabbitMqServerAddress = "gull.rmq.cloudamqp.com"; // Passe o endereço como argumento
        Scanner s = new Scanner(System.in);

        System.out.println("Deseja comprar ou vender");
        String acao = s.nextLine();

        System.out.println("Qual a acao");
        String codigo = s.nextLine();

        System.out.println("Qual a quantidade");
        String quantidade = s.nextLine();

        System.out.println("Qual o preco desta acao");
        String preco = s.nextLine();

        String orderMessage = "<"+acao+";"+codigo+";"+quantidade+";"+preco+">";

        try {
            RabbitMQConnection rabbitMQConnection = new RabbitMQConnection(rabbitMqServerAddress, 5672, "enzvwect", "3zMCaXyufH92EvroBPRjzj-qYzZrr8Re", "enzvwect");
            Connection connection = rabbitMQConnection.createConnection();

            // Crie um canal
            Channel channel = connection.createChannel();

            // Declare a fila (caso ainda não exista)
            channel.queueDeclare(QUEUE_NAME, true, false, false, null);

            // Envie a mensagem para a fila
            channel.basicPublish("", QUEUE_NAME, MessageProperties.PERSISTENT_TEXT_PLAIN, orderMessage.getBytes());

            System.out.println("Mensagem enviada: " + orderMessage);

            // Feche o canal e a conexão
            channel.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        s.close();
    }
}
