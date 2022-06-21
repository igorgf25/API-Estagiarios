package com.gft.api.service;

import br.com.caelum.stella.validation.CPFValidator;
import com.gft.api.exception.EntityAlreadyExist;
import com.gft.api.exception.EntityNotFoundException;
import com.gft.api.model.Starter;
import com.gft.api.model.dto.starter.ConsultaStarterDTO;
import com.gft.api.model.dto.starter.RegistroStarterDTO;
import com.gft.api.model.dto.starter.StarterMapper;
import com.gft.api.model.dto.starter.UpdateStarterDTO;
import com.gft.api.repository.StarterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StarterService {

    @Autowired
    StarterRepository starterRepository;

    @Autowired
    ImagemService imagemService;

    public List<ConsultaStarterDTO> buscarTodos(String foto) {
        return starterRepository.findAll().stream()
                .map(starter -> StarterMapper.fromEntityToConsultaFoto(starter, foto))
                .collect(Collectors.toList());
    }

    public List<ConsultaStarterDTO> buscarTodosAsc(String foto) {
        return starterRepository.findAllByOrderByNomeAsc().stream()
                .map(starter -> StarterMapper.fromEntityToConsultaFoto(starter, foto))
                .collect(Collectors.toList());
    }

    public List<ConsultaStarterDTO> buscarTodosDesc(String foto) {
        return starterRepository.findAllByOrderByNomeDesc().stream()
                .map(starter -> StarterMapper.fromEntityToConsultaFoto(starter, foto))
                .collect(Collectors.toList());
    }

    public ConsultaStarterDTO salvarStarter(RegistroStarterDTO starter) {
        Optional<Starter> testeCPF = starterRepository.findById(starter.getCpf());

        if (testeCPF.isPresent()) {
            throw new EntityAlreadyExist("CPF já registrado no banco de dados");
        }

        if (!validaCPF(starter.getCpf())) {
            throw new IllegalArgumentException("CPF inválido");
        }

        Starter starterRetorno = starterRepository.save(StarterMapper.fromRegistroToEntity(starter));
        ConsultaStarterDTO starterResposta = StarterMapper.fromEntityToConsulta(starterRetorno);
        return starterResposta;
    }

    public ConsultaStarterDTO salvarStarterComFoto(RegistroStarterDTO starter) {
        ConsultaStarterDTO retorno = salvarStarter(starter);

        imagemService.salvarImagen(starter.getFoto(), starter.getCpf());

        return retorno;
    }

    public ConsultaStarterDTO modificarStarter(UpdateStarterDTO starter) {
        Optional<Starter> consulta = starterRepository.findById(starter.getCpf());

        if(consulta.isEmpty()) {
            throw new EntityNotFoundException("Starter não encontrado");
        }

        Starter resposta = starterRepository.save(StarterMapper.fromUpdateToEntity(starter));

        return StarterMapper.fromEntityToConsulta(resposta);
    }

    public void excluirStarter(String cpf) {
        Optional<Starter> consulta = starterRepository.findById(cpf);

        if (consulta.isEmpty()) {
            throw new EntityNotFoundException("Starter não encontrado");
        }

        starterRepository.deleteById(cpf);
    }

    public ConsultaStarterDTO buscarStarter(String cpf) {
        Optional<Starter> consulta = starterRepository.findById(cpf);

        if (consulta.isEmpty()) {
            throw new EntityNotFoundException("Starter não encontrado");
        }

        return StarterMapper.fromEntityToConsulta(consulta.get());
    }

    private boolean validaCPF(String cpf) {
        CPFValidator cpfValidator = new CPFValidator();
        try {
            cpfValidator.assertValid(cpf);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    public List<ConsultaStarterDTO> buscarStarterPorNome(String nome) {
        return starterRepository.findAllByNomeStartsWithOrderByNomeAsc(nome).stream()
                .map(StarterMapper::fromEntityToConsulta)
                .collect(Collectors.toList());
    }


    public void alterarImagemStarter(MultipartFile foto, String cpf) {

        //testa se o starter existe, caso não exite o buscarStarter retorna uma excessão
        ConsultaStarterDTO teste = buscarStarter(cpf);

        try {
            imagemService.salvarImagen(foto, cpf);
        } catch (Exception e) {
            System.out.println("Deu BO" + e.getMessage());
        }
    }
}
