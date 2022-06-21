package com.gft.api.model.dto.usuario;

import com.gft.api.model.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ConsultaUsuarioDTO {
    private Long id;

    private String usuario;

    private String senha;

    private String email;

    private List<Role> roles;
}
