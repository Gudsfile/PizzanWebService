package pizzan;

import javax.jws.WebService;
import java.util.List;

@WebService(name = "UserService", targetNamespace = "http://localhost:8088")
public interface UserService {

    boolean addUser(User u);

    boolean addAdmin(String token, User u);

    List<User> getUsers(String token);

    boolean deleteUser(String token, int id);

    String login(String login, String pass);

    boolean logout(String token);

    User getUser(String token, int id);

}
