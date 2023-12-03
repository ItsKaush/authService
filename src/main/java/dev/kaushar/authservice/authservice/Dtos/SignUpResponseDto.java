package dev.kaushar.authservice.authservice.Dtos;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SignUpResponseDto {
    private String name;
    private String email;
    private String message;
}
