package Testing;

import Controllers.TopicController;
import Controllers.UserController;
import Models.Topic;
import Models.User;
import Tools.XMLDoc;

public class Main {
    public static void main(String[] args) {
        
        Topic topic = new TopicController().getTopic("1");
        User user = topic.getUser();
        System.out.println(user);
        
    }
}
