package hello;

import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.NodeEntity;

@NodeEntity
public class MailingList {

    @GraphId Long id;
    public String name;
    
    public MailingList() { }
    public MailingList(String name) { this.name = name; }
    
    @Override
    public String toString() {
        return "MailingList [name=" + name + "]";
    }

}
