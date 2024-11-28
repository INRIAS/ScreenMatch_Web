package com.aluracursos.screenmatch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.aluracursos.screenmatch.principal.PrincipalRepository;
import com.aluracursos.screenmatch.repository.SerieRepository;

@SpringBootApplication
public class ScreenmatchApplication implements CommandLineRunner {
	@Autowired// otorga permisos de actualizar el mapeo
	private SerieRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(ScreenmatchApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		// Principal principal = new Principal();
		// principal.muestraMenu();

		// EjemploStream ejemploStream = new EjemploStream();
		// ejemploStream.muestraEjemplo();

		PrincipalRepository principalRepository = new PrincipalRepository(repository);
		principalRepository.muestraElMenu();
	}
}
