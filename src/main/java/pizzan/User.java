package pizzan;

/**
 * This class represent a user (either an admin or a customer)
 */
public class User {

    /**
     * User id
     */
    private int id;
    /**
     * This user's name
     */
    private String name;
    /**
     * This user's password
     */
    private String pass;
    /**
     * True if the user is an admin
     */
    private boolean admin;
    /**
     * This user's current unique authentification token
     */
    private String authentificationToken;

    public User() {

    }

    public User(int id, String name, String pass, boolean admin) {
        this.id = id;
        this.name = name;
        this.pass = pass;
        this.admin = admin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public String getAuthentificationToken() {
        return authentificationToken;
    }

    public void setAuthentificationToken(String authentificationToken) {
        this.authentificationToken = authentificationToken;
    }
}
