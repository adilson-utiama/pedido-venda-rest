package com.asuprojects.pvconceitual.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.asuprojects.pvconceitual.domain.Estado;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Integer>{

	@Transactional(readOnly=true)
	public List<Estado> findAllByOrderByNome();
}
