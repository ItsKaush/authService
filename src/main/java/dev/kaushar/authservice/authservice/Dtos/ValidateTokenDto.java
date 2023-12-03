package dev.kaushar.authservice.authservice.Dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ValidateTokenDto {
    private Long userId;
    private String token;
}
