package dev.kaushar.authservice.authservice.Dtos;

import dev.kaushar.authservice.authservice.models.Role;
import dev.kaushar.authservice.authservice.models.User;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class UserDto {
    private String name;
    private String email;
    private Set<Role> roles;


    public static UserDto from(User user){
        UserDto userDto = new UserDto();
        userDto.setEmail(user.getEmailId());
        userDto.setName(user.getName());
        userDto.setRoles(user.getRoles());

        return userDto;
    }
}
