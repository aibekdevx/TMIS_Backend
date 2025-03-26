package com.diploma.authservice.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuhtenticationRequest {

    @Email(message = "Введите корректный email!")
    @NotBlank(message = "Email обязателен к заполнению!")
    private String email;

    @NotBlank(message = "Введите пароль!")
    private String password;
}
