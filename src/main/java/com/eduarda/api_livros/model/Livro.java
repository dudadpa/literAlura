package com.eduarda.api_livros.model;

import com.eduarda.api_livros.dto.DadosLivro;
import jakarta.persistence.*;

@Entity
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String idioma;
    private Integer downloads;

    @ManyToOne
    private Autor autor;

    public Livro() {}

    public Livro(DadosLivro dados, Autor autor) {
        this.titulo = dados.titulo();
        this.idioma = dados.idiomas().get(0);
        this.downloads = dados.downloads();
        this.autor = autor;
    }

    public String toString() {
        return titulo + " - " + autor;
    }
}
