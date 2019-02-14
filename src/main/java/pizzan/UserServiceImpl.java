package pizzan;

import javax.jws.WebService;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User service implementation
 */
@WebService(endpointInterface = "pizzan.UserService", serviceName = "userservice", portName = "8080")
public class UserServiceImpl implements UserService {

    /**
     * The list of users
     */
    private List<User> users = new ArrayList<User>();
    /**
     * Alphabet used to build random string token
     */
    static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    /**
     * Random number used to build random string token
     */
    static SecureRandom rnd = new SecureRandom();
    /**
     * Map with String token as Keys and Users as values
     */
    private Map<String, User> tokenMap = new HashMap<>();

    public UserServiceImpl() {
        users.add(new User(1, "admin", "admin", true));
    }

    @Override
    public boolean addUser(String name, String pass) {
        System.out.println("addUser method has been invoked:");
        if (name.equals("") || pass.equals("")) {
            System.out.println("user null");
            return false;
        } else {
            User u = new User(users.size()+1, name, pass, false);
            return users.add(u);
        }
    }

    @Override
    public boolean addAdmin(String token, String name, String pass) {
        System.out.println("addAdmin method has been invoked:");
        if (tokenMap.get(token) != null && tokenMap.get(token).isAdmin()) {
            if (name.equals("") || pass.equals("")) {
                System.out.println("user null");
                return false;
            } else {
                User u = new User(users.size()+1, name, pass, true);
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

    /**
     * Build a random char string
     * @param len lenghts of the string to build
     * @return a random string
     */
    String tokenBuilder( int len ){
        StringBuilder sb = new StringBuilder( len );
        for( int i = 0; i < len; i++ )
            sb.append( AB.charAt( rnd.nextInt(AB.length()) ) );
        return sb.toString();
    }

    /**
     * Get the user Map<String token, User>
     * @return the user Map<String token, User>
     */
    public Map<String, User> getTokenMap() {
        return tokenMap;
    }
}
