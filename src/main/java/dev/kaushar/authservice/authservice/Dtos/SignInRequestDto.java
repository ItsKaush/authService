package dev.kaushar.authservice.authservice.Dtos;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Primary;

@Getter
@Setter
public class SignInRequestDto {
    private String email;
    private String password;
}
