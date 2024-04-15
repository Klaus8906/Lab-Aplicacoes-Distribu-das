package trab_bolsa_de_valores;

public class Order {
    private String oper;
    private String symbol; // Símbolo da ação (ex: PETR4)
    private int quantity; // Quantidade de ações
    private double price; // Preço da ação
    private String corretora;

    public Order(String symbol, int quantity, double price, String oper) {
        this.symbol = symbol;
        this.quantity = quantity;
        this.price = price;
        this.oper = oper;
        this.corretora = "BKR1";
    }

    // Getters e setters
    public String getOper() {
        return this.oper;
    }

    public void setOper(String oper) {
        this.oper = oper;
    }

    public String getCorretora() {
        return this.corretora;
    }

    public void setCorretora(String corretora) {
        this.corretora = corretora;
    }

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
