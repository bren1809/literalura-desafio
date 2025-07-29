# 📚 LiterAlura - Catálogo de Livros

![Java](https://img.shields.io/badge/Java-17+-orange?style=for-the-badge&logo=openjdk)
![Spring](https://img.shields.io/badge/Spring_Boot-3.x-green?style=for-the-badge&logo=spring)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-13+-blue?style=for-the-badge&logo=postgresql)
![Maven](https://img.shields.io/badge/Maven-4.0.0-red?style=for-the-badge&logo=apache-maven)

## ✨ Descrição do Projeto

**LiterAlura** é uma aplicação de console desenvolvida como parte do desafio de programação da Alura. O projeto consiste em um catálogo de livros interativo que consome a API pública [Gutendex](https://gutendex.com/) para obter informações sobre livros e autores, permitindo ao usuário pesquisar, listar e salvar esses dados em um banco de dados local PostgreSQL.

A aplicação foi construída utilizando as melhores práticas de desenvolvimento com o framework Spring, incluindo o uso de Spring Data JPA para persistência de dados e uma arquitetura bem encapsulada para facilitar a manutenção e escalabilidade.

## 🚀 Funcionalidades Principais

O menu interativo oferece as seguintes opções:

1.  **Buscar livro por título:** Pesquisa um livro na API Gutendex. Se encontrado, salva o livro e seu autor no banco de dados local para consultas futuras.
2.  **Listar livros registrados:** Exibe todos os livros que foram salvos no banco de dados.
3.  **Listar autores registrados:** Exibe todos os autores únicos que foram salvos no banco.
4.  **Listar autores vivos em um determinado ano:** Pede um ano ao usuário e exibe uma lista de autores do banco que estavam vivos naquele ano.
5.  **Listar livros em um determinado idioma:** Pede um código de idioma (es, en, fr, pt) e exibe os livros registrados naquele idioma.
6.  **Sair:** Encerra a aplicação.

## 🛠️ Tecnologias Utilizadas

-   **Java 17:** Linguagem principal do projeto.
-   **Spring Boot:** Framework principal para criar a aplicação standalone.
-   **Spring Data JPA:** Para facilitar a persistência e consulta de dados no banco.
-   **PostgreSQL:** Banco de dados relacional para armazenar os catálogos.
-   **Hibernate:** Implementação do JPA utilizada pelo Spring.
-   **Jackson:** Biblioteca para desserialização do JSON vindo da API.
-   **Maven:** Gerenciador de dependências e build do projeto.

## ⚙️ Pré-requisitos

Antes de começar, você vai precisar ter instalado em sua máquina:

-   [JDK 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html) ou superior.
-   [Apache Maven](https://maven.apache.org/download.cgi) 3.8 ou superior.
-   [PostgreSQL](https://www.postgresql.org/download/) 13 ou superior.
-   Um cliente Git para clonar o repositório.

👨‍💻 Autor
Projeto desenvolvido por Brener Brito.
