package pizzan;

import javax.jws.WebService;
import java.util.List;

@WebService(name = "UserService", targetNamespace = "http://localhost:8088")
public interface UserService {

    boolean addUser(User u);

    List<User> getUsers();

    boolean deleteUser(int id);

    boolean login(String login, String pass);

    boolean logout();

    User getUser(int id);

}
