package dev.kaushar.authservice.authservice;

import dev.kaushar.authservice.authservice.Dtos.SignUpRequestDto;
import dev.kaushar.authservice.authservice.controllers.AuthController;
import dev.kaushar.authservice.authservice.exceptions.UserAlreadyExistsException;
import dev.kaushar.authservice.authservice.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
public class UserSignUp {
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//    @Autowired
//    private AuthController authController;
//
//
//    @Test
//    public void userSignUp() throws UserAlreadyExistsException {
//        SignUpRequestDto signUpRequestDto = new SignUpRequestDto();
//        signUpRequestDto.setEmail("kaush@gmail.com");
//        signUpRequestDto.setName("Kaush");
//        signUpRequestDto.setPassword(passwordEncoder.encode("password"));
//
//        authController.signup(signUpRequestDto);
//
//    }
}
