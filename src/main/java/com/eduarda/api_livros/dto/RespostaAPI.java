package com.eduarda.api_livros.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record RespostaAPI(
        @JsonAlias("results")
        List<DadosLivro> resultados
){}
