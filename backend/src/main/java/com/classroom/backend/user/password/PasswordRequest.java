package com.classroom.backend.user.password;

import com.classroom.backend.auth.validation.password.PasswordMatches;
import com.classroom.backend.auth.validation.password.ValidPassword;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
 This class needs to be mutable due to its usage by Thymeleaf
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@PasswordMatches
public class PasswordRequest {
    @ValidPassword
    private String password;
    private String matchingPassword;
}
