package org.tuo.auth;

import jakarta.validation.constraints.NotBlank;

public class AuthRequestDTO{
    @NotBlank
    public String usernameOrEmail;
    @NotBlank
    public String password;
}
