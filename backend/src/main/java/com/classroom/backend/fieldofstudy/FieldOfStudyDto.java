package com.classroom.backend.fieldofstudy;

import com.classroom.backend.department.Department;
import com.classroom.backend.enums.AcademicTitle;
import com.classroom.backend.enums.LevelOfEducation;
import com.classroom.backend.enums.ModeOfStudy;
import com.classroom.backend.student.Student;
import com.classroom.backend.subject.Subject;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import java.util.HashSet;
import java.util.Set;

/**
 * A DTO for the {@link FieldOfStudy} entity
 */
@Data
@NoArgsConstructor
public class FieldOfStudyDto {
    private Long id;
    @NotEmpty(message = "{message.name.empty}")
    @Size(min = 2, max = 50, message = "{message.name.length.50}")
    private String name;
    @Length(max = 500, message = "{message.description.length}")
    private String description;
    private LevelOfEducation levelOfEducation;
    private ModeOfStudy mode;
    private AcademicTitle title;
    private String image;
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Department department;
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Subject> subjects = new HashSet<>();
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Student> students = new HashSet<>();
}