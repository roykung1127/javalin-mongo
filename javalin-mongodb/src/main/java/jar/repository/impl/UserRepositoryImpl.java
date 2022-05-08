package jar.repository.impl;

import java.util.ArrayList;
import java.util.List;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;  
import com.mongodb.client.MongoDatabase;

import org.bson.types.ObjectId;

import jar.entity.User;
import jar.repository.UserRepository;



public class UserRepositoryImpl implements UserRepository {
    
    
    private MongoDatabase mongoDatabase;
    private MongoCollection<User> mongoCollection;


    public UserRepositoryImpl(MongoClient mongoClient) {

        // // employee
        this.mongoDatabase = mongoClient.getDatabase("roy_testing");
        this.mongoCollection = mongoDatabase.getCollection("employee", User.class);
        // this.mongoCollection = mongoDatabase.getCollection("employee", User.class).withCodecRegistry(codecRegistry);
        
    }



    public List<User> findAll() {
        List<User> result = new ArrayList<>();
        for(User row: mongoCollection.find()) {
            result.add(row);
        }
        return result;
        
    }


    public void create(User user) {
        user.setId(new ObjectId());
        mongoCollection.insertOne(user);
    }
}
