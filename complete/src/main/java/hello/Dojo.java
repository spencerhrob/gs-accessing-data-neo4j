package hello;

import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.NodeEntity;

@NodeEntity
public class Dojo {

    @GraphId Long id;
    public String name;
    
    public Dojo() { }
    public Dojo(String name) { this.name = name; }
    
    @Override
    public String toString() {
        return "Dojo [name=" + name + "]";
    }
}
