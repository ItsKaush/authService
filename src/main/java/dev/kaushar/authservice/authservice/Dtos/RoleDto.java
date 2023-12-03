package dev.kaushar.authservice.authservice.Dtos;

import dev.kaushar.authservice.authservice.models.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleDto {
    private String role;

    public static RoleDto from(Role role){
        RoleDto roleDto = new RoleDto();
        roleDto.setRole(role.getRole());
        return roleDto;
    }
}
