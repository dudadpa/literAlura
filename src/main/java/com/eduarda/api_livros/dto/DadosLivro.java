package com.eduarda.api_livros.dto;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosLivro(

        @JsonAlias("title") String titulo,
        @JsonAlias("languages")List<String> idiomas,
        @JsonAlias("download_count") Integer downloads,
        @JsonAlias("authors") List<DadosAutor> autores

) {}
