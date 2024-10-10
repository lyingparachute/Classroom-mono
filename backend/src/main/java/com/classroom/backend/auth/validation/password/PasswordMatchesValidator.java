package com.classroom.backend.auth.validation.password;

import com.classroom.backend.user.password.PasswordRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {

    @Override
    public boolean isValid(final Object obj,
                           final ConstraintValidatorContext context) {
        final var request = (PasswordRequest) obj;
        final var isValid = request.password().equals(request.matchingPassword());
        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(context.getDefaultConstraintMessageTemplate())
                    .addPropertyNode("matchingPassword").addConstraintViolation();
        }
        return isValid;
    }
}
