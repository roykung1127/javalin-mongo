package jar.controller;

import org.eclipse.jetty.client.HttpResponseException;
import org.jetbrains.annotations.NotNull;

import io.javalin.http.BadRequestResponse;
import io.javalin.http.Context;
import jar.entity.User;
import jar.repository.UserRepository;


public class UserController {

    private  UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public  void findAll(Context context) {

        // if(1 == 1) {

     
        // }

       context.json(userRepository.findAll());
    }

    public void create(@NotNull Context context) {
        User user = context.bodyAsClass(User.class);
        userRepository.create(user);
    }

}
