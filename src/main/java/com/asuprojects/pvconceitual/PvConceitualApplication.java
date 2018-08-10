package com.asuprojects.pvconceitual;

import java.math.BigDecimal;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.asuprojects.pvconceitual.domain.Categoria;
import com.asuprojects.pvconceitual.domain.Cidade;
import com.asuprojects.pvconceitual.domain.Estado;
import com.asuprojects.pvconceitual.domain.Produto;
import com.asuprojects.pvconceitual.repositories.CategoriaRepository;
import com.asuprojects.pvconceitual.repositories.CidadeRespository;
import com.asuprojects.pvconceitual.repositories.EstadoRepository;
import com.asuprojects.pvconceitual.repositories.ProdutoRepository;

@SpringBootApplication
public class PvConceitualApplication implements CommandLineRunner{

	@Autowired
	private CategoriaRepository categorias;
	@Autowired
	private ProdutoRepository produtos;
	@Autowired
	private EstadoRepository estados;
	@Autowired
	private CidadeRespository cidades;
	
	public static void main(String[] args) {
		SpringApplication.run(PvConceitualApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		//Apenas para teste, populando banco ao startar spring boot
		Categoria c1 = new Categoria(null, "Informatica");
		Categoria c2 = new Categoria(null, "Escritorio");
		
		Produto p1 = new Produto(null, "Computador", new BigDecimal(2000.0));
		Produto p2 = new Produto(null, "Impressora", new BigDecimal(800.0));
		Produto p3 = new Produto(null, "Mouse", new BigDecimal(80.0));
		
		c1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		c2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().add(c1);
		p2.getCategorias().addAll(Arrays.asList(c1, c2));
		p3.getCategorias().add(c1);
				
		categorias.saveAll(Arrays.asList(c1, c2));
		produtos.saveAll(Arrays.asList(p1, p2, p3));
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		
		Cidade cid1 = new Cidade(null, "Uberlândia", est1);
		Cidade cid2 = new Cidade(null, "São Paulo", est2);
		Cidade cid3 = new Cidade(null, "Campinas", est2);
		
		est1.getCidades().add(cid1);
		est2.getCidades().addAll(Arrays.asList(cid2, cid3));
		
		estados.saveAll(Arrays.asList(est1, est2));
		cidades.saveAll(Arrays.asList(cid1, cid2, cid3));
	}
}
