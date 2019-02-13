package pizzan;

public class Order {

    private int id;
    private int customerId;
    private int pizzaId;
    private boolean paid = false;

    public Order() {
    }

    public Order(int id, int customerId, int pizzaId, boolean paid) {
        this.id = id;
        this.customerId = customerId;
        this.pizzaId = pizzaId;
        this.paid = paid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getPizzaId() {
        return pizzaId;
    }

    public void setPizzaId(int pizzaId) {
        this.pizzaId = pizzaId;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }
}
