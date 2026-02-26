package org.peaklog.controller;

import lombok.RequiredArgsConstructor;
import org.peaklog.api.AuthControllerApi;
import org.peaklog.model.dto.CreateUserDTO;
import org.peaklog.model.dto.LoginDTO;
import org.peaklog.model.entity.Usuario;
import org.peaklog.repository.UsuarioRepository;
import org.peaklog.security.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController implements AuthControllerApi {

    private final UsuarioRepository usuarioRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    public ResponseEntity<String> register(CreateUserDTO createUserDTO) {
        Usuario user = new Usuario();
        user.setNombre(createUserDTO.getNombre());
        user.setFechaNacimiento(createUserDTO.getBirthDate());
        user.setPassword(passwordEncoder.encode(createUserDTO.getPassword()));
        usuarioRepository.save(user);

        return new ResponseEntity<>("Usuario creado exitosamente", null, 201);
    }

    @Override
    public ResponseEntity<String> login(LoginDTO loginDTO) {
        var usuario = usuarioRepository.findByLogin(loginDTO.getLogin())
                .orElseThrow();

        if (!passwordEncoder.matches(loginDTO.getPassword(), usuario.getPassword())) {
            throw new RuntimeException("Credenciales incorrectas");
        }

        return ResponseEntity.ok(jwtService.generateToken(usuario.getLogin()));
    }
}
