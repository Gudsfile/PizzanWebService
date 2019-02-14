package pizzan;

/**
 * This class represent a pizza
 */
public class Pizza {

    /**
     * Pizza id
     */
    private int id;
    /**
     * This pizza's name
     */
    private String name;
    /**
     * This pizza's price
     */
    private double price;

    public Pizza() {
    }

    public Pizza(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
