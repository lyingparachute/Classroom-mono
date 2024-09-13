package com.classroom.backend.department;

import com.classroom.backend.fieldofstudy.FieldOfStudy;
import com.classroom.backend.teacher.Teacher;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

/**
 * A DTO for the {@link Department} entity
 */
@Data
@NoArgsConstructor
public class DepartmentDto {
    private Long id;
    @NotEmpty(message = "{message.name.empty}")
    @Size(min = 10, max = 50, message = "{department.name.size}")
    private String name;

    private String address;

    @NotEmpty(message = "{department.telNumber.empty}")
    @Pattern(regexp = "\\d{9}", message = "{department.telNumber.size}")
    private String telNumber;
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Teacher dean;
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<FieldOfStudy> fieldsOfStudy = new HashSet<>();
}