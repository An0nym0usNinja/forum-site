package Models;

public class User {
    //VARIABLES
    private String id;
    private String username;
    private String email;
    private String password;

    //CONSTRUCTORS
    public User() {
    }
    public User(String id, String username, String email, String password) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
    }
    
    //ACCESSORS
    public String getId() {
        return id;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    //METHODS
    @Override
    public String toString() {
        return "["
                + "id => "+this.id+", "
                + "username => "+this.username+", "
                + "email => "+this.email+""
                + "]";
    }
        
}
