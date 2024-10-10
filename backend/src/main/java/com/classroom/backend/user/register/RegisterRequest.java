package com.classroom.backend.user.register;

import com.classroom.backend.user.UserRole;
import com.classroom.backend.user.password.PasswordRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import org.hibernate.validator.constraints.Length;

@Builder
public record RegisterRequest(
    @Length(min = 2, max = 30, message = "{message.firstName.length}")
    String firstName,

    @Length(min = 2, max = 30, message = "{message.lastName.length}")
    String lastName,

    @NotBlank(message = "{message.email.empty}")
    @Email(message = "{message.email.valid}")
    String email,

    @Valid
    PasswordRequest passwordRequest,

    @NotNull(message = "{role.not.null}")
    UserRole role
) {
}
