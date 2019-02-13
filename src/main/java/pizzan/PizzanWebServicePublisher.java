package pizzan;

import javax.xml.ws.Endpoint;

public class PizzanWebServicePublisher {
    public static void main(String[] args) throws ClassNotFoundException {
        UserServiceImpl userServiceImpl = new UserServiceImpl();
        Endpoint.publish("http://localhost:8088/userService", userServiceImpl);
        OrderServiceImpl orderServiceImpl = new OrderServiceImpl(userServiceImpl);
        Endpoint.publish("http://localhost:8088/orderService", orderServiceImpl);

    }
}
