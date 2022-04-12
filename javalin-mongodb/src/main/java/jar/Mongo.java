package jar;

import io.javalin.Javalin;
import jar.controller.UserController;

import jar.repository.impl.UserRepositoryImpl;


import com.mongodb.MongoClientSettings;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;


import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

import java.util.Arrays;


public class Mongo {

    private final MongoClient mongoClient;

    private UserController userController;

    public Mongo() {

        CodecRegistry pojoCodecRegistry = fromProviders(PojoCodecProvider.builder().automatic(true).build());
        CodecRegistry codecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(), pojoCodecRegistry);

        this.mongoClient = MongoClients.create(MongoClientSettings.builder()
        .applyToClusterSettings(builder ->
                builder.hosts(Arrays.asList(new ServerAddress("localhost", 27017))))
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
