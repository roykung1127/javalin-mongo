package jar;


import io.javalin.Javalin;

// related java
import jar.controller.UserController;
import jar.repository.impl.UserRepositoryImpl;

import com.mongodb.ConnectionString;
// about mongo connection
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

// about mongo pojo codec 
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

// others
import java.util.Arrays;


public class Mongo {

    private final MongoClient mongoClient;
    private UserController userController;

    public Mongo() {

        CodecRegistry pojoCodecRegistry = fromProviders(PojoCodecProvider.builder().automatic(true).build());
        CodecRegistry codecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(), pojoCodecRegistry);

        ConnectionString connectionString = new ConnectionString("mongodb+srv://ma_royk:zenkima1@cluster0.xnyt0.mongodb.net/roy_testing?retryWrites=true&w=majority");
        // ConnectionString connectionString = new ConnectionString("mongodb://ma_royk:zenkima1@cluster0.xnyt0.mongodb.net/roy_testing?retryWrites=true&w=majority");

        this.mongoClient = MongoClients.create(MongoClientSettings.builder()
        // .applyToClusterSettings(builder ->
        //         builder.hosts(Arrays.asList(new ServerAddress("localhost", 27017)))) // localhost
        .applyConnectionString(connectionString) // mongo atlas
        .codecRegistry(codecRegistry)
        .build());


        
        this.userController = new UserController(new UserRepositoryImpl(mongoClient));

    }

    public static void main(String[] args) {
        new Mongo().startup();
    };


    public void startup(){  
            
        Javalin app = Javalin.create().start(7070);
        app.get("/", ctx -> ctx.result("mongo"));
        app.get("/mongo", userController::findAll);
        app.post("/mongo", userController::create);
        
    }


    
}
