package dev.kaushar.authservice.authservice.exceptions;

public class TokenNotFoundException extends Exception{
    public TokenNotFoundException(String message){
        super(message);
    }
}
