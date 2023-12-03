package dev.kaushar.authservice.authservice.controllers;

import dev.kaushar.authservice.authservice.Dtos.AddRolesRequestDto;
import dev.kaushar.authservice.authservice.Dtos.UserResponseDto;
import dev.kaushar.authservice.authservice.exceptions.UserNotFoundException;
import dev.kaushar.authservice.authservice.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    private UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    //Get details of a user
    @GetMapping("/{userId}")
    public ResponseEntity<UserResponseDto> getUser(@PathVariable("userId") Long userId) throws UserNotFoundException {

        return new ResponseEntity<>(userService.getUser(userId), HttpStatus.OK);
    }


    //set the roles of a user

    @PostMapping("/addroles")
    public ResponseEntity<UserResponseDto> addNewRoles(@RequestBody AddRolesRequestDto addRolesRequestDto) throws UserNotFoundException {
        UserResponseDto userResponseDto = userService.addNewRoles(addRolesRequestDto.getUserId(),addRolesRequestDto.getRoles());

        return new ResponseEntity<>(userResponseDto, HttpStatus.OK);
    }
}
