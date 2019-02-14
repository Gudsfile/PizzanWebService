package pizzan;

import javax.jws.WebService;
import java.util.ArrayList;
import java.util.List;

/**
 * Order service implementation
 */
@WebService(endpointInterface = "pizzan.OrderService", serviceName = "OrderService", portName = "8080")
public class OrderServiceImpl implements OrderService {

    /**
     * List of pizzas
     */
    private List<Pizza> pizzas = new ArrayList<>();
    /**
     * List of orders
     */
    private List<Order> orders = new ArrayList<>();
    /**
     * UserServiceImpl used to get the Map<String token, User>
     */
    private UserServiceImpl userServiceImpl;

    public OrderServiceImpl() {
    }

    public OrderServiceImpl(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
        pizzas.add(new Pizza(1, "reine", 7.89));
    }

    @Override
    public boolean addPizza(String token, String name, double price) {
        System.out.println("addPizza method has been invoked:");
        if (userServiceImpl.getTokenMap().get(token) != null && userServiceImpl.getTokenMap().get(token).isAdmin()) {
            Pizza p = new Pizza(pizzas.size()+1, name, price);
            return pizzas.add(p);
        } else {
            System.out.println("Token error or Admin rights needed");
            return false;
        }
    }

    @Override
    public boolean deletePizza(String token, int id) {
        System.out.println("deletePizza method has been invoked: "+id);
        if (userServiceImpl.getTokenMap().get(token) != null && userServiceImpl.getTokenMap().get(token).isAdmin()) {
            for (Pizza p : pizzas) {
                if (p.getId() == id) {
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
    public Pizza getPizza(String token, int id) {
        System.out.println("getPizza method has been invoked: "+id);
        if (userServiceImpl.getTokenMap().get(token) != null) {
            for (Pizza pizza : pizzas) {
                if (pizza.getId() == id) return pizza;
            }
        } else {
            System.out.println("Token error");
        }
        return null;
    }

    @Override
    public List<Pizza> getPizzas(String token) {
        System.out.println("getPizzas method has been invoked:");
        if (userServiceImpl.getTokenMap().get(token) != null) {
            return pizzas;
        } else {
            System.out.println("Token error");
        }
        return null;
    }

    @Override
    public boolean addOrder(String token, int pizzaId) {
        System.out.println("addOrder method has been invoked: "+pizzaId);
        if (userServiceImpl.getTokenMap().get(token) != null && !userServiceImpl.getTokenMap().get(token).isAdmin()) {
            boolean found = false;
            int i = 0;
            while(!found && i<pizzas.size()) {
                if (pizzas.get(i).getId() == pizzaId) {
                    User user = userServiceImpl.getTokenMap().get(token);
                    Order order = new Order(orders.size()+1, user.getId(), pizzaId);
                    orders.add(order);
                    found = true;
                    return true;
                }
                i++;
            }
        } else {
            System.out.println("Token error");
        }
        return false;
    }

    @Override
    public List<Order> getUnpaidOrders(String token) {
        System.out.println("getUnpaidOrders method has been invoked:");
        if (userServiceImpl.getTokenMap().get(token) != null) {
            User user = userServiceImpl.getTokenMap().get(token);
            List<Order> ret = new ArrayList<>();
            for (Order o : orders) {
                if (o.getCustomerId() == user.getId() && !o.isPaid()) {
                    ret.add(o);
                }
            }
            return ret;
        } else {
            System.out.println("Token error");
        }
        return null;
    }


    @Override
    public List<Order> getUserOrders(String token) {
        System.out.println("getUserOrders method has been invoked:");
        if (userServiceImpl.getTokenMap().get(token) != null) {
            User user = userServiceImpl.getTokenMap().get(token);
            List<Order> ret = new ArrayList<>();
            for (Order o : orders) {
                if (o.getCustomerId() == user.getId()) {
                    ret.add(o);
                }
            }
            return ret;
        } else {
            System.out.println("Token error");
        }
        return null;
    }

    @Override
    public List<Order> getOrders(String token) {
        System.out.println("getOrders method has been invoked:");
        if (userServiceImpl.getTokenMap().get(token) != null && userServiceImpl.getTokenMap().get(token).isAdmin()) {
            return orders;
        } else {
            System.out.println("Token error or Admin rights needed");
        }
        return null;
    }

    @Override
    public Order getOrder(String token, int id) {
        System.out.println("getOrder method has been invoked: "+id);
        if (userServiceImpl.getTokenMap().get(token) != null && userServiceImpl.getTokenMap().get(token).isAdmin()) {
            for (Order o : orders) {
                if (o.getId() == id) return o;
            }
        } else {
            System.out.println("Token error or Admin rights needed");
        }
        return null;
    }
}
