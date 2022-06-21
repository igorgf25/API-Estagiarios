package com.gft.api.controller;

import com.gft.api.exception.InvalidDataException;
import com.gft.api.model.dto.usuario.ConsultaUsuarioDTO;
import com.gft.api.model.dto.usuario.RegistroUsuarioDTO;
import com.gft.api.service.UsuarioService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("v1/usuario")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    /**
     * Salva um usuario no banco de dados
     * @param usuario para ser cadastrado no banco de dados
     * @return um usuario utilizando a classe DTO ConsultaUsuarioDTO
     */
    @PostMapping
    @ApiOperation(value = "Cadastra uma nova categoria",
            notes = "Cadastra uma nova categoria no banco de dados de acordo com as informações passadas." +
                    " Acessivel somente para administradores")
    public ResponseEntity<ConsultaUsuarioDTO> criarUsuario(@Valid @RequestBody RegistroUsuarioDTO usuario, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new InvalidDataException("Por favor preencha todos os campos");
        }

        return new ResponseEntity(usuarioService.salvarUsuario(usuario), HttpStatus.CREATED);
    }

}
