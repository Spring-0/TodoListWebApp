package me.dev.TodoListWebApp.service;

import me.dev.TodoListWebApp.db.UserRepository;
import me.dev.TodoListWebApp.models.User;
import org.springframework.beans.factory.annotation.Autowired;
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
     * else throw an exception
     *
     * @param id the user's UUID
     * @return
     * @throws UserNotFoundException
     */
    public User getUserById(String id) throws UserNotFoundException {
        if( userRepo.existsById(id)){
            return userRepo.findById(id).get();
        }else {
           throw new UserNotFoundException(id);
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
     * else throw an exception
     *
     * @param id the user's UUID
     * @return
     * @throws UserNotFoundException
     */
    public ResponseEntity<String> deleteUserById(String id) throws UserNotFoundException{
        if (userRepo.existsById(id)) {
            userRepo.deleteById(id);
            return ResponseEntity.ok("User with ID: "+id + "was successfully deleted.");
        }  else{
          throw new UserNotFoundException(id);

        }
    }

    /**
     * if user exist, then update the username of a given id
     * else throw an exception
     * @param id the user's UUID
     * @param username the username to be updated
     * @return
     * @throws UserNotFoundException
     */
    public ResponseEntity<User> updateUsername(String id, String username) throws UserNotFoundException {

        if (userRepo.existsById(id)) {
            User updateUsername = userRepo.findById(id).get();
            updateUsername.setUsername(username);
            userRepo.save(updateUsername);
            return ResponseEntity.ok(updateUsername );
        } else {
            throw new UserNotFoundException(id);
        }
    }

    /**
     * check if your exist, if true update a user given id, password, and username
     * else throw an exception
     *
     * @param id the user's UUID
     * @param password the password that is to be updated
     * @param username the username to be updated
     * @return
     * @throws UserNotFoundException
     */
    public ResponseEntity<User>  updateUser( String id, String username, String password) throws UserNotFoundException {
        if( userRepo.existsById(id)){
            User updatedUser = userRepo.findById(id).get();
            updatedUser.setUsername(username);
            updatedUser.setPassword(password);
            userRepo.save(updatedUser);
            return ResponseEntity.ok(updatedUser);
        }else{
            throw new UserNotFoundException(id);
        }


    }



}

