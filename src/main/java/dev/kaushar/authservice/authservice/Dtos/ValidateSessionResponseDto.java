package dev.kaushar.authservice.authservice.Dtos;

import dev.kaushar.authservice.authservice.models.SessionStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ValidateSessionResponseDto {
    private UserDto userDto;
    private SessionStatus sessionStatus;
}
