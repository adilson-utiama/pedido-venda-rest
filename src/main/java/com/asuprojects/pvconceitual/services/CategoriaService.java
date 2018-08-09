package com.asuprojects.pvconceitual.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asuprojects.pvconceitual.domain.Categoria;
import com.asuprojects.pvconceitual.repositories.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository categorias;
	
	public Categoria buscaPorId(int id) {
		Optional<Categoria> optional = categorias.findById(id);
		return optional.orElse(null);
	}

	public List<Categoria> findAll() {
		return categorias.findAll();
		
	}

}
