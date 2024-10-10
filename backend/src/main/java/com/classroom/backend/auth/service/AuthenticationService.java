package com.classroom.backend.auth.service;

import com.classroom.backend.auth.model.AuthenticationRequest;
import com.classroom.backend.auth.model.AuthenticationResponse;
import com.classroom.backend.token.JwtService;
import com.classroom.backend.token.Token;
import com.classroom.backend.token.TokenRepository;
import com.classroom.backend.token.TokenType;
import com.classroom.backend.user.User;
import com.classroom.backend.user.UserRepository;
import com.classroom.backend.user.UserRole;
import com.classroom.backend.user.register.RegisterRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository repository;
    private final ModelMapper mapper;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Transactional
    public AuthenticationResponse register(final RegisterRequest request) {
        final var user = new User();
        mapper.map(request, user);
        user.setPassword(passwordEncoder.encode(request.passwordRequest().password()));
        if (user.getRole() == null) {
            user.setRole(UserRole.ROLE_STUDENT);
        }
        final var savedUser = repository.save(user);
        final var jwtToken = jwtService.generateToken(savedUser);
        saveUserToken(savedUser, jwtToken);
        return new AuthenticationResponse(jwtToken);
    }

    @Transactional
    public AuthenticationResponse authenticate(final AuthenticationRequest request) {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                request.email(),
                request.password()
            ));
        final var user = repository.findByEmail(request.email())
            .orElseThrow(() -> new UsernameNotFoundException("User with email " + request.email() + " does not exist in database."));
        final var jwtToken = jwtService.generateToken(user);
        revokeAllUserTokens(user);
        saveUserToken(user, jwtToken);
        return new AuthenticationResponse(jwtToken);
    }

    private void saveUserToken(final User user,
                               final String jwtToken) {
        final var token = Token.builder()
            .user(user)
            .token(jwtToken)
            .tokenType(TokenType.BEARER)
            .expired(false)
            .revoked(false)
            .build();
        tokenRepository.save(token);
    }

    private void revokeAllUserTokens(final User user) {
        final var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }
}
