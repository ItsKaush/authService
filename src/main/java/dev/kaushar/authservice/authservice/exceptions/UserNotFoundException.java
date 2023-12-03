package dev.kaushar.authservice.authservice.exceptions;


public class UserNotFoundException extends Exception{
    public UserNotFoundException(String message){
        super(message);
    }
}
