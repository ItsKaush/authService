package dev.kaushar.authservice.authservice.controllers;

import dev.kaushar.authservice.authservice.Dtos.*;
import dev.kaushar.authservice.authservice.exceptions.IncorrcetPasswordException;
import dev.kaushar.authservice.authservice.exceptions.TokenNotFoundException;
import dev.kaushar.authservice.authservice.exceptions.UserAlreadyExistsException;
import dev.kaushar.authservice.authservice.exceptions.UserNotFoundException;
import dev.kaushar.authservice.authservice.models.SessionStatus;
import dev.kaushar.authservice.authservice.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;


@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;


@Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signup")
    public ResponseEntity<UserDto> signup(@RequestBody SignUpRequestDto signUpRequestDto) throws UserAlreadyExistsException {
        UserDto userDto= authService.signUp(signUpRequestDto.getName(),signUpRequestDto.getEmail(),signUpRequestDto.getPassword());

        return new ResponseEntity<>(userDto,HttpStatus.OK);
    }

    @PostMapping("/signin")
    public ResponseEntity<UserDto> signin(@RequestBody SignUpRequestDto signUpRequestDto) throws UserNotFoundException, IncorrcetPasswordException {

        return authService.signIn(signUpRequestDto.getEmail(), signUpRequestDto.getPassword());
    }

    @PostMapping("/validate")
    public ResponseEntity<ValidateSessionResponseDto> validate(@RequestBody ValidateTokenDto validateTokenDto) throws TokenNotFoundException {
        Optional<UserDto> userDto  = authService.validate(validateTokenDto.getUserId(), validateTokenDto.getToken());

        if(userDto.isEmpty()){
            ValidateSessionResponseDto validateSessionResponseDto = new ValidateSessionResponseDto();
            validateSessionResponseDto.setSessionStatus(SessionStatus.INVALID);
            return new ResponseEntity<>(validateSessionResponseDto, HttpStatus.OK);

        }

        ValidateSessionResponseDto validateSessionResponseDto = new ValidateSessionResponseDto();
        validateSessionResponseDto.setUserDto(userDto.get());
        validateSessionResponseDto.setSessionStatus(SessionStatus.ACTIVE);

        return new ResponseEntity<>(validateSessionResponseDto, HttpStatus.OK);
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@RequestBody LogoutRequestDto logoutRequestDto){
        authService.logOut(logoutRequestDto.getUserId(), logoutRequestDto.getToken());

        return ResponseEntity.ok().build();
    }


}















