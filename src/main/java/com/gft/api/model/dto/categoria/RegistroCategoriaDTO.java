package com.gft.api.model.dto.categoria;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegistroCategoriaDTO {
    @NotBlank
    private String tecnologia;

    @NotBlank
    private String nome;
}
