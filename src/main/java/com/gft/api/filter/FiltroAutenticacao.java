package com.gft.api.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gft.api.model.Usuario;
import com.gft.api.model.dto.oauth.DetalheUsuarioData;
import com.gft.api.service.EmailService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

public class FiltroAutenticacao extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    private final EmailService emailService;

    public FiltroAutenticacao(AuthenticationManager authenticationManager, EmailService emailService) {
        this.authenticationManager = authenticationManager;
        this.emailService = emailService;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            Usuario usuario = new ObjectMapper().readValue(request.getInputStream(), Usuario.class);
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(usuario.getUsuario()
                    , usuario.getSenha(), usuario.getRoles()));
        } catch (IOException e) {
            throw new RuntimeException("Falha ao autenticar usuario", e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        DetalheUsuarioData usuarioData = (DetalheUsuarioData) authResult.getPrincipal();

        Date hoje = new Date();
        Date dataExpiracao = new Date(hoje.getTime() + Long.parseLong("6000000"));

        String token = JWT.create().withSubject(usuarioData.getUsername())
                 .withExpiresAt(dataExpiracao)
                 .sign(Algorithm.HMAC512("bcc50eb0-b709-4fa5-bb7e-2ed5d7435922"));

        try {
            emailService.enviar(usuarioData.getEmail(), "Login efetuado",
                    "Login efetuado com sucesso ");
        } catch (Exception e) {

        }

         response.getWriter().write(token);
         response.getWriter().flush();
    }
}
