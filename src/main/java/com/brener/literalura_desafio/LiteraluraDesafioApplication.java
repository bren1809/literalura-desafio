package com.brener.literalura_desafio;

import com.brener.literalura_desafio.principal.Principal;
import com.brener.literalura_desafio.repository.AutorRepository;
import com.brener.literalura_desafio.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiteraluraDesafioApplication implements CommandLineRunner {

	@Autowired
	private LivroRepository livroRepository;

	@Autowired
	private AutorRepository autorRepository;

	public static void main(String[] args) {
		SpringApplication.run(LiteraluraDesafioApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal(livroRepository, autorRepository);
		principal.exibirMenuPrincipal();
	}
}
