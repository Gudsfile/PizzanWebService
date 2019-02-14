package pizzan;

import javax.jws.WebService;

/**
 * Payment web service
 * This service is used to pay orders
 * As a customer you can get the total debt you have and pay your orders
 * Access point : http://localhost:8090/PaymentService
 */
@WebService(name = "PaymentService", targetNamespace = "http://localhost:8090")
public interface PaymentService {

    /**
     * Allows a user to get the total price he needs to pay
     * @param token user authentification token
     * @return the total price
     */
    double getTotalDebt(String token);

    /**
     * Allows a user to pay the total price
     * Set his orders to paid
     * @param token user authentification token
     * @return true if the user has paid
     */
    boolean payDebt(String token);
}
