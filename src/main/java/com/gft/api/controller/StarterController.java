package com.gft.api.controller;

import com.gft.api.exception.InvalidDataException;
import com.gft.api.model.dto.starter.ConsultaStarterDTO;
import com.gft.api.model.dto.starter.RegistroStarterDTO;
import com.gft.api.model.dto.starter.UpdateStarterDTO;
import com.gft.api.service.StarterService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("v1/starter")
public class StarterController {

    @Autowired
    StarterService starterService;

    /**
     * Retorna todas os estagiários cadastrados
     *
     * @return uma lista de estagiários utilizando a classe DTO ConsultaStarterDTO
     */
    @GetMapping("{foto}")
    @ApiOperation(value = "Lista todas os estagiários cadastradas", notes = "Coloque sim no parametro foto para que seja retornado" +
            " também a imagem e não para que não seja retornado. Acessivel para usuarios e administradores")
    public ResponseEntity<List<ConsultaStarterDTO>> buscarTodosStarters(@PathVariable String foto) {
        return ResponseEntity.ok(starterService.buscarTodos(foto));
    }

    /**
     * Retorna todas os estagiários cadastrados em ordem crescente de acordo com seu nome
     *
     * @return uma lista de estagiários utilizando a classe DTO ConsultaStarterDTO
     */
    @GetMapping("asc/{foto}")
    @ApiOperation(value = "Lista todas os estagiarios cadastradas ordenado por nome ",
            notes = "Lista todas os starters cadastradas ordenado por nome em ordem crescente." +
                    " Coloque sim no parametro foto para que seja retornado também a imagem e não para que não seja retornado." +
                    "Acessivel para usuarios e administradores")
    public ResponseEntity<List<ConsultaStarterDTO>> buscarTodosStartersAsc(@PathVariable String foto) {
        return ResponseEntity.ok(starterService.buscarTodosAsc(foto));
    }

    /**
     * Retorna todas os estagiários cadastrados em ordem decrescente de acordo com seu nome
     *
     * @return uma lista de estagiários utilizando a classe DTO ConsultaStarterDTO
     */
    @GetMapping("desc/{foto}")
    @ApiOperation(value = "Lista todas os estagiários cadastradas ordenado por nome ",
            notes = "Lista todas os estagiários cadastradas ordenado por nome em ordem crescente." +
                    "Coloque sim no parametro foto para que seja retornado também a imagem e não para que não seja retornado." +
                    " Acessivel para usuarios e administradores")
    public ResponseEntity<List<ConsultaStarterDTO>> buscarTodosStartersDesc(@PathVariable String foto) {
        return ResponseEntity.ok(starterService.buscarTodosDesc(foto));
    }

    /**
     * Retorna um estagiário em especifico de acordo com o cpf informado
     *
     * @param cpf do estagiário que deseja obter informações
     * @return um starter utilizando a classe DTO ConsultaStarterDTO
     */
    @GetMapping("cpf/{cpf}")
    @ApiOperation(value = "Retorna um estagiário",
            notes = "Retorna um estagiário de acordo com o cpf informado. " +
                    "Acessivel para usuarios e administradores")
    public ResponseEntity<ConsultaStarterDTO> buscarStarter(@PathVariable String cpf) {
        return ResponseEntity.ok(starterService.buscarStarter(cpf));
    }

    /**
     * Retorna os estagiário de acordo com o nome informado
     *
     * @param nome do estagiário que deseja obter informações
     * @return estagiário utilizando a classe DTO ConsultaStarterDTO
     */
    @GetMapping("nome/{nome}")
    @ApiOperation(value = "Retorna um estagiário",
            notes = "Retorna um estagiário de acordo com o cpf informado. " +
                    "Acessivel para usuarios e administradores")
    public ResponseEntity<List<ConsultaStarterDTO>> buscarStarterPorNome(@PathVariable String nome) {
        return ResponseEntity.ok(starterService.buscarStarterPorNome(nome));
    }

    /**
     * Salva um estagiário no banco de dados
     *
     * @param starter para ser cadastrado no banco de dados
     * @return um estagiário utilizando a classe DTO ConsultaStarterDTO
     */
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ApiOperation(value = "Cadastra um estagiário",
            notes = "Cadastra um estagiário no banco de dados de acordo com as informações passadas." +
                    " Acessivel somente para administradores")
    public ResponseEntity<ConsultaStarterDTO> criaStarter(@Valid @ModelAttribute RegistroStarterDTO starter, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new InvalidDataException("Por favor preencha todos os campos");
        }

        if (starter.getFoto() != null) {
            return ResponseEntity.ok(starterService.salvarStarterComFoto(starter));
        }
        return ResponseEntity.ok(starterService.salvarStarter(starter));
    }

    /**
     * Altera uma estagiário já salva de acordo com o id informado
     *
     * @param starter as informações que devem ser alteradas na entidade já salva no banco de dados
     * @return o estagiário já atualizado utilizando a classe DTO ConsultaStarterDTO
     */
    @PutMapping()
    @ApiOperation(value = "Altera um estagiário",
            notes = "Altera um estagiário já previamente cadastrada de acordo com o id e os dados informados" +
                    ". Acessivel somente para administradores")
    public ResponseEntity<ConsultaStarterDTO> alterarStarter(@Valid @ModelAttribute UpdateStarterDTO starter, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new InvalidDataException("Por favor preencha todos os campos");
        }
        return ResponseEntity.ok(starterService.modificarStarter(starter));
    }


    /**
     * @param foto que deseja que seja salva
     * @param cpf  do estagiário que deseja alterar a foto
     * @return um ResponseEntity contendo a confirmação da alteração
     */
    @ApiOperation(value = "Altera foto de um estagiário",
            notes = "Altera a imagem de um estagiário de acordo com as informações passadas. Acessivel somente para administradores")
    @PutMapping("foto/{cpf}")
    public ResponseEntity<String> alterarFotoStarter(@RequestPart(required = true) MultipartFile foto, @PathVariable String cpf) {
        starterService.alterarImagemStarter(foto, cpf);
        return ResponseEntity.ok("Foto alterada com sucesso");
    }

    /**
     * Exclui um estagiário de acordo com o seu cpf
     *
     * @param cpf do estagiário que deseja exluir
     * @return o estagiário exluido utilizando a classe DTO ConsultaStarterDTO
     */
    @DeleteMapping("{cpf}")
    @ApiOperation(value = "Exclui um estagiário",
            notes = "Exclui um estagiário de acordo com o cpf informado. Acessivel somente para administradores")
    public ResponseEntity<ConsultaStarterDTO> excluirStarter(@PathVariable String cpf) {
        starterService.excluirStarter(cpf);
        return ResponseEntity.ok().build();
    }

}
