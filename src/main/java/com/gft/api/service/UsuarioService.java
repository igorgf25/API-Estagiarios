package com.gft.api.service;

import com.gft.api.exception.EntityAlreadyExist;
import com.gft.api.model.Usuario;
import com.gft.api.model.dto.usuario.ConsultaUsuarioDTO;
import com.gft.api.model.dto.usuario.RegistroUsuarioDTO;
import com.gft.api.model.dto.usuario.UsuarioMapper;
import com.gft.api.repository.RoleRepository;
import com.gft.api.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    RoleRepository roleRepository;

    public Usuario buscaUsuarioPorLogin(String login) {
        Optional<Usuario> usuario = usuarioRepository.findByUsuario(login);

        if (usuario.isEmpty()) {
            throw new EntityNotFoundException("Entidade não encontrada");
        }

        return usuario.get();
    }

    public ConsultaUsuarioDTO salvarUsuario(RegistroUsuarioDTO usuario) {

        Optional<Usuario> teste = usuarioRepository.findByUsuario(usuario.getUsuario());

        if (teste.isPresent()) {
            throw new EntityAlreadyExist("Usuario já existe");
        }

        Usuario usuarioVolta = usuarioRepository.save(UsuarioMapper.fromRegistroToUsuario(usuario, roleRepository));
        return UsuarioMapper.fromEntityToConsulta(usuarioVolta);
    }
}
