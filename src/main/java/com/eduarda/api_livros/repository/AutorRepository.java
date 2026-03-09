package com.eduarda.api_livros.repository;

import com.eduarda.api_livros.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AutorRepository extends JpaRepository<Autor, Long> {

    @Query("SELECT a FROM Autor a WHERE a.nascimento <= :ano AND (a.falecimento IS NULL OR a.falecimento >= :ano)")
    List<Autor> findAutoresVivosNoAno(int ano);
}
