package com.asuprojects.pvconceitual.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.asuprojects.pvconceitual.domain.Categoria;
import com.asuprojects.pvconceitual.services.CategoriaService;

@RestController
@RequestMapping("/categorias")
public class CategoriaResource {
	
	@Autowired
	private CategoriaService service;

	@GetMapping
	public ResponseEntity<?> listarTodos() {
		List<Categoria> categorias = service.findAll();
		return ResponseEntity.ok(categorias);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> buscaPorId(@PathVariable("id") int id){
		Categoria categoria = service.buscaPorId(id);
		return ResponseEntity.ok(categoria);
	}
}
