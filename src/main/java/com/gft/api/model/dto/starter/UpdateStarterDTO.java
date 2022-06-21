package com.gft.api.model.dto.starter;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateStarterDTO {
    @NotBlank
    private String cpf;

    @NotBlank
    private String nome;

    @NotBlank
    private String quatro_letras;

    @NotBlank
    private String email;

    @NotNull
    @Min(value = 1)
    private Long categoria_id;
}
