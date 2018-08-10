package com.asuprojects.pvconceitual.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asuprojects.pvconceitual.domain.Cliente;
import com.asuprojects.pvconceitual.repositories.ClienteRepository;
import com.asuprojects.pvconceitual.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clientes;
	
	public Cliente buscaPorId(int id) {
		Optional<Cliente> optional = clientes.findById(id);
		return optional.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado, ID: " + id + 
				" , Tipo: " + Cliente.class.getName()));
	}
}
