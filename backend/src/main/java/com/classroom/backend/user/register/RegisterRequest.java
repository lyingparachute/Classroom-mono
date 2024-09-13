package com.classroom.backend.user.register;

import com.classroom.backend.user.UserRole;
import com.classroom.backend.user.password.PasswordRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

/*
 This class needs to be mutable due to its usage by Thymeleaf
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    @Length(min = 2, max = 30, message = "{message.firstName.length}")
    private String firstName;

    @Length(min = 2, max = 30, message = "{message.lastName.length}")
    private String lastName;

    @NotBlank(message = "{message.email.empty}")
    @Email(message = "{message.email.valid}")
    private String email;

    @Valid
    private PasswordRequest passwordRequest;

    @NotNull(message = "{role.not.null}")
    private UserRole role;
}
