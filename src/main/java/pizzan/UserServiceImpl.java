package pizzan;

import javax.jws.WebService;
import java.util.ArrayList;
import java.util.List;

@WebService(endpointInterface = "pizzan.UserService", serviceName = "UserService", portName = "8088")
public class UserServiceImpl implements UserService {

    private List<User> users = new ArrayList<User>();
    private boolean adminToken = false;
    private boolean userToken = false;

    public UserServiceImpl() {
        users.add(new User(1, "admin", "admin", true));
    }

    public boolean addUser(User u) {
        if (adminToken) {
            System.out.println("addUser method has been invoked:" + u);
            if (u.getName().equals("") || u.getPass().equals("")) {
                System.out.println("user null");
                return false;
            } else {
                u.setId(users.size()+1);
                return users.add(u);
            }
        } else {
            System.out.println("You need admin rights for this");
            return false;
        }

    }

    public List<User> getUsers() {
        System.out.println("getUsers method has been invoked.");
        return users;    }

    public boolean deleteUser(int id) {
        if (adminToken) {
            User u = getUser(id);
            if (u != null) {
                users.remove(u);
                return true;
            } else {
                return false;
            }
        } else {
            System.out.println("You need admin rights for this");
            return false;
        }
    }

    public boolean login(String login, String pass) {
        for (User u : users) {
            if (u.getName().equals(login) && u.getPass().equals(pass)) {
                if (u.isAdmin()) {
                    adminToken = true;
                    userToken = false;
                } else {
                    adminToken = false;
                    userToken = true;
                }
                return true;
            }
        }
        return false;
    }

    public boolean logout() {
        if (adminToken) {
            adminToken = false;
            return true;
        }
        if (userToken) {
            userToken = false;
            return true;
        }
        return false;
    }

    public User getUser(int id) {
        if (adminToken) {
            for (User u : users) {
                if (u.getId() == id) {
                    return u;
                }
            }
            return null;
        } else {
            System.out.println("You need admin rights for this");
            return null;
        }
    }
}
