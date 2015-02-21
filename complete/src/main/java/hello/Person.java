package hello;

import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;

@NodeEntity
public class Person {

    @GraphId Long id;
    public String name;

    public Person() {}
    public Person(String name) { this.name = name; }

    @RelatedTo(type="MEMBER_OF", direction=Direction.OUTGOING)
    Dojo dojo;
    
    @RelatedTo(type="MEMBER_OF", direction=Direction.OUTGOING)
    MailingList mailingList;

    public void setDojo(Dojo dojo) {
        this.dojo = dojo;
    }
    public void setMailingList(MailingList mailingList) {
        this.mailingList = mailingList;
    }

    @Override
    public String toString() {
        return "Person [name=" + name + "]";
    }
}
