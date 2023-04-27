package me.dev.TodoListWebApp.controllers;


import me.dev.TodoListWebApp.db.UserRepository;
import me.dev.TodoListWebApp.models.User;
import me.dev.TodoListWebApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<?> createUser(@RequestBody User user) {
        if(user.getUsername().equals("") || user.getPassword().equals("")){
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
        }
        userRepository.save(user);
        return ResponseEntity.ok(user);
    }


    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody User user) {
        User userFromDB = userRepository.findUserByUsernameAndPassword(user.getUsername(), user.getPassword());

        if (user.getUsername().equals("") || user.getPassword().equals("")) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
        } else if (userFromDB == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } else {
            return ResponseEntity.ok(user);
        }
    }

  
    /**
     * Endpoint used to get all users
     *
     * @return all user's
     */
    @GetMapping("/get")
    public List<User> getAllUser() {
        return userService.getAllUser();
    }

  
    /**
     * Endpoint used to retrieve a user given id
     *
     * @param id the user's UUID
     * @return
     */
    @GetMapping("/get/{id}")
    public ResponseEntity<?> getUser(@PathVariable String id)  {
        return userService.getUserById(id);
    }


    /**
     * endpoint for updating the username and password for a given ID
     * @param user request body from the user as input
     * @param id the user's UUID
     * @return
     */
    @PutMapping("/updateUser/{id}")
    public ResponseEntity<?> updateUser(@RequestBody User user, @PathVariable String id) {
        return userService.updateUser(id,user.getUsername(), user.getPassword());
    }

    /**
     * Endpoint for updating a username
     * @param user  the body containing the new changes
     * @param id the user's UUID
     * @return
     */
   @PatchMapping ("/update-userName/{id}")
    public ResponseEntity<?> updateUsername(@RequestBody User user, @PathVariable String id)  {
        return  userService.updateUsername(id, user.getUsername());
    }


    /**
     * delete a user for a given ID
     *
     * @param id the user's UUID
     * @return
     */
   @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser( @PathVariable String id )  {
       return userService.deleteUserById(id);
    }


}