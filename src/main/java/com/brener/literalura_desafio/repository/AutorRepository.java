package com.brener.literalura_desafio.repository;

import com.brener.literalura_desafio.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AutorRepository extends JpaRepository<Autor, Long> {
    Optional<Autor> findByNomeContainingIgnoreCase(String nome);

    @Query("SELECT a FROM Autor a WHERE a.anoFalecimento >= :ano AND a.anoNascimento <= :ano")
    List<Autor> findAutoresVivosEmAno(Integer ano);
}
