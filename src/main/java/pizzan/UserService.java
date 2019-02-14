package pizzan;

import javax.jws.WebService;
import java.util.List;

/**
 * User web service
 * This service is used to add customer users or admin user to the app
 * Users can login using their name and password
 * Loging in return a unique string token that is needed to execute most of the api requests
 * Admin can add new admins, delete users and get the list of all users
 * Access point : http://localhost:8080/UserService
 */
@WebService(name = "UserService", targetNamespace = "http://localhost:8080")
public interface UserService {

    /**
     * Add a customer
     * @param name customer's name
     * @param pass customer's password
     * @return true if the customer has been added
     */
    boolean addUser(String name, String pass);

    /**
     * Allows an admin to add another admin
     * @param token admin authentification token
     * @param name admin's name
     * @param pass admin's password
     * @return
     */
    boolean addAdmin(String token, String name, String pass);

    /**
     * Allows an admin to get the list of users
     * @param token admin authentification token
     * @return the list of users
     */
    List<User> getUsers(String token);

    /**
     * Allows an admin to delete a user using this user's id
     * @param token admin authentification token
     * @param id user's id to be deleted
     * @return true if the user has been deleted
     */
    boolean deleteUser(String token, int id);

    /**
     * Login a user
     * Return a unique authentification token that is to be used for every request on the api
     * @param login user login
     * @param pass user password
     * @return a unique 64 char random string;
     */
    String login(String login, String pass);

    /**
     * Logout a user
     * Remove his token from the list of current users token
     * @param token user token
     * @return true if the user has been loged out
     */
    boolean logout(String token);

    /**
     * Allows an admin to get a user information
     * @param token admin authentification token
     * @param id user id
     * @return a user
     */
    User getUser(String token, int id);

}
