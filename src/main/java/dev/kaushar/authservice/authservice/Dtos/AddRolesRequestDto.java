package dev.kaushar.authservice.authservice.Dtos;

import dev.kaushar.authservice.authservice.models.Role;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class AddRolesRequestDto {
    private Long userId;
    private Set<Role> roles;
}
