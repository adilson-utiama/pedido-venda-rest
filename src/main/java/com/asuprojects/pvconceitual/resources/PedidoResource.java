package com.asuprojects.pvconceitual.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.asuprojects.pvconceitual.domain.Pedido;
import com.asuprojects.pvconceitual.services.PedidoService;

@RestController
@RequestMapping("/pedidos")
public class PedidoResource {

	@Autowired
	private PedidoService pedidos;
	
	@GetMapping("/{id}")
	public ResponseEntity<?> buscaPorId(@PathVariable("id") int id){
		Pedido pedido = pedidos.buscaPorId(id);
		return ResponseEntity.ok(pedido);
	}
}
