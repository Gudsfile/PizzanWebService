package pizzan;

/**
 * This class represent an order
 */
public class Order {

    /**
     * Order id
     */
    private int id;
    /**
     * This order customer's id
     */
    private int customerId;
    /**
     * This order pizza's id
     */
    private int pizzaId;
    /**
     * True if the order is paid
     */
    private boolean paid = false;

    public Order() {
    }

    public Order(int id, int customerId, int pizzaId) {
        this.id = id;
        this.customerId = customerId;
        this.pizzaId = pizzaId;
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
