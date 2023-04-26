package me.dev.TodoListWebApp.service;

import me.dev.TodoListWebApp.db.UserRepository;
import me.dev.TodoListWebApp.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    // auto-inject instance of UserRepository
    @Autowired
    UserRepository userRepo;

    /**
     * if user id exist, then return the user
     * else return an error
     *
     * @param id the user's UUID
     * @return
     */
    public ResponseEntity<?> getUserById(String id) {
        if( userRepo.existsById(id)){
            User found = userRepo.findById(id).get();
            return ResponseEntity.ok(found);
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("User with ID: " + id + " not found.\nPlease create an account.");
        }

    }

    /**
     * return a list of all users
     *
     * @return
     */
    public List<User> getAllUser(){
        return (ArrayList<User>) userRepo.findAll();
    }

    /**
     * if user exist, then delete a user with a given id
     * else return an error
     *
     * @param id the user's UUID
     * @return
     */
    public ResponseEntity<?> deleteUserById(String id){
        if (userRepo.existsById(id)) {
            userRepo.deleteById(id);
            return ResponseEntity.ok().body("User with ID: "+id + " was successfully deleted.");
        }  else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("User with ID: " + id + " not found. Please create an account.");
        }
    }

    /**
     * if user exist, then update the username of a given id
     * else return an error
     * @param id the user's UUID
     * @param username the username to be updated
     * @return
     */
    public ResponseEntity<?> updateUsername(String id, String username)  {

        if (userRepo.existsById(id)) {
            User updateUsername = userRepo.findById(id).get();
            updateUsername.setUsername(username);
            userRepo.save(updateUsername);
            return ResponseEntity.ok().body("Updated for ID: " +id+"\nNew username: "+updateUsername.getUsername());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("User with ID: " + id + "" +
                            "not found. Please create an account.");
        }
    }

    /**
     * check if the given UUID exists, if true update the password and username
     * else return an error
     *
     * @param id the user's UUID
     * @param password the password that is to be updated
     * @param username the username to be updated
     * @return
     */
    public ResponseEntity<?>  updateUser( String id, String username, String password) {
        if( userRepo.existsById(id)){
            User updatedUser = userRepo.findById(id).get();
            updatedUser.setUsername(username);
            updatedUser.setPassword(password);
            userRepo.save(updatedUser);
            return ResponseEntity.ok().body("Updated for ID: " + id+"\nNew username: "+ updatedUser.getUsername() +"" +
                    "\nNew password: "+ updatedUser.getPassword());
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("User with ID: " + id + "" +
                            "not found. Please create an account.");
        }


    }



}

