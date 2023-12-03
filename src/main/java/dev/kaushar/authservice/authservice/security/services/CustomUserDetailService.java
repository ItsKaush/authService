package dev.kaushar.authservice.authservice.security.services;

import com.fasterxml.jackson.annotation.OptBoolean;
import dev.kaushar.authservice.authservice.models.User;
import dev.kaushar.authservice.authservice.repositories.UserRepository;
import dev.kaushar.authservice.authservice.security.models.CustomUserDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailService implements UserDetailsService {
    private final UserRepository userRepository;

    public CustomUserDetailService(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findUserByEmailId(username);

        if(userOptional.isEmpty()){
            throw new UsernameNotFoundException("User " + username + " not found");
        }


        return new CustomUserDetails(userOptional.get());
    }
}
