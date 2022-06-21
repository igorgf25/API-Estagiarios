package com.gft.api.controller;

import com.gft.api.exception.InvalidDataException;
import com.gft.api.model.dto.categoria.ConsultaCategoriaDTO;
import com.gft.api.model.dto.categoria.RegistroCategoriaDTO;
import com.gft.api.service.CategoriaService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("v1/categoria")
public class CategoriaController {

    @Autowired
    CategoriaService categoriaService;

    /**
     * Retorna todas as categorias cadastradas
     *
     * @return uma lista de categorias utilizando a classe DTO ConsultaCategoriaDTO
     */
    @GetMapping
    @ApiOperation(value = "Lista todas as categorias cadastradas", notes = "Acessivel para usuarios e administradores")
    public ResponseEntity<List<ConsultaCategoriaDTO>> buscaTodasCategorias() {
        return ResponseEntity.ok(categoriaService.buscarTodos());
    }

    /**
     * Retorna uma categoria em especifico de acordo com o id passado
     *
     * @param id da categoria que deseja obter informações
     * @return uma categoria utilizando a classe DTO ConsultaCategoriaDTO
     */
    @GetMapping("{id}")
    @ApiOperation(value = "Retorna uma categoria",
            notes = "Retorna uma categoria de acordo com o id informado. " +
                    "Acessivel para usuarios e administradores")
    public ResponseEntity<ConsultaCategoriaDTO> buscaCategoria(@PathVariable Long id) {
        return ResponseEntity.ok(categoriaService.buscarCategoria(id));
    }

    /**
     * Salva a categoria passada no banco de dados
     *
     * @param categoria para ser cadastrada do no banco de dados
     * @return uma categoria utilizando a classe DTO ConsultaCategoriaDTO
     */
    @PostMapping
    @ApiOperation(value = "Cadastra uma nova categoria",
            notes = "Cadastra uma nova categoria no banco de dados de acordo com as informações passadas." +
                    " Acessivel somente para administradores")
    public ResponseEntity<ConsultaCategoriaDTO> criaCategoria(@Valid @RequestBody RegistroCategoriaDTO categoria, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new InvalidDataException("Por favor preencha todos os campos");
        }

        return new ResponseEntity(categoriaService.salvarCategoria(categoria), HttpStatus.CREATED);
    }

    /**
     * Altera uma categoria já salva de acordo com o id informado
     *
     * @param categoria as informações que devem ser alteradas na entidade já salva no banco de dados
     * @param id        da categoria que deseja alterar
     * @return a categoria já atualizada utilizando a classe DTO ConsultaCategoriaDTO
     */
    @PutMapping("{id}")
    @ApiOperation(value = "Altera uma categoria",
            notes = "Altera uma categoria já previamente cadastrada de acordo com o id e os dados informados" +
                    ". Acessivel somente para administradores")
    public ResponseEntity<ConsultaCategoriaDTO> alterarCategoria(@Valid @RequestBody RegistroCategoriaDTO categoria, BindingResult bindingResult, @PathVariable Long id) {
        if (bindingResult.hasErrors()) {
            throw new InvalidDataException("Por favor preencha todos os campos");
        }

        return ResponseEntity.ok(categoriaService.modificarCategoria(categoria, id));
    }

    /**
     * Exclui uma categoria de acordo com o seu id
     *
     * @param id da categoria que deseja exluir
     * @return a categoria excluida utilizando a classe DTO ConsultaCategoriaDTO
     */
    @ApiOperation(value = "Apaga uma categoria",
            notes = "Apaga uma categoria de acorodo como id informado. " +
                    "Acessivel somente para administradores")
    @DeleteMapping("{id}")
    public ResponseEntity<ConsultaCategoriaDTO> excluirCategoria(@PathVariable Long id) {
        categoriaService.excluirCategoria(id);
        return ResponseEntity.ok().build();
    }

}
