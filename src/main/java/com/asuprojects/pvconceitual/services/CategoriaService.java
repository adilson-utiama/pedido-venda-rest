package com.asuprojects.pvconceitual.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.asuprojects.pvconceitual.domain.Categoria;
import com.asuprojects.pvconceitual.dto.CategoriaDTO;
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
	
	public Categoria update(Categoria cat) {
		Categoria categoria = findById(cat.getId());
		updateData(categoria, cat);
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
	
	public Page<Categoria> findPage(Integer page, Integer linesPerPage, String direction, String orderBy){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return categorias.findAll(pageRequest);
	}
	
	public Categoria fromDTO(CategoriaDTO catDTO) {
		return new Categoria(catDTO.getId(), catDTO.getNome());
	}

	private void updateData(Categoria categoria, Categoria cat) {
		categoria.setNome(cat.getNome());
		
	}
	
}
