package me.dev.TodoListWebApp.controllers;


import com.fasterxml.jackson.annotation.JsonAnyGetter;
import me.dev.TodoListWebApp.db.UserRepository;
import me.dev.TodoListWebApp.models.Todo;
import me.dev.TodoListWebApp.models.User;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserRepository userRepository;

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
        return new ArrayList<>((Collection) userRepository.findAll());
    }

    /**
     * Endpoint used to get a user given id
     *
     * @param id
     * @return
     */
    @GetMapping("/get/{id}")
    public ResponseEntity<User> getUser(@PathVariable String id) {
        User user = userRepository.findById(id).orElseThrow();
        return ResponseEntity.ok(user);
    }


    /**
     * Endpoint used to update user info given an id
     *
     * @param user
     * @param id
     * @return
     */
    @PutMapping("/update/{id}")
    public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable String id) {
        // check if id exist else create a new user
        User update = userRepository.findById(id).orElseThrow();

        // update
        update.setUsername(user.getUsername());
        update.setPassword(user.getPassword());

        userRepository.save(update);
        return ResponseEntity.ok(update);
    }

   //Todo: finish partial user info update

   /* @PatchMapping ("/update/{id}/{username}")
    public ResponseEntity<User> updateUsername(@PathVariable String id, @PathVariable String username){
        User updateUsername= userRepository.findById(id).orElseThrow();

        updateUsername.setUsername(username);
        userRepository.save(updateUsername);
        return ResponseEntity.ok(updateUsername);
    }*/


    //todo: finish implementation of the delete endpoint
    // delete the user using id
   /* @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser( @PathVariable String id ){
            Optional<User> user = userRepository.findById(id);
        if (user.isPresent()){
            userRepository.deleteById(id);
            return ResponseEntity.ok();
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND);
        }


    }
*/


}
