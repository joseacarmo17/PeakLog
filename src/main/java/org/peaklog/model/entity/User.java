package org.peaklog.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "USERS", schema = "AUTH")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID", nullable = false, updatable = false)
  private Integer id;

  @Column(name = "LOGIN", nullable = false, unique = true)
  private String login;

  @Column(name = "NAME", nullable = false)
  private String name;

  @Column(name = "EMAIL", nullable = false, unique = true)
  private String email;

  @Column(name = "BIRTHDATE", nullable = false)
  private LocalDate birthDate;

  @Column(name = "PASSWORD", nullable = false)
  private String password; // Siempre hasheada (BCrypt)

  @Column(name = "CREATED_AT", nullable = false, updatable = false)
  private LocalDateTime createdAt;

  @PrePersist
  void prePersist() {
    createdAt = LocalDateTime.now();
  }
}
