package org.tuo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Email;

public class RegisterRequestDTO {
    @NotBlank
    @Size(min=3, max=50)
    public String username;
    @NotBlank
    @Email
    @Size(max=100)
    public String email;
    @NotBlank
    @Size(min=6, max=50)
    public String password;
}
