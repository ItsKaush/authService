package dev.kaushar.authservice.authservice.controllers;

import dev.kaushar.authservice.authservice.Dtos.RoleDto;
import dev.kaushar.authservice.authservice.exceptions.RoleAlreadyExistsException;
import dev.kaushar.authservice.authservice.services.RoleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("roles")
public class RoleController {
    private final RoleService roleService;

    public RoleController(RoleService roleService){
        this.roleService = roleService;
    }

    @PostMapping
    public ResponseEntity<RoleDto> addRole(@RequestBody RoleDto roleDto) throws RoleAlreadyExistsException {
           return new ResponseEntity<>(roleService.addRole(roleDto.getRole()), HttpStatus.OK);
    }

}
