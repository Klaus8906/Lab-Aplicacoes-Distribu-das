package trab_bolsa_de_valores;

public class Order {
    private String symbol; // Símbolo da ação (ex: PETR4)
    private int quantity; // Quantidade de ações
    private double price; // Preço da ação

    public Order(String symbol, int quantity, double price) {
        this.symbol = symbol;
        this.quantity = quantity;
        this.price = price;
    }

    // Getters e setters
    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
