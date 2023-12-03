package dev.kaushar.authservice.authservice.controllers;

import dev.kaushar.authservice.authservice.Dtos.ErrorResponseDto;
import dev.kaushar.authservice.authservice.exceptions.IncorrcetPasswordException;
import dev.kaushar.authservice.authservice.exceptions.TokenNotFoundException;
import dev.kaushar.authservice.authservice.exceptions.UserAlreadyExistsException;
import dev.kaushar.authservice.authservice.exceptions.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler({UserAlreadyExistsException.class, UserNotFoundException.class, IncorrcetPasswordException.class, TokenNotFoundException.class})
    public ResponseEntity<ErrorResponseDto> notFound(Exception exception){
        ErrorResponseDto errorResponseDto = new ErrorResponseDto();
        errorResponseDto.setErrorMessage(exception.getMessage());

        return new ResponseEntity<>(errorResponseDto, HttpStatus.NOT_FOUND);
    }

}
