package jar;

import io.javalin.Javalin;

import com.mongodb.MongoClient;  
import com.mongodb.client.MongoCollection;  
import com.mongodb.client.MongoDatabase;  
import org.bson.Document;  

public class HelloWorld {
    public static void main(String[] args) {

        try{  
            //---------- Connecting DataBase -------------------------//  
            MongoClient mongoClient = new MongoClient( "localhost" , 27017 );  
            //---------- Creating DataBase ---------------------------//  
            MongoDatabase db = mongoClient.getDatabase("roy_testing");  
            //---------- Creating Collection -------------------------//  
            MongoCollection<Document> table = db.getCollection("employee");  
            //---------- Creating Document ---------------------------//    
            Document doc = new Document("name", "Peter Wing");  
            doc.append("id",12);  
            //----------- Inserting Data ------------------------------//  
            table.insertOne(doc);  

            Javalin app = Javalin.create().start(7070);
            app.get("/", ctx -> ctx.result("Hello World"));

        }catch(Exception e){  
        System.out.println(e);  
        }  

        
    }
}