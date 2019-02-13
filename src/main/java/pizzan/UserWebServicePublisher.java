package pizzan;

import javax.xml.ws.Endpoint;

public class UserWebServicePublisher {
    public static void main(String[] args) throws ClassNotFoundException {
        Endpoint.publish("http://localhost:8088/userWebService", new UserServiceImpl());
    }
}
