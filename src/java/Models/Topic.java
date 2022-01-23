package Models;

import Controllers.UserController;

public class Topic {
    //VARIABLES
    private String id;
    private String topic;
    private String user_fk;

    //CONSTRUCTORS
    public Topic() {
    }
    public Topic(String id, String topic, String user_fk) {
        this.id = id;
        this.topic = topic;
        this.user_fk = user_fk;
    }

    //ACCESSORS
    public String getId() {
        return id;
    }
    public String getTopic() {
        return topic;
    }
    public String getUser_fk() {
        return user_fk;
    }
    
    //RELATIONSHIPS
    public User getUser(){
        return new UserController().getUser(this.user_fk);
    }
    
    //METHODS
    @Override
    public String toString() {
        return "["
                + "id => "+this.id+", "
                + "topic => "+this.topic+", "
                + "user_fk => "+this.user_fk+""
                + "]";
    }
    
}
