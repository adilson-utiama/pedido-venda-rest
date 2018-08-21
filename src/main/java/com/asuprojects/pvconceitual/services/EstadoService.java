package com.asuprojects.pvconceitual.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asuprojects.pvconceitual.domain.Estado;
import com.asuprojects.pvconceitual.repositories.EstadoRepository;

@Service
public class EstadoService {

	@Autowired
	private EstadoRepository repo;
	
	public List<Estado> findAll(){
		return repo.findAllByOrderByNome();
	}
}
