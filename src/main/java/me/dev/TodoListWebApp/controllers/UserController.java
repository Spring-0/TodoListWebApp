package me.dev.TodoListWebApp.controllers;


import me.dev.TodoListWebApp.db.UserRepository;
import me.dev.TodoListWebApp.models.User;
import me.dev.TodoListWebApp.service.UserNotFoundException;
import me.dev.TodoListWebApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;
    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepo) {
        this.userRepository = userRepo;
    }

  
    // create a new user entity
    @PostMapping("/register")
    public User createUser(@RequestBody User user) {
        return userRepository.save(user);
    }

  
    /**
     * Endpoint used to get all users
     *
     * @return
     */
    @GetMapping("/get")
    public List<User> getAllUser() {
        return userService.getAllUser();
    }

  
    /**
     * Endpoint used to get a user given id
     *
     * @param id the user UUID used
     * @return
     */
    @GetMapping("/get/{id}")
    public User getUser(@PathVariable String id) throws UserNotFoundException {
        return userService.getUserById(id);
    }


    /**
     *
     * @param user request body from the user as input
     * @param id
     * @return
     * @throws UserNotFoundException
     */
    @PutMapping("/updateUser/{id}")
    public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable String id) throws UserNotFoundException {
        return userService.updateUser(id,user.getUsername(), user.getPassword());
    }

  
   @PatchMapping ("/update-userName/{id}")
    public ResponseEntity<User> updateUsername(@RequestBody User user, @PathVariable String id) throws UserNotFoundException {
        return userService.updateUsername(id, user.getUsername());
    }


    // delete the user using id
   @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@RequestBody User user,  @PathVariable String id ) throws UserNotFoundException {
       return userService.deleteUserById(id);
    }

}