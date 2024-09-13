package com.classroom.backend.auth.service;

import com.classroom.backend.auth.model.UpdateRequest;
import com.classroom.backend.exception.EntityNotFoundException;
import com.classroom.backend.exception.InvalidOldPasswordException;
import com.classroom.backend.exception.UserAlreadyExistException;
import com.classroom.backend.student.StudentDto;
import com.classroom.backend.student.StudentService;
import com.classroom.backend.teacher.TeacherDto;
import com.classroom.backend.teacher.TeacherService;
import com.classroom.backend.user.User;
import com.classroom.backend.user.UserRepository;
import com.classroom.backend.user.UserRole;
import com.classroom.backend.user.register.RegisterRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserManagementService implements UserDetailsService {

    private final UserRepository repository;
    private final ModelMapper mapper;
    private final BCryptPasswordEncoder passwordEncoder;
    private final StudentService studentService;
    private final TeacherService teacherService;

    @Transactional
    public User register(final RegisterRequest request) throws UserAlreadyExistException {
        final var email = request.getEmail();
        if (emailExists(email))
            throw new UserAlreadyExistException("There is already an account with email address: " + email);
        final var user = new User();
        mapper.map(request, user);
        user.setPassword(passwordEncoder.encode(request.getPasswordRequest().getPassword()));
        final var savedUser = repository.save(user);
        createUniversityAttendeeAccount(request, savedUser);
        savedUser.getAttendee();
        return savedUser;
    }

    private void createUniversityAttendeeAccount(final RegisterRequest request,
                                                 final User user) {
        final var requestRole = request.getRole();
        if (requestRole == UserRole.ROLE_STUDENT) {
            final var studentDto = mapper.map(request, StudentDto.class);
            studentDto.setUserDetails(user);
            studentService.create(studentDto);
        }
        if (requestRole == UserRole.ROLE_TEACHER || requestRole == UserRole.ROLE_DEAN) {
            final var teacherDto = mapper.map(request, TeacherDto.class);
            teacherDto.setUserDetails(user);
            teacherService.create(teacherDto);
        }
    }

    @Transactional
    public User update(final UpdateRequest request) {
        final var user = loadUserByUsername(request.email());
        mapper.map(request, user);
        return repository.save(user);
    }

    @Override
    public User loadUserByUsername(final String email) {
        return repository.findByEmail(email)
            .orElseThrow(() -> new UsernameNotFoundException("User with email " + email + " does not exist in database."));
    }

    @Transactional
    public void removeByUsername(final String email) {
        repository.delete(loadUserByUsername(email));
    }

    @Transactional
    public void removeById(final Long id) {
        final var byId = repository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException(
                User.class, "ID", id.toString()));
        removeUniversityAttendeeAccount(byId);
        repository.delete(byId);
    }

    public void updateUserPassword(final User user,
                                   final String newPassword) {
        user.setPassword(passwordEncoder.encode(newPassword));
        repository.save(user);
    }

    public void updateUserEmail(final User user,
                                final String newEmail) {
        user.setEmail(newEmail);
        repository.save(user);
    }

    public boolean emailExists(final String email) {
        return repository.findByEmail(email).isPresent();
    }

    private void removeUniversityAttendeeAccount(final User user) {
        if (user.getStudent() != null) {
            studentService.remove(user.getStudent().getId());
        }
        if (user.getTeacher() != null) {
            teacherService.remove(user.getTeacher().getId());
        }
    }

    public void invalidateSession(final HttpServletRequest request) {
        final var session = request.getSession();
        SecurityContextHolder.clearContext();
        if (session != null) {
            session.invalidate();
        }
    }

    public void validateOldInputPassword(final String inputPassword,
                                         final String actualEncodedPassword) {
        if (!passwordEncoder.matches(inputPassword, actualEncodedPassword))
            throw new InvalidOldPasswordException("Invalid old password!");
    }
}
