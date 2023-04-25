package me.dev.TodoListWebApp.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String id){
        super("User not found with ID: " + id + ". Please create an account.");
    }



    //todo: display an error that user can see
    /**
    /* * Exception handling
     *
     * @param ex the message with is printed to the user
     * @return
     *//*
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleUserNotFoundException(UserNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }*/
}
