package com.gft.api.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class ImagemService {

    public void salvarImagen(MultipartFile foto, String cpf) {

        StringBuilder fileNames = new StringBuilder();
        Path fileNameAndPath = Paths.get("src/main/resources/static/imagens", cpf+".png");
        fileNames.append(cpf+".png" + " ");
        try {
            Files.write(fileNameAndPath, foto.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public static byte[] buscarImagem(String cpf) {

        Path fileNameAndPath = Paths.get("src/main/resources/static/imagens", cpf+".png");

        try {
            return Files.readAllBytes(fileNameAndPath);
        } catch (Exception e) {
            return null;
        }
    }
}
