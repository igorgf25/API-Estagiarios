package com.gft.api.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_starter")
public class Starter {
    @Id
    private String cpf;

    private String nome;

    private String quatro_letras;

    private String email;

    private Long categoria_id;

}
