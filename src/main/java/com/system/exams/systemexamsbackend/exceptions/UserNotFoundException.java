package com.system.exams.systemexamsbackend.exceptions;

public class UserNotFoundException extends Exception {

    public UserNotFoundException() {
        super("There is no user with this name, try again !!");
    }

    public UserNotFoundException(String message) {
        super(message);
    }
    
}
