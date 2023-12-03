package dev.kaushar.authservice.authservice.services;

import dev.kaushar.authservice.authservice.Dtos.UserResponseDto;
import dev.kaushar.authservice.authservice.exceptions.UserNotFoundException;
import dev.kaushar.authservice.authservice.models.Role;
import dev.kaushar.authservice.authservice.models.User;
import dev.kaushar.authservice.authservice.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public UserResponseDto getUser(Long userId) throws UserNotFoundException {
        Optional<User> optionalUser = userRepository.findUserById(userId);

        if(optionalUser.isEmpty()){
            throw new UserNotFoundException("User not exists with Id: " + userId);
        }

        User user = optionalUser.get();


        return UserResponseDto.from(user);
    }

    public UserResponseDto addNewRoles(Long userId, Set<Role> roles) throws UserNotFoundException {
        Optional<User> optionalUser = userRepository.findUserById(userId);

        if(optionalUser.isEmpty()){
            throw new UserNotFoundException("User not exists with Id: " + userId);
        }

        User user = optionalUser.get();
        Set<Role> existingRoles = user.getRoles();
        existingRoles.addAll(roles);

        user.setRoles(existingRoles);

        userRepository.save(user);

        return UserResponseDto.from(user);

    }
}
