package com.asuprojects.pvconceitual.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asuprojects.pvconceitual.domain.Cidade;
import com.asuprojects.pvconceitual.repositories.CidadeRespository;

@Service
public class CidadeService {

	@Autowired
	private CidadeRespository repo;
	
	public List<Cidade> findByEstado(Integer estado_id){
		return repo.findCidades(estado_id);
	}
}
