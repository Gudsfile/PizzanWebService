package pizzan;

import javax.jws.WebService;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebService(endpointInterface = "pizzan.UserService", serviceName = "UserService", portName = "8088")
public class UserServiceImpl implements UserService {

    private List<User> users = new ArrayList<User>();
    static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    static SecureRandom rnd = new SecureRandom();
    private Map<String, User> tokenMap = new HashMap<>();

    public UserServiceImpl() {
        users.add(new User(1, "admin1", "admin", true));
    }

    @Override
    public boolean addUser(User u) {
        System.out.println("addUser method has been invoked:");
        if (u.getName().equals("") || u.getPass().equals("")) {
            System.out.println("user null");
            return false;
        } else {
            u.setId(users.size()+1);
            u.setAdmin(false);
            return users.add(u);
        }
    }

    @Override
    public boolean addAdmin(String token, User u) {
        System.out.println("addAdmin method has been invoked:");
        if (tokenMap.get(token) != null && tokenMap.get(token).isAdmin()) {
            if (u.getName().equals("") || u.getPass().equals("")) {
                System.out.println("user null");
                return false;
            } else {
                u.setId(users.size()+1);
                u.setAdmin(true);
                return users.add(u);
            }
        } else {
            System.out.println("Token error or Admin rights needed");
            return false;
        }
    }

    @Override
    public List<User> getUsers(String token) {
        System.out.println("getUsers method has been invoked:");
        if (tokenMap.get(token) != null && tokenMap.get(token).isAdmin()) {
            System.out.println("getUsers method has been invoked.");
            return users;
        } else {
            System.out.println("Token error or Admin rights needed");
            return null;
        }
    }

    @Override
    public boolean deleteUser(String token, int id) {
        System.out.println("deleteUser method has been invoked:");
        if (tokenMap.get(token) != null && tokenMap.get(token).isAdmin()) {
            User u = getUser(token, id);
            if (u != null) {
                users.remove(u);
                return true;
            } else {
                return false;
            }
        } else {
            System.out.println("Token error or Admin rights needed");
            return false;
        }
    }

    @Override
    public User getUser(String token, int id) {
        System.out.println("getUser method has been invoked:");
        if (tokenMap.get(token) != null && tokenMap.get(token).isAdmin()) {
            for (User u : users) {
                if (u.getId() == id) {
                    return u;
                }
            }
            return null;
        } else {
            System.out.println("Token error or Admin rights needed");
            return null;
        }
    }
    @Override
    public String login(String login, String pass) {
        System.out.println("login method has been invoked:");
        for (User u : users) {
            if (u.getName().equals(login) && u.getPass().equals(pass)) {
                String token = tokenBuilder(64);
                u.setAuthentificationToken(token);
                tokenMap.put(token, u);
                return token;
            }
        }
        return null;
    }

    @Override
    public boolean logout(String userToken) {
        System.out.println("logout method has been invoked:");
        if (!tokenMap.isEmpty()) {
            tokenMap.remove(userToken);
            return true;
        } else {
            System.out.println("No user logged in");
            return false;
        }
    }

    String tokenBuilder( int len ){
        StringBuilder sb = new StringBuilder( len );
        for( int i = 0; i < len; i++ )
            sb.append( AB.charAt( rnd.nextInt(AB.length()) ) );
        return sb.toString();
    }

    public Map<String, User> getTokenMap() {
        return tokenMap;
    }
}
