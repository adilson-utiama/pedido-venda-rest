package com.asuprojects.pvconceitual.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.asuprojects.pvconceitual.domain.Cliente;
import com.asuprojects.pvconceitual.services.ClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteResource {

	@Autowired
	private ClienteService clientes;
	
	@GetMapping("/{id}")
	public ResponseEntity<?> buscaPorId(@PathVariable("id") int id){
		Cliente cliente = clientes.buscaPorId(id);
		return ResponseEntity.ok(cliente);
	}
}
