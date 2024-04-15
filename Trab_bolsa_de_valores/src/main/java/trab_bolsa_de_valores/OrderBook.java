package trab_bolsa_de_valores;

import java.util.ArrayList;
import java.util.List;

public class OrderBook {
    private List<Order> buyOrders; // Ordens de compra
    private List<Order> sellOrders; // Ordens de venda

    public OrderBook() {
        this.buyOrders = new ArrayList<>();
        this.sellOrders = new ArrayList<>();
    }

    public void addBuyOrder(Order order) {
        buyOrders.add(order);
        matchOrders();
    }

    public void addSellOrder(Order order) {
        sellOrders.add(order);
        matchOrders();
    }

    private void matchOrders() {
        // Percorra as ordens de compra e venda
        for (Order buyOrder : buyOrders) {
            for (Order sellOrder : sellOrders) {
                if (buyOrder.getSymbol().equals(sellOrder.getSymbol())) {
                    // Verifique se o preço da ordem de venda é menor ou igual ao preço da ordem de compra
                    if (sellOrder.getPrice() <= buyOrder.getPrice()) {
                        // Casamento encontrado! Execute a operação
                        int quantityToMatch = Math.min(buyOrder.getQuantity(), sellOrder.getQuantity());

                        // Atualize as quantidades nas ordens
                        buyOrder.setQuantity(buyOrder.getQuantity() - quantityToMatch);
                        sellOrder.setQuantity(sellOrder.getQuantity() - quantityToMatch);

                        // Notifique os clientes interessados sobre a operação executada
                        System.out.println("Ordem executada: " + quantityToMatch + " ações de " + buyOrder.getSymbol() +
                                " por R$" + buyOrder.getPrice());

                        // Se alguma das ordens ficar com quantidade zero, remova-a do livro de ofertas
                        if (buyOrder.getQuantity() == 0) {
                            buyOrders.remove(buyOrder);
                        }
                        if (sellOrder.getQuantity() == 0) {
                            sellOrders.remove(sellOrder);
                        }
                    }
                }
            }
        }
    }

}