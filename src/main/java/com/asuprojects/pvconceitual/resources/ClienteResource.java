package com.asuprojects.pvconceitual.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.asuprojects.pvconceitual.domain.Cliente;
import com.asuprojects.pvconceitual.dto.ClienteDTO;
import com.asuprojects.pvconceitual.dto.ClienteNewDTO;
import com.asuprojects.pvconceitual.services.ClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteResource {

	@Autowired
	private ClienteService clientes;
	
	@GetMapping("/{id}")
	public ResponseEntity<Cliente> findById(@PathVariable("id") int id){
		Cliente cliente = clientes.findById(id);
		return ResponseEntity.ok(cliente);
	}
	
	@GetMapping("/email")
	public ResponseEntity<Cliente> findByEmail(@RequestParam(value = "value") String email){
		Cliente cliente = clientes.findByEmail(email);
		return ResponseEntity.ok().body(cliente);
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@GetMapping
	public ResponseEntity<List<ClienteDTO>> findAll() {
		List<Cliente> listaClientes = clientes.findAll();
		List<ClienteDTO> listaDTO = listaClientes.stream()
				.map(cat -> new ClienteDTO(cat))
				.collect(Collectors.toList());
		return ResponseEntity.ok(listaDTO);
	}

	@PostMapping
	public ResponseEntity<Void> insert(@Valid @RequestBody ClienteNewDTO catDTO){
		Cliente cliente = clientes.fromDTO(catDTO);
		Cliente clienteSalvo = clientes.insert(cliente);
		URI uriToResource = ServletUriComponentsBuilder
			.fromCurrentRequest()
			.path("/{id}")
			.buildAndExpand(clienteSalvo.getId())
			.toUri();
		
		return ResponseEntity.created(uriToResource).build();
	}
	
	
	@PutMapping("/{id}")
	public ResponseEntity<Void> update(@Valid @RequestBody ClienteDTO cliDTO, @PathVariable("id") int id){
		Cliente cliente = clientes.fromDTO(cliDTO);
		cliente.setId(id);
		clientes.update(cliente);
		return ResponseEntity.noContent().build();
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") int id){
		clientes.delete(id);
		return ResponseEntity.noContent().build();
	} 
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@GetMapping("/page")
	public ResponseEntity<Page<ClienteDTO>> findPage(
			@RequestParam(value = "page", defaultValue="0") Integer page, 
			@RequestParam(value = "linesPerPage", defaultValue="24") Integer linesPerPage, 
			@RequestParam(value = "direction", defaultValue="ASC") String direction, 
			@RequestParam(value = "orderBy", defaultValue="nome") String orderBy){
		Page<Cliente> pageCli = clientes.findPage(page, linesPerPage, direction, orderBy);
		Page<ClienteDTO> pageDTO = pageCli.map(c -> new ClienteDTO(c));
		return ResponseEntity.ok(pageDTO);
	}
}
