package pizzan;

import javax.jws.WebService;
import java.util.ArrayList;
import java.util.List;

@WebService(endpointInterface = "pizzan.OrderService", serviceName = "OrderService", portName = "8088")
public class OrderServiceImpl implements OrderService {

    private List<Pizza> pizzas = new ArrayList<>();
    private List<Order> orders = new ArrayList<>();
    private UserServiceImpl userServiceImpl;

    public OrderServiceImpl() {
    }

    public OrderServiceImpl(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
        pizzas.add(new Pizza("reine", "", 7.89));
    }

    @Override
    public boolean addPizza(String token, Pizza pizza) {
        System.out.println("addPizza method has been invoked:");
        if (userServiceImpl.getTokenMap().get(token) != null && userServiceImpl.getTokenMap().get(token).isAdmin()) {
            return pizzas.add(pizza);
        } else {
            System.out.println("Token error or Admin rights needed");
            return false;
        }
    }

    @Override
    public boolean deletePizza(String token, String name) {
        System.out.println("deletePizza method has been invoked:");
        if (userServiceImpl.getTokenMap().get(token) != null && userServiceImpl.getTokenMap().get(token).isAdmin()) {
            for (Pizza p : pizzas) {
                if (p.getName().equals(name)) {
                    pizzas.remove(p);
                    return true;
                }
            }
        } else {
            System.out.println("Token error or Admin rights needed");
        }
        return false;
    }

    @Override
    public Pizza getPizza(String name) {
        for (Pizza pizza : pizzas) {
            if (pizza.getName().equals(name)) {
                return pizza;
            }
        }
        return null;
    }

    @Override
    public List<Pizza> getPizzas() {
        System.out.println("getPizzas method has been invoked:");
        return pizzas;
    }

    @Override
    public boolean addOrder(String token, Order order) {
        return false;
    }

    @Override
    public List<Order> getOrders(String token) {
        return null;
    }

    @Override
    public Order getOrder(int id) {
        return null;
    }

    @Override
    public List<Order> getUserOrders(String token, int id) {
        return null;
    }
}
