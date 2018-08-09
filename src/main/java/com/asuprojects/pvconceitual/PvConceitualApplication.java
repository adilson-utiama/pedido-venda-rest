package com.asuprojects.pvconceitual;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.asuprojects.pvconceitual.domain.Categoria;
import com.asuprojects.pvconceitual.repositories.CategoriaRepository;

@SpringBootApplication
public class PvConceitualApplication implements CommandLineRunner{

	@Autowired
	private CategoriaRepository categorias;
	
	public static void main(String[] args) {
		SpringApplication.run(PvConceitualApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		//Apenas para teste, populando banco ao startar spring boot
		Categoria c1 = new Categoria(null, "Informatica");
		Categoria c2 = new Categoria(null, "Escritorio");
		categorias.saveAll(Arrays.asList(c1, c2));
		
	}
}
