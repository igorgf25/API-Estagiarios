package com.gft.api.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_categoria")
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tecnologia;

    private String nome;

    @OneToMany
    @JoinColumn(name = "categoria_id")
    private List<Starter> starters;

    public Categoria(String tecnologia, String nome) {
        this.tecnologia = tecnologia;
        this.nome = nome;
    }
}
