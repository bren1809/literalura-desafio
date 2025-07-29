package com.brener.literalura_desafio.repository;

import com.brener.literalura_desafio.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LivroRepository extends JpaRepository<Livro, Long> {
    Optional<Livro> findByTituloContainsIgnoreCase(String titulo);
}
