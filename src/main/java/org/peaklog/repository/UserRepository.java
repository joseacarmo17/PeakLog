package org.peaklog.repository;

import java.util.Optional;
import org.peaklog.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findByLogin(String login);

  Optional<User> findByEmail(String email);
}
