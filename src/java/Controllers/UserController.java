package Controllers;

import Env.Environment;
import Models.User;
import Tools.XMLDoc;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

public class UserController {
    //VARIABLES
    private final String URL;
    private final Client client;
    private WebTarget target;
    
    //CONSTRUCTORS
    public UserController() {
        this.URL = new Environment().SERVICE_URL;
        this.client = ClientBuilder.newClient();
    }
    
    //CONTROLLER METHODS
    public User getUser(String id){
        this.target = client.target(this.URL+"user/"+id);
        String xml_result = target.request(MediaType.APPLICATION_XML).get(String.class);
        if (xml_result.equals("")){
            return null;
        }
        String user_id = new XMLDoc(xml_result).getAttribute("id");
        String user_name = new XMLDoc(xml_result).getAttribute("username");
        String user_email = new XMLDoc(xml_result).getAttribute("email");
        String user_password = new XMLDoc(xml_result).getAttribute("password");
        return new User(user_id, user_name, user_email, user_password);
    }
    public User[] getUsers(){
        this.target = client.target(this.URL+"user");
        String xml_result = target.request(MediaType.APPLICATION_XML).get(String.class);
        if (xml_result.equals("")){
            return null;
        }
        
        String[] usersXML = new XMLDoc(xml_result).getAllXMLUsers();
        User[] users = new User[usersXML.length];
        for (int i=0; i<users.length; i++){
            String user_id = new XMLDoc(usersXML[i]).getAttribute("id");
            String user_name = new XMLDoc(usersXML[i]).getAttribute("username");
            String user_email = new XMLDoc(usersXML[i]).getAttribute("email");
            String user_password = new XMLDoc(usersXML[i]).getAttribute("password");
            users[i] = new User(user_id, user_name, user_email, user_password);
        }
        
        return users;
    }
    public void createUser(String username, String email, String password){
        this.target = client.target(this.URL+"user");
        target.request(MediaType.APPLICATION_XML).post(Entity.xml(""
                + "<user>"
                    + "<username>" + username + "</username>"
                    + "<email>" + email + "</email>"
                    + "<password>" + password + "</password>"
                + "</user>"));
    }
    public void deleteUser(User user){
        this.target = client.target(this.URL+"user/"+user.getId());
        target.request(MediaType.APPLICATION_XML).delete(String.class);
    }
    public void updateUser(User user){
        this.target = client.target(this.URL+"user/"+user.getId());
        target.request(MediaType.APPLICATION_XML).put(Entity.xml(""
                + "<user>"
                    + "<id>" + user.getId()+ "</id>"
                    + "<username>" + user.getUsername() + "</username>"
                    + "<email>" + user.getEmail() + "</email>"
                    + "<password>" + user.getPassword() + "</password>"
                + "</user>"));
    }
    
    //HELPER METHODS
    public boolean emailExists(String email){
        User[] users = this.getUsers();
        for (User user : users){
            if (user.getEmail().equals(email)){
                return true;
            }
        }
        return false;
    }
    public boolean usernameExists(String username){
        User[] users = this.getUsers();
        for (User user : users){
            if (user.getUsername().equals(username)){
                return true;
            }
        }
        return false;
    }
        
}
