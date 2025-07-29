package com.brener.literalura_desafio.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosResultados(
        @JsonAlias("results") List<DadosLivro> livros
) {
}
