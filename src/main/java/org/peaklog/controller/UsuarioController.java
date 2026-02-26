package org.peaklog.controller;

import org.peaklog.api.UsuarioControllerApi;
import org.peaklog.model.dto.UsuarioDTO;
import org.peaklog.model.dto.UsuarioListDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsuarioController implements UsuarioControllerApi {

    @Override
    public ResponseEntity<UsuarioDTO> findUserById(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<UsuarioListDTO> findAllUsers() {
        return null;
    }
}

