package org.peaklog.controller;

import lombok.RequiredArgsConstructor;
import org.peaklog.api.AuthControllerApi;
import org.peaklog.config.security.JwtService;
import org.peaklog.model.dto.CreateUserDto;
import org.peaklog.model.dto.LoginDto;
import org.peaklog.model.entity.User;
import org.peaklog.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
public class AuthController implements AuthControllerApi {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    public ResponseEntity<Void> register(CreateUserDto createUserDTO) {
        User user = new User();
        user.setLogin(createUserDTO.getLogin());
        user.setName(createUserDTO.getName());
        user.setBirthDate(LocalDate.parse(createUserDTO.getBirthDate()));
        user.setEmail(createUserDTO.getEmail());
        user.setPassword(passwordEncoder.encode(createUserDTO.getPassword()));
        userRepository.save(user);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<String> login(LoginDto loginDTO) {
        var usuario = userRepository.findByLogin(loginDTO.getLogin())
                .orElseThrow();

        if (!passwordEncoder.matches(loginDTO.getPassword(), usuario.getPassword())) {
            throw new RuntimeException("Credenciales incorrectas");
        }

        return ResponseEntity.ok(jwtService.generateAccessToken(usuario.getLogin()));
    }

    public ResponseEntity<String> refreshToken(String token) {

        try {
            String accessToken = token.substring(7);

            if (!jwtService.isRefreshTokenValid(accessToken)) {
                return ResponseEntity
                        .status(HttpStatus.UNAUTHORIZED)
                        .body("Refresh token inválido");
            }

            String login = jwtService.extractLogin(accessToken);

            String refreshToken = jwtService.generateRefreshToken(login);

            return ResponseEntity.ok(refreshToken);

        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body("Refresh token inválido o expirado");
        }
    }
}
