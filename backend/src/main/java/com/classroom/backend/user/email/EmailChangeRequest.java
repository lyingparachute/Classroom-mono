package com.classroom.backend.user.email;

import com.classroom.backend.auth.validation.email.EmailMatches;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

@EmailMatches
public record EmailChangeRequest(
    @NotEmpty(message = "{message.email.empty}")
    @Email(message = "{message.email.valid}")
    String email,
    String matchingEmail,
    String expectedVerificationCode,
    String verificationCode
) {
}
