package org.peaklog.controller;

import lombok.RequiredArgsConstructor;
import org.peaklog.api.AuthControllerApi;
import org.peaklog.config.security.JwtService;
import org.peaklog.model.dto.CreateUserDto;
import org.peaklog.model.dto.LoginDto;
import org.peaklog.model.entity.Usuario;
import org.peaklog.repository.UsuarioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
public class AuthController implements AuthControllerApi {

    private final UsuarioRepository usuarioRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    public ResponseEntity<Void> register(CreateUserDto createUserDTO) {
        Usuario user = new Usuario();
        user.setLogin(createUserDTO.getLogin());
        user.setNombre(createUserDTO.getName());
        user.setFechaNacimiento(LocalDate.parse(createUserDTO.getBirthDate()));
        user.setEmail(createUserDTO.getEmail());
        user.setPassword(passwordEncoder.encode(createUserDTO.getPassword()));
        usuarioRepository.save(user);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<String> login(LoginDto loginDTO) {
        var usuario = usuarioRepository.findByLogin(loginDTO.getLogin())
                .orElseThrow();

        if (!passwordEncoder.matches(loginDTO.getPassword(), usuario.getPassword())) {
            throw new RuntimeException("Credenciales incorrectas");
        }

        return ResponseEntity.ok(jwtService.generateToken(usuario.getLogin()));
    }
}
