package dev.kaushar.authservice.authservice.services;

import dev.kaushar.authservice.authservice.Dtos.RoleDto;
import dev.kaushar.authservice.authservice.exceptions.RoleAlreadyExistsException;
import dev.kaushar.authservice.authservice.models.Role;
import dev.kaushar.authservice.authservice.repositories.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleService {
    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository){
        this.roleRepository = roleRepository;
    }

    public RoleDto addRole(String role) throws RoleAlreadyExistsException {
        Optional<Role> existingRole = roleRepository.findByRole(role);

        if(existingRole.isPresent()){
            throw new RoleAlreadyExistsException("Role :" + role + "Already Exists");
        }

        Role newRole = new Role();
        newRole.setRole(role);

        Role savedRole = roleRepository.save(newRole);

        return RoleDto.from(savedRole);
    }

}
