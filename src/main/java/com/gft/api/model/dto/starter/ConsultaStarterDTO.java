package com.gft.api.model.dto.starter;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ConsultaStarterDTO {
    private String cpf;

    private String nome;

    private String quatro_letras;

    private String email;

    private Long categoria_id;

    private byte[] foto;
}
