package com.gft.api.model.dto.starter;

import com.gft.api.model.Starter;
import com.gft.api.service.ImagemService;

public class StarterMapper {

    public static ConsultaStarterDTO fromEntityToConsultaFoto(Starter starter, String decisaoFoto) {
        byte[] foto;

        if (decisaoFoto.equalsIgnoreCase("Sim")) {
            foto = ImagemService.buscarImagem(starter.getCpf());
        } else {
            foto = null;
        }
        return new ConsultaStarterDTO(starter.getCpf(), starter.getNome(), starter.getQuatro_letras(),
                starter.getEmail(), starter.getCategoria_id(), foto);
    }

    public static ConsultaStarterDTO fromEntityToConsulta(Starter starter) {
        return new ConsultaStarterDTO(starter.getCpf(), starter.getNome(), starter.getQuatro_letras(),
                starter.getEmail(), starter.getCategoria_id(), ImagemService.buscarImagem(starter.getCpf()));
    }

    public static Starter fromRegistroToEntity(RegistroStarterDTO starter) {
        return new Starter(starter.getCpf(), starter.getNome(), starter.getQuatro_letras(),
                starter.getEmail(), starter.getCategoria_id());
    }

    public static Starter fromUpdateToEntity(UpdateStarterDTO starter) {
        return new Starter(starter.getCpf(), starter.getNome(), starter.getQuatro_letras(),
                starter.getEmail(), starter.getCategoria_id());
    }
}
