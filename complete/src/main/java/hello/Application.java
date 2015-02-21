package hello;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.neo4j.kernel.impl.util.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.config.Neo4jConfiguration;
import org.springframework.data.neo4j.core.GraphDatabase;

import java.io.File;

@Configuration
@EnableNeo4jRepositories(basePackages = "hello")
public class Application extends Neo4jConfiguration implements CommandLineRunner {

    public Application() {
        setBasePackage("hello");
    }

    @Bean
    GraphDatabaseService graphDatabaseService() {
        return new GraphDatabaseFactory().newEmbeddedDatabase("accessingdataneo4j.db");
    }

    @Autowired
    PersonRepository personRepository;

    @Autowired
    GraphDatabase graphDatabase;

    public void run(String... args) throws Exception {
        Transaction tx = graphDatabase.beginTx();
        try {
            Person linus = new Person("Linus");
            linus.setDojo(new Dojo("Coding Dojo"));
            linus.setMailingList(new MailingList("Kernel Mailing List"));
            
            personRepository.save(linus);
            
            tx.success();
        } finally {
            tx.close();
        }

    }

    public static void main(String[] args) throws Exception {
        FileUtils.deleteRecursively(new File("accessingdataneo4j.db"));

        SpringApplication.run(Application.class, args);
    }

}
