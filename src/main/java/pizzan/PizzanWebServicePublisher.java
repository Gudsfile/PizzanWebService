package pizzan;

import javax.xml.ws.Endpoint;

/**
 * Pizzan web service publisher
 */
public class PizzanWebServicePublisher {
    public static void main(String[] args) throws ClassNotFoundException {
        UserServiceImpl userServiceImpl = new UserServiceImpl();
        Endpoint.publish("http://localhost:8080/userservice", userServiceImpl);
        OrderServiceImpl orderServiceImpl = new OrderServiceImpl(userServiceImpl);
        Endpoint.publish("http://localhost:8080/orderservice", orderServiceImpl);
        PaymentServiceImpl paymentServiceImpl = new PaymentServiceImpl(userServiceImpl, orderServiceImpl);
        Endpoint.publish("http://localhost:8080/paymentservice", paymentServiceImpl);
    }
}
