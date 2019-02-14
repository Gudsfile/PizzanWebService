package pizzan;

import javax.xml.ws.Endpoint;

/**
 * Pizzan web service publisher
 */
public class PizzanWebServicePublisher {
    public static void main(String[] args) throws ClassNotFoundException {
        UserServiceImpl userServiceImpl = new UserServiceImpl();
        Endpoint.publish("http://localhost:8080/userService", userServiceImpl);
        OrderServiceImpl orderServiceImpl = new OrderServiceImpl(userServiceImpl);
        Endpoint.publish("http://localhost:8080/orderService", orderServiceImpl);
        PaymentServiceImpl paymentServiceImpl = new PaymentServiceImpl(userServiceImpl, orderServiceImpl);
        Endpoint.publish("http://localhost:8080/paymentService", paymentServiceImpl);
    }
}
