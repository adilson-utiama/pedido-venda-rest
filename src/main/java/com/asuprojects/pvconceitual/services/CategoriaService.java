package com.asuprojects.pvconceitual.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.asuprojects.pvconceitual.domain.Categoria;
import com.asuprojects.pvconceitual.repositories.CategoriaRepository;
import com.asuprojects.pvconceitual.services.exceptions.DataIntegrityException;
import com.asuprojects.pvconceitual.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository categorias;
	
	public Categoria findById(int id) {
		Optional<Categoria> optional = categorias.findById(id);
		return optional.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado, ID: " + id + 
				" , Tipo: " + Categoria.class.getName()));
	}
	
	public Categoria insert(Categoria categoria) {
		categoria.setId(null);
		return categorias.save(categoria);
	}
	
	public Categoria update(Categoria categoria) {
		findById(categoria.getId());
		return categorias.save(categoria);
	} 
	
	public void delete(Integer id) {
		findById(id);
		try {
			categorias.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir categoria que possui produttos");
		}
	}
	
	public List<Categoria> findAll() {
		return categorias.findAll();
		
	}

}
