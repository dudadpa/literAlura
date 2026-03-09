package com.eduarda.api_livros.service;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ConverteDados {

    private ObjectMapper mapper = new ObjectMapper();

    public <T> T obterDados(String json, Class<T> classe) {

        try {

            return mapper.readValue(json, classe);

        } catch (Exception e) {

            throw new RuntimeException(e);
        }
    }
}
