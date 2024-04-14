package trab_bolsa_de_valores;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class RabbitMQConnection {
    private String host;
    private int port;
    private String username;
    private String password;
    private String vhost;

    public RabbitMQConnection(String host, int port, String username, String password, String vhost) {
        this.host = host;
        this.port = port;
        this.username = username;
        this.password = password;
        this.vhost = vhost;
    }

    public Connection createConnection() throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(host);
        factory.setPort(port);
        factory.setUsername(username);
        factory.setPassword(password);
        factory.setVirtualHost(vhost);

        return factory.newConnection();
    }
}