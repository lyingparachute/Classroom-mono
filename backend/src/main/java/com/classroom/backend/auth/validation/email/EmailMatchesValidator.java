package com.classroom.backend.auth.validation.email;

import com.classroom.backend.user.email.EmailChangeRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class EmailMatchesValidator implements ConstraintValidator<EmailMatches, Object> {

    @Override
    public boolean isValid(final Object obj,
                           final ConstraintValidatorContext context) {
        final var request = (EmailChangeRequest) obj;
        final var isValid = request.email().equals(request.matchingEmail());
        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(context.getDefaultConstraintMessageTemplate())
                .addPropertyNode("matchingEmail").addConstraintViolation();
        }
        return isValid;
    }
}
