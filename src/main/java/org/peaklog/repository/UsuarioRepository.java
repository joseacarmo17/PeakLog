package org.peaklog.repository;

import org.peaklog.model.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository  extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByLogin(String login);

    Optional<Usuario> findByEmail(String email);

}
