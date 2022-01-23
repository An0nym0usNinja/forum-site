package Tools;

public class XMLDoc {
    //VARIABLES
    private String xmlString;

    //CONSTRUCTORS
    public XMLDoc(String xmlString) {
        this.xmlString = xmlString;
    }
    
    //METHODS
    public String getAttribute(String var) {
        try {
            String answer = this.xmlString.split("<"+var+">")[1].split("</"+var+">")[0];
            return answer;
        } catch (Exception e) {
            return "";
        }
    }
    public String[] getAllXMLUsers() {
        String users_string = new XMLDoc(this.xmlString).getAttribute("users");
        String[] split = users_string.split("<user>");
        String[] users = new String[split.length-1];
        for (int i = 1; i<split.length; i++){
            users[(i-1)] = split[i];
        }
        return users;
    }
    public String[] getAllXMLTopics() {
        String topics_string = new XMLDoc(this.xmlString).getAttribute("topics");
        String[] split = topics_string.split("<topic>");
        String[] topics = new String[split.length-1];
        for (int i = 1; i<split.length; i++){
            topics[(i-1)] = split[i];
        }
        return topics;
    }
    
}
