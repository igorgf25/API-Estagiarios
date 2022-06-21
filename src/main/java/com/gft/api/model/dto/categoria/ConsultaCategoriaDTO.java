package com.gft.api.model.dto.categoria;

import com.gft.api.model.Starter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ConsultaCategoriaDTO {
    private Long id;

    private String tecnologia;

    private String nome;

    private List<Starter> starters;
}
