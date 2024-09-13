package com.classroom.backend.subject;

import com.classroom.backend.enums.Semester;
import com.classroom.backend.fieldofstudy.FieldOfStudy;
import com.classroom.backend.teacher.Teacher;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import java.util.HashSet;
import java.util.Set;

/**
 * A DTO for the {@link Subject} entity
 */
@Data
@NoArgsConstructor
class SubjectDto {
    private Long id;
    @NotEmpty(message = "{message.name.empty}")
    @Length(min = 2, max = 30, message = "{message.name.length}")
    private String name;
    @Length(max = 500, message = "{message.description.length}")
    private String description;
    @Enumerated(EnumType.STRING)
    private Semester semester;
    @PositiveOrZero
    @Max(value = 100, message = "{subject.hoursInSemester.max}")
    private int hoursInSemester;
    @Max(value = 60, message = "{subject.ects.max}")
    @Min(value = 5, message = "{subject.ects.min}")
    private int ectsPoints;
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private FieldOfStudy fieldOfStudy;
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Teacher> teachers = new HashSet<>();
}
