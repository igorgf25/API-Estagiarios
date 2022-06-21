package com.gft.api.service;

import com.gft.api.exception.EntityNotFoundException;
import com.gft.api.model.Categoria;
import com.gft.api.model.dto.categoria.CategoriaMapper;
import com.gft.api.model.dto.categoria.ConsultaCategoriaDTO;
import com.gft.api.model.dto.categoria.RegistroCategoriaDTO;
import com.gft.api.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoriaService {

    @Autowired
    CategoriaRepository categoriaRepository;

    /**
     *
     * @return
     */
    public List<ConsultaCategoriaDTO> buscarTodos() {
        return categoriaRepository.findAll().stream().map(CategoriaMapper::fromEntityToConsulta).collect(Collectors.toList());
    }

    /**
     *
     * @param categoria
     * @return
     */
    public ConsultaCategoriaDTO salvarCategoria(RegistroCategoriaDTO categoria) {
        Categoria cateriaVolta = categoriaRepository.save(CategoriaMapper.fromRegistroToEntity(categoria));
        ConsultaCategoriaDTO cateriaResposta = CategoriaMapper.fromEntityToConsulta(cateriaVolta);

        return cateriaResposta;
    }

    public ConsultaCategoriaDTO modificarCategoria(RegistroCategoriaDTO categoria, Long id) {
        Optional<Categoria> consulta = categoriaRepository.findById(id);

        if (consulta.isEmpty()) {
            throw new EntityNotFoundException("Categoria não encontrada");
        }

        consulta.get().setNome(categoria.getNome());
        consulta.get().setTecnologia(categoria.getTecnologia());

        categoriaRepository.save(consulta.get());

        return CategoriaMapper.fromEntityToConsulta(consulta.get());
    }

    public void excluirCategoria(Long id) {
        Optional<Categoria> consulta = categoriaRepository.findById(id);

        if (consulta.isEmpty()) {
            throw new EntityNotFoundException("Categoria não encontrada");
        }

        categoriaRepository.deleteById(id);
    }

    public ConsultaCategoriaDTO buscarCategoria(Long id) {
        Optional<Categoria> consulta = categoriaRepository.findById(id);

        if (consulta.isEmpty()) {
            throw new EntityNotFoundException("Categoria não encontrada");
        }

        return CategoriaMapper.fromEntityToConsulta(consulta.get());
    }
}
