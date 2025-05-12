package com.diploma.authservice.auth;

import com.diploma.authservice.dto.TeacherDTO;
import com.diploma.authservice.service.TeacherService;
import com.diploma.authservice.user.Role;
import com.diploma.authservice.user.User;
import com.diploma.authservice.user.UserRepository;
import com.diploma.authservice.config.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final TeacherService teacherService;

    public AuthenticationResponse register(RegisterRequest request) {
        // 1. Проверяем уникальность email
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Пользователь с email " + request.getEmail() + " уже существует"
            );
        }

        // 2. Создаём пользователя с нужной ролью
        User user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .createdAt(new Date())
                .updatedAt(new Date())
                .build();
        userRepository.save(user);

        // 3. Если роль TEACHER — создаём запись в таблице teachers
        if (request.getRole() == Role.TEACHER) {
            TeacherDTO teacherDTO = new TeacherDTO();
            teacherDTO.setUser(user);
            teacherDTO.setCreatedAt(new Date());
            teacherDTO.setUpdatedAt(new Date());
            teacherService.createTeacher(teacherDTO);
        }

        // 4. Генерируем JWT с ролью в extraClaims и возвращаем ответ
        Map<String, Object> extraClaims = Map.of("role", user.getRole().name());
        String token = jwtService.generateToken(extraClaims, user);
        return AuthenticationResponse.builder()
                .token(token)
                .build();
    }

    public AuthenticationResponse authenticate(AuhtenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.UNAUTHORIZED, "Неверные учетные данные"));

        // Генерируем JWT с ролью в extraClaims
        Map<String, Object> extraClaims = Map.of("role", user.getRole().name());
        String token = jwtService.generateToken(extraClaims, user);
        return AuthenticationResponse.builder()
                .token(token)
                .build();
    }
}
