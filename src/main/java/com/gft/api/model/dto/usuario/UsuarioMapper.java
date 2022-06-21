package com.gft.api.model.dto.usuario;

import com.gft.api.model.Usuario;
import com.gft.api.repository.RoleRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

public class UsuarioMapper {

    static BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public static Usuario fromRegistroToUsuario(RegistroUsuarioDTO usuarioDTO, RoleRepository roleRepository) {
        return new Usuario(usuarioDTO.getUsuario(), encoder.encode(usuarioDTO.getSenha()),
                usuarioDTO.getEmail(), List.of(roleRepository.findById(Long.parseLong("2")).get()));
    }

    public static ConsultaUsuarioDTO fromEntityToConsulta(Usuario usuario) {
        return new ConsultaUsuarioDTO(usuario.getId(), usuario.getUsuario(), usuario.getSenha(),
                usuario.getEmail(), usuario.getRoles());
    }
}
