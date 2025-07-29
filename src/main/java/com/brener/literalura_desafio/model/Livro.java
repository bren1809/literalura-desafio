package com.brener.literalura_desafio.model;

import jakarta.persistence.*;

@Entity
@Table(name = "livros")
public class Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String titulo;

    private String idioma;
    private Integer numeroDownloads;

    @ManyToOne(cascade = CascadeType.ALL)
    private Autor autor;

    public Livro() {}

    public Livro(DadosLivro dadosLivro) {
        this.titulo = dadosLivro.titulo();
        this.idioma = String.join(", ", dadosLivro.idiomas());
        this.numeroDownloads = dadosLivro.numeroDownloads();
    }

    @Override
    public String toString() {
        String nomeAutor = (autor != null) ? autor.getNome() : "Desconhecido";
        return "------ LIVRO ------\n" +
                " Título: " + titulo + "\n" +
                " Autor: " + nomeAutor + "\n" +
                " Idioma: " + idioma + "\n" +
                " Downloads: " + numeroDownloads + "\n" +
                "-------------------\n";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public Integer getNumeroDownloads() {
        return numeroDownloads;
    }

    public void setNumeroDownloads(Integer numeroDownloads) {
        this.numeroDownloads = numeroDownloads;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }
}
