package jar.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import org.bson.codecs.pojo.annotations.BsonProperty;
// import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.types.ObjectId;

import lombok.Data;


@Data
public class User {
    @JsonSerialize(using = ToStringSerializer.class)
    @BsonProperty("_id")
    private ObjectId id;
    private String name;
    // private String city;
    // private String email;
    // private String phone;
    // private String _class;
    // // employee
    @BsonProperty("id")
    private int studentId;
}
