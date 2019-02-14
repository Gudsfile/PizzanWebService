package pizzan;

import javax.jws.WebService;
import java.util.List;

/**
 * Order web service
 * This service is used to add orders and pizzas
 * As an admin you can add new pizza and delete them, get all customer orders
 * As a customer you can order a pizza, get your order list
 * Access point : http://localhost:8080/orderservice
 */
@WebService(name = "orderservice", targetNamespace = "http://localhost:8080")
public interface OrderService {

    /**
     * Allows an admin to add a pizza to the list of available pizzas
     * @param token admin authentification token
     * @param name name of the new pizza
     * @param price price of the new pizza
     * @return true if the new pizza has been added
     */
    boolean addPizza(String token, String name, double price);

    /**
     * Allows an admin to delete a pizza from the list of available pizzas
     * @param token admin authentification token
     * @param id id of the pizza to delete
     * @return true if the pizza has been deleted
     */
    boolean deletePizza(String token, int id);

    /**
     * Get the list of available pizzas
     * @param token user authentification token
     * @return the list of pizzas
     */
    List<Pizza> getPizzas(String token);

    /**
     * Get a pizza information
     * @param id the pizza's id
     * @return a pizza
     */
    Pizza getPizza(String token, int id);

    /**
     * Allows a customer to order a pizza
     * @param token customer authentification token
     * @param pizzaId the pizza's to order id
     * @return true if the pizza has been ordered
     */
    boolean addOrder(String token, int pizzaId);

    /**
     * Allows a customer to get current orders (i.e. orders which are not paid yet)
     * @param token user authentification token
     * @return current orders
     */
    List<Order> getUnpaidOrders(String token);

    /**
     * Allows a customer to get all his orders
     * @param token user authentification token
     * @return user orders
     */
    List<Order> getUserOrders(String token);

    /**
     * Allows an admin to get the list of all orders
     * @param token admin authentification token
     * @return the list of all orders
     */
    List<Order> getOrders(String token);

    /**
     * Allows an admin to get an order informations
     * @param token admin authentification token
     * @param id order id
     * @return an order
     */
    Order getOrder(String token, int id);

}
