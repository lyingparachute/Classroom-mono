package com.classroom.backend.teacher;


import com.classroom.backend.department.Department;
import com.classroom.backend.student.Student;
import com.classroom.backend.subject.Subject;
import com.classroom.backend.user.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import java.util.HashSet;
import java.util.Set;

/**
 * A DTO for the {@link Teacher} entity
 */
@Data
@NoArgsConstructor
public class TeacherDto {
    private Long id;
    @NotNull
    @NotEmpty(message = "{message.firstName.empty}")
    @Length(min = 2, max = 30, message = "{message.firstName.length}")
    private String firstName;
    @NotNull
    @NotEmpty(message = "{message.lastName.empty}")
    @Length(min = 2, max = 30, message = "{message.lastName.length}")
    private String lastName;
    @NotNull
    @Min(value = 21, message = "{teacher.age.min}")
    @Max(value = 80, message = "{teacher.age.max}")
    private int age;
    @NotNull
    @NotEmpty(message = "{message.email.empty}")
    @Email(message = "{message.email.valid}")
    private String email;
    private User userDetails;
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Department department;
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Student> students = new HashSet<>();
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Subject> subjects = new HashSet<>();

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }
}
