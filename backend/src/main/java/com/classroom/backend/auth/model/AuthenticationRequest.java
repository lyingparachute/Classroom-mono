package com.classroom.backend.auth.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record AuthenticationRequest(
    @NotBlank(message = "{message.email.empty}")
    @Email(message = "{message.email.valid}")
    String email,
    String password
) {
}
