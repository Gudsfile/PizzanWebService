package pizzan;

import javax.jws.WebService;
import java.util.List;

@WebService(name = "OrderService", targetNamespace = "http://localhost:8088")
public interface OrderService {

    boolean addPizza(String token, Pizza pizza);

    boolean deletePizza(String token, String name);

    List<Pizza> getPizzas();

    Pizza getPizza(String name);

    boolean addOrder(String token, Order order);

    List<Order> getOrders(String token);

    Order getOrder(int id);

    List<Order> getUserOrders(String token, int id);
}
