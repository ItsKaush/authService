package dev.kaushar.authservice.authservice.exceptions;

public class RoleAlreadyExistsException extends Exception{
    public RoleAlreadyExistsException(String message){
        super(message);
    }
}
