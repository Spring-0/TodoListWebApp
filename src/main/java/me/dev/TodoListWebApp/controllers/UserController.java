package me.dev.TodoListWebApp.controllers;


import me.dev.TodoListWebApp.db.UserRepository;
import me.dev.TodoListWebApp.models.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepo){
        this.userRepository = userRepo;

    }
    @PostMapping("/register")
    public User createUser(@RequestBody User user){
       userRepository.save(user);
        return  user;
    }




}
