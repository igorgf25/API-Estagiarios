package com.gft.api.controller;

import com.gft.api.model.dto.usuario.Login;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "Login")
public class LoginController {

    //Controller criado para que seja gerado no swagger o caminho de login
    @PostMapping(path = "login")
    @ApiOperation(value = "Login de usuario",
            notes = "Endpoint criado para a realização do login e a geração do token de autenticação")
    public String fakeLogin(@RequestBody Login fakeLoginDTO){
        return null;
    }
}
