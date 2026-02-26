package org.peaklog.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class CreateUserDTO {
    private String nombre;
    private LocalDate birthDate;
    private String email;
    private String password;
}
