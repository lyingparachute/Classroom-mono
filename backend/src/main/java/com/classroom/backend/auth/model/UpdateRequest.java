package com.classroom.backend.auth.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;


public record UpdateRequest(
    @Length(min = 2, max = 30, message = "{message.firstName.length}")
    String firstName,

    @Length(min = 2, max = 30, message = "{message.lastName.length}")
    String lastName,

    @NotBlank(message = "{message.email.empty}")
    @Email(message = "{message.email.valid}")
    String email,

    // FIXME - Password shouldn't be editable
    //    @ValidPassword
    String password
) {
}
