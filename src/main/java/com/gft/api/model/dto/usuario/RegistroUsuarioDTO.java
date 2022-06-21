package com.gft.api.model.dto.usuario;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegistroUsuarioDTO {
    @NotBlank
    private String usuario;

    @NotBlank
    private String senha;

    @NotBlank
    private String email;
}
