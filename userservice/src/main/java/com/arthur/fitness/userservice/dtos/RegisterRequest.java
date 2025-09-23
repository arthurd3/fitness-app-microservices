package com.arthur.fitness.userservice.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RegisterRequest {
    @NotBlank(message = "Email is Required")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Password is Required")
    @Size(min = 6 , message = "Password must have atleast of 6 characters")
    private String password;

    private String keycloakId;

    private String firstName;
    private String lastName;
}
