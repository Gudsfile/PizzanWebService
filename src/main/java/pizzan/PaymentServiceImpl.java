package pizzan;

import javax.jws.WebService;

/**
 * Payment service implementation
 */
@WebService(endpointInterface = "pizzan.PaymentService", serviceName = "paymentservice", portName = "8080")
public class PaymentServiceImpl implements PaymentService {

    /**
     * UserServiceImpl used to get the Map<String token, User>
     */
    private UserServiceImpl userServiceImpl;
    /**
     * OrderServiceImpl used to get user orders
     */
    private OrderServiceImpl orderServiceImpl;

    public PaymentServiceImpl(){
    }

    public PaymentServiceImpl(UserServiceImpl userServiceImpl, OrderServiceImpl orderServiceImpl) {
        this.userServiceImpl = userServiceImpl;
        this.orderServiceImpl = orderServiceImpl;
    }

    @Override
    public double getTotalDebt(String token) {
        double total = 0;
        if (userServiceImpl.getTokenMap().get(token) != null) {
            for (Order o : orderServiceImpl.getUnpaidOrders(token)) {
                total += orderServiceImpl.getPizza(token, o.getPizzaId()).getPrice();
            }
        } else {
            System.out.println("Token error or Admin rights needed");
        }
        return total;
    }

    @Override
    public boolean payDebt(String token) {
        if (userServiceImpl.getTokenMap().get(token) != null) {
            for (Order o : orderServiceImpl.getUnpaidOrders(token)) {
                o.setPaid(true);
            }
            return true;
        } else {
            System.out.println("Token error or Admin rights needed");
        }
        return false;
    }
}
