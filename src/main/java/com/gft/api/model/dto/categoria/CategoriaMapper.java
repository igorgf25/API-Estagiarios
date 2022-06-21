package com.gft.api.model.dto.categoria;

import com.gft.api.model.Categoria;

public class CategoriaMapper {
    public static ConsultaCategoriaDTO fromEntityToConsulta(Categoria categoria) {
        return new ConsultaCategoriaDTO(categoria.getId(), categoria.getTecnologia(), categoria.getNome(), categoria.getStarters());
    }

    public static Categoria fromRegistroToEntity(RegistroCategoriaDTO categoria) {
        return new Categoria(categoria.getTecnologia(), categoria.getNome());
    }
}
