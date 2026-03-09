package com.eduarda.api_livros.model;

import com.eduarda.api_livros.dto.DadosAutor;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private Integer nascimento;
    private Integer falecimento;

    public Autor() {}

    public Autor(DadosAutor dados) {
        this.nome = dados.nome();
        this.nascimento = dados.nascimento();
        this.falecimento = dados.falecimento();
    }

    @Override
    public String toString() {
        return "Autor: " + nome;
    }
}
