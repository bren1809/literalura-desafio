package com.brener.literalura_desafio.principal;

import com.brener.literalura_desafio.model.Autor;
import com.brener.literalura_desafio.model.DadosLivro;
import com.brener.literalura_desafio.model.DadosResultados;
import com.brener.literalura_desafio.model.Livro;
import com.brener.literalura_desafio.repository.AutorRepository;
import com.brener.literalura_desafio.repository.LivroRepository;
import com.brener.literalura_desafio.service.ConsumoApiService;
import com.brener.literalura_desafio.service.ConverteDados;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Principal {
    private final Scanner scanner = new Scanner(System.in);
    private final ConsumoApiService consumoApi = new ConsumoApiService();
    private final ConverteDados conversor = new ConverteDados();
    private final String ENDERECO_BASE = "https://gutendex.com/books/";
    private final LivroRepository livroRepository;
    private final AutorRepository autorRepository;

    public Principal(LivroRepository livroRepository, AutorRepository autorRepository) {
        this.livroRepository = livroRepository;
        this.autorRepository = autorRepository;
    }

    public void exibirMenuPrincipal() {
        var opcao = -1;
        while (opcao != 0) {
            imprimirMenuDeOpcoes();
            try {
                opcao = Integer.parseInt(scanner.nextLine());
                processarOpcao(opcao);
            } catch (NumberFormatException e) {
                System.out.println("Erro: Entrada inválida. Por favor, digite apenas o número da opção.");
            }
        }
    }

    private void imprimirMenuDeOpcoes() {
        System.out.println("\n--- MENU ---");
        System.out.println("1 - Buscar livro por título");
        System.out.println("2 - Listar livros registrados");
        System.out.println("3 - Listar autores registrados");
        System.out.println("4 - Listar autores vivos em um determinado ano");
        System.out.println("5 - Listar livros em um determinado idioma");
        System.out.println("0 - Sair");
        System.out.print("Escolha uma opção: ");
    }

    private void processarOpcao(int opcao) {
        switch (opcao) {
            case 1:
                processarBuscaDeLivroPorTitulo();
                break;
            case 2:
                processarListagemDeLivrosRegistrados();
                break;
            case 3:
                processarListagemDeAutoresRegistrados();
                break;
            case 4:
                processarListagemDeAutoresVivosPorAno();
                break;
            case 5:
                processarListagemDeLivrosPorIdioma();
                break;
            case 0:
                System.out.println("Encerrando a aplicação...");
                break;
            default:
                System.out.println("Opção inválida! Por favor, escolha um número do menu.");
        }
    }


    private void processarBuscaDeLivroPorTitulo() {
        System.out.print("Digite o nome do livro para busca: ");
        var nomeLivro = scanner.nextLine();

        Optional<Livro> livroExistente = livroRepository.findByTituloContainsIgnoreCase(nomeLivro);
        if (livroExistente.isPresent()) {
            System.out.println("Este livro já está cadastrado no banco de dados!");
            imprimirDetalhesDoLivro(livroExistente.get());
            return;
        }

        String urlBusca = ENDERECO_BASE + "?search=" + nomeLivro.replace(" ", "+");
        try {
            System.out.println("Buscando...");
            String json = consumoApi.obterDados(urlBusca);
            DadosResultados dadosResultados = conversor.obterDados(json, DadosResultados.class);
            Optional<DadosLivro> dadosLivroOpt = dadosResultados.livros().stream().findFirst();

            if (dadosLivroOpt.isPresent()) {
                DadosLivro dadosLivro = dadosLivroOpt.get();
                Livro novoLivro = new Livro(dadosLivro);
                if (!dadosLivro.autores().isEmpty()) {
                    Autor autor = new Autor(dadosLivro.autores().get(0));
                    novoLivro.setAutor(autor);
                }

                livroRepository.save(novoLivro);
                System.out.println("Livro salvo com sucesso!");
                imprimirDetalhesDoLivro(novoLivro);
            } else {
                System.out.println("Nenhum livro encontrado com o título '" + nomeLivro + "' na API.");
            }
        } catch (RuntimeException e) {
            System.out.println("Ocorreu um erro durante a busca: " + e.getMessage());
        }
    }

    private void processarListagemDeLivrosRegistrados() {
        List<Livro> livros = livroRepository.findAll();

        if (livros.isEmpty()) {
            System.out.println("Nenhum livro registrado no banco de dados.");
        } else {
            System.out.println("\n--- LISTA DE LIVROS REGISTRADOS ---");
            livros.forEach(System.out::println);
        }
    }

    private void processarListagemDeAutoresRegistrados() {
        List<Autor> autores = autorRepository.findAll();

        if (autores.isEmpty()) {
            System.out.println("Nenhum autor registrado no banco de dados.");
        } else {
            System.out.println("\n--- LISTA DE AUTORES REGISTRADOS ---");
            autores.forEach(System.out::println);
        }
    }

    private void processarListagemDeAutoresVivosPorAno() {
        System.out.println("Digite o ano que deseja pesquisar: ");
        try {
            var ano = Integer.parseInt(scanner.nextLine());
            List<Autor> autoresVivos = autorRepository.findAutoresVivosEmAno(ano);

            if (autoresVivos.isEmpty()) {
                System.out.println("Nenhum autor vivo encontrado para o ano de " + ano + ".");
            } else {
                System.out.println("\n--- AUTORES VIVOS NO ANO DE " + ano + "---");
                autoresVivos.forEach(System.out::println);
            }
        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida. Por favor, digite um ano válido (números).");
        }
    }

    private void processarListagemDeLivrosPorIdioma() {
        System.out.println("Escolha um dos idiomas para a busca:");
        System.out.println("es - espanhol");
        System.out.println("en - inglês");
        System.out.println("fr - francês");
        System.out.println("pt - português");
        System.out.print("Digite o código do idioma: ");

        var codigoIdioma = scanner.nextLine();

        List<Livro> livrosPorIdioma = livroRepository.findByIdiomaContainingIgnoreCase(codigoIdioma);

        if (livrosPorIdioma.isEmpty()) {
            System.out.println("Nenhum livro encontrado para o idioma '" + codigoIdioma + "'.");
        } else {
            System.out.println("\n--- LIVROS ENCONTRADOS NO IDIOMA '" + codigoIdioma + "' ---");
            livrosPorIdioma.forEach(System.out::println);
        }
    }

    private void imprimirDetalhesDoLivro(Livro livro) {
        System.out.println("\n--- DETALHES DO LIVRO ---");
        System.out.println("Título: " + livro.getTitulo());
        if (livro.getAutor() != null) {
            System.out.println("Autor: " + livro.getAutor().getNome());
        } else {
            System.out.println("Autor: Desconhecido");
        }
        System.out.println("Idioma: " + String.join(", ", livro.getIdioma()));
        System.out.println("Número de Downloads: " + livro.getNumeroDownloads());
        System.out.println("------------------------\n");
    }
}