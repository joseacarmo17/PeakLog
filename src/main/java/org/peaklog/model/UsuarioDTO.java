package org.peaklog.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class UsuarioDTO {

    private Integer id;
    private String nombre;
    private String email;
}
