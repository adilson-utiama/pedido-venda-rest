package com.asuprojects.pvconceitual.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asuprojects.pvconceitual.domain.Pedido;
import com.asuprojects.pvconceitual.repositories.PedidoRepository;
import com.asuprojects.pvconceitual.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository pedidos;

	public Pedido findById(int id) {
		Optional<Pedido> optional = pedidos.findById(id);
		return optional.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado, ID: " + id + 
				" , Tipo: " + Pedido.class.getName()));
	}
}
