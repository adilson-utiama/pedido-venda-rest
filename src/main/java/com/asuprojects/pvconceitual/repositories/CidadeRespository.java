package com.asuprojects.pvconceitual.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.asuprojects.pvconceitual.domain.Cidade;

@Repository
public interface CidadeRespository extends JpaRepository<Cidade, Integer>{

}
