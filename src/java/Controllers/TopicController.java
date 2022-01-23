package Controllers;

import Env.Environment;
import Models.Topic;
import Models.User;
import Tools.XMLDoc;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

public class TopicController {
    //VARIABLES
    private final String URL;
    private final Client client;
    private WebTarget target;

    //CONSTRUCTORS
    public TopicController() {
        this.URL = new Environment().SERVICE_URL;
        this.client = ClientBuilder.newClient();
    }
    
    //CONTROLLER METHODS
    public Topic getTopic(String id){
        this.target = client.target(this.URL+"topic/"+id);
        String xml_result = target.request(MediaType.APPLICATION_XML).get(String.class);
        if (xml_result.equals("")){
            return null;
        }
        String topic_id = new XMLDoc(xml_result).getAttribute("id");
        String topic_topic = new XMLDoc(xml_result).getAttribute("topicText");
        String topic_user_fk = new XMLDoc(new XMLDoc(xml_result).getAttribute("userFk")).getAttribute("id");
        return new Topic(topic_id, topic_topic, topic_user_fk);
    }
    public Topic[] getTopics(){
        this.target = client.target(this.URL+"topic");
        String xml_result = target.request(MediaType.APPLICATION_XML).get(String.class);
        if (xml_result.equals("")){
            return null;
        }
        String[] topicsXML = new XMLDoc(xml_result).getAllXMLTopics();
        Topic[] topics = new Topic[topicsXML.length];
        for (int i=0; i<topics.length; i++){
            String topic_id = new XMLDoc(topicsXML[i]).getAttribute("id");
            String topic_topic = new XMLDoc(topicsXML[i]).getAttribute("topicText");
            String topic_user_fk = new XMLDoc(new XMLDoc(topicsXML[i]).getAttribute("userFk")).getAttribute("id");
            topics[i] = new Topic(topic_id, topic_topic, topic_user_fk);
        }
        
        return topics;
    }
    public void createTopic(String topicText, String user_fk){
        this.target = client.target(this.URL+"topic");
        target.request(MediaType.APPLICATION_XML).post(Entity.xml(""
                + "<topic>"
                    + "<topicText>" + topicText + "</topicText>"
                    + "<userFk>"
                        + "<id>" + user_fk + "</id>"
                    + "</userFk>"
                + "</topic>"));
    }
    public void deleteTopic(Topic topic){
        this.target = client.target(this.URL+"topic/"+topic.getId());
        target.request(MediaType.APPLICATION_XML).delete(String.class);
    }
    public void updateTopic(Topic topic, String newTopicText){
        this.target = client.target(this.URL+"topic/"+topic.getId());
        target.request(MediaType.APPLICATION_XML).put(Entity.xml(""
                + "<topic>"
                    + "<id>" + topic.getId() + "</id>"
                    + "<topicText>" + newTopicText + "</topicText>"
                    + "<userFk>"
                        + "<id>" + topic.getUser_fk()+ "</id>"
                    + "</userFk>"
                + "</topic>"));
    }
    
}
