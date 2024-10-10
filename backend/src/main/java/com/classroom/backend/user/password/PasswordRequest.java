package com.classroom.backend.user.password;

import com.classroom.backend.auth.validation.password.PasswordMatches;
import com.classroom.backend.auth.validation.password.ValidPassword;

@PasswordMatches
public record PasswordRequest(
    @ValidPassword
    String password,
    String matchingPassword
) {
}
