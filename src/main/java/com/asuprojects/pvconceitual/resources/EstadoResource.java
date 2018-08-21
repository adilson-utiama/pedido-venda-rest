package com.asuprojects.pvconceitual.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.asuprojects.pvconceitual.domain.Cidade;
import com.asuprojects.pvconceitual.domain.Estado;
import com.asuprojects.pvconceitual.dto.CidadeDTO;
import com.asuprojects.pvconceitual.dto.EstadoDTO;
import com.asuprojects.pvconceitual.services.CidadeService;
import com.asuprojects.pvconceitual.services.EstadoService;

@RestController
@RequestMapping("/estados")
public class EstadoResource {

	@Autowired
	private EstadoService estadoService;
	
	@Autowired
	private CidadeService cidadeService;
	
	@GetMapping
	public ResponseEntity<List<EstadoDTO>> findAll(){
		List<Estado> estados = estadoService.findAll();
		List<EstadoDTO> list = estados.stream().map(x -> new EstadoDTO(x)).collect(Collectors.toList());
		return ResponseEntity.ok(list);
	}
	
	@GetMapping("/{estado_id}/cidades")
	public ResponseEntity<List<CidadeDTO>> findCidades(@PathVariable("estado_id") Integer estadoId){
		List<Cidade> cidades = cidadeService.findByEstado(estadoId);
		List<CidadeDTO> list = cidades.stream().map(cid -> new CidadeDTO(cid)).collect(Collectors.toList());
		return ResponseEntity.ok().body(list);
	}
}
