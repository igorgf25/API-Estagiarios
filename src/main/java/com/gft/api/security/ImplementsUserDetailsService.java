package com.gft.api.security;

import com.gft.api.model.Usuario;
import com.gft.api.model.dto.oauth.DetalheUsuarioData;
import com.gft.api.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class ImplementsUserDetailsService implements UserDetailsService{

    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> usuario = usuarioRepository.findByUsuario(username);

        if(usuario.isEmpty()) {
            throw new UsernameNotFoundException("Usuario não encontrado!");
        }
        return new DetalheUsuarioData(usuario);

    }

    public Usuario buscarUsuarioPorId(Long idUsuario) {
        Optional<Usuario> optional = usuarioRepository.findById(idUsuario);

        if(optional.isEmpty()) {
            throw new RuntimeException("Usuário não encontrado");
        }

        return optional.get();

    }


    public Usuario salvarUsuario(Usuario usuario) {

        return usuarioRepository.save(usuario);

    }
}
