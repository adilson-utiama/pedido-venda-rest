package com.asuprojects.pvconceitual.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.asuprojects.pvconceitual.domain.Cliente;
import com.asuprojects.pvconceitual.dto.ClienteDTO;
import com.asuprojects.pvconceitual.repositories.ClienteRepository;
import com.asuprojects.pvconceitual.services.exceptions.DataIntegrityException;
import com.asuprojects.pvconceitual.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clientes;
	
	public Cliente findById(int id) {
		Optional<Cliente> optional = clientes.findById(id);
		return optional.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado, ID: " + id + 
				" , Tipo: " + Cliente.class.getName()));
	}
	
	public Cliente update(Cliente cli) {
		Cliente cliente = findById(cli.getId());
		updateData(cliente, cli);
		return clientes.save(cliente);
	} 
	
	public void delete(Integer id) {
		findById(id);
		try {
			clientes.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir porque há entidades relacionadas");
		}
	}
	
	public List<Cliente> findAll() {
		return clientes.findAll();
		
	}
	
	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String direction, String orderBy){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return clientes.findAll(pageRequest);
	}
	
	public Cliente fromDTO(ClienteDTO cliDTO) {
		return new Cliente(cliDTO.getId(), cliDTO.getNome(), cliDTO.getEmail(), null, null);
	}

	private void updateData(Cliente cliente, Cliente cli) {
		cliente.setNome(cli.getNome());
		cliente.setEmail(cli.getEmail());
	}
}
