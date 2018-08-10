package com.asuprojects.pvconceitual.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
	public ResponseEntity<?> findById(@PathVariable("id") int id){
		Categoria categoria = service.findById(id);
		return ResponseEntity.ok(categoria);
	}

	@PostMapping
	public ResponseEntity<Void> insert(@RequestBody Categoria categoria){
		Categoria categoriaSalva = service.insert(categoria);
	
		//Constroi uri baseado no recurso criado
		URI uriToResource = ServletUriComponentsBuilder
			.fromCurrentRequest()
			.path("/{id}")
			.buildAndExpand(categoriaSalva.getId())
			.toUri();
		
		return ResponseEntity.created(uriToResource).build(); //devolve no header da resposta
	}

}
