package com.eduarda.api_livros.repository;

import com.eduarda.api_livros.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface LivroRepository  extends JpaRepository<Livro, Long> {

    List<Livro> findByIdioma(String idioma);

    long countByIdioma(String idioma);
}
