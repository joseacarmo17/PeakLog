package org.peaklog.service.impl;

import lombok.RequiredArgsConstructor;
import org.peaklog.repository.UserRepository;
import org.peaklog.service.UsuarioService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {
    private final UserRepository userRepository;


//    @Override
//    public UsuarioListDTO findAll() {
//        return null;
//    }
//
//    @Override
//    public UsuarioDTO findUserById(Integer id) {
//        return null;
//    }
}
