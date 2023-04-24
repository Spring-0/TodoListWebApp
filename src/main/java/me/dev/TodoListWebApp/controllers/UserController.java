package me.dev.TodoListWebApp.controllers;


import me.dev.TodoListWebApp.db.UserRepository;
import me.dev.TodoListWebApp.models.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepo){
        this.userRepository = userRepo;

    }
    @PostMapping("/user")
    public String createUser(User user){
        // test
        System.out.println(user);
        return  "1232323";
    }




}
