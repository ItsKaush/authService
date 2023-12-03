package dev.kaushar.authservice.authservice.Dtos;

import dev.kaushar.authservice.authservice.models.Role;
import dev.kaushar.authservice.authservice.models.User;
import lombok.Getter;
import lombok.Setter;


import java.util.Set;

@Getter
@Setter
public class UserResponseDto {
    private String name;
    private String email;
//    private String password;
    private Set<Role> roles;

    public static UserResponseDto from(User user){
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setEmail(user.getEmailId());
        userResponseDto.setName(user.getName());
//        userResponseDto.setPassword(user.getPassword());
        Set<Role> roles = user.getRoles();

        userResponseDto.setRoles(roles);

        return userResponseDto;

    }

}
