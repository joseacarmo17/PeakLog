package org.peaklog.controller;

import lombok.RequiredArgsConstructor;
import org.peaklog.api.AuthControllerApi;
import org.peaklog.model.dto.CreateUserDto;
import org.peaklog.model.dto.LoginDto;
import org.peaklog.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController implements AuthControllerApi {

    private final AuthService authService;

    @Override
    public ResponseEntity<Void> register(CreateUserDto createUserDTO) {
        this.authService.register(createUserDTO);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<String> login(LoginDto loginDTO) {

        return ResponseEntity.ok(this.authService.login(loginDTO));
    }

    public ResponseEntity<String> refreshToken(String token) {

        return this.authService.refreshToken(token) != null
                ? ResponseEntity.ok(this.authService.refreshToken(token))
                : ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}
