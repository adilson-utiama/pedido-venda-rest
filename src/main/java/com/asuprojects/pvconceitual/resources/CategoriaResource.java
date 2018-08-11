package com.asuprojects.pvconceitual.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.asuprojects.pvconceitual.domain.Categoria;
import com.asuprojects.pvconceitual.dto.CategoriaDTO;
import com.asuprojects.pvconceitual.services.CategoriaService;

@RestController
@RequestMapping("/categorias")
public class CategoriaResource {
	
	@Autowired
	private CategoriaService service;

	@GetMapping
	public ResponseEntity<List<CategoriaDTO>> findAll() {
		List<Categoria> categorias = service.findAll();
		List<CategoriaDTO> listaDTO = categorias.stream()
				.map(cat -> new CategoriaDTO(cat))
				.collect(Collectors.toList());
		return ResponseEntity.ok(listaDTO);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Categoria> findById(@PathVariable("id") int id){
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

	@PutMapping("/{id}")
	public ResponseEntity<Void> update(@RequestBody Categoria categoria, @PathVariable("id") int id){
		categoria.setId(id);
		service.update(categoria);
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") int id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	} 
	
	
	
}
