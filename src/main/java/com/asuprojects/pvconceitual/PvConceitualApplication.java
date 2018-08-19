package com.asuprojects.pvconceitual;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.asuprojects.pvconceitual.domain.Categoria;
import com.asuprojects.pvconceitual.domain.Cidade;
import com.asuprojects.pvconceitual.domain.Cliente;
import com.asuprojects.pvconceitual.domain.Endereco;
import com.asuprojects.pvconceitual.domain.Estado;
import com.asuprojects.pvconceitual.domain.ItemPedido;
import com.asuprojects.pvconceitual.domain.Pagamento;
import com.asuprojects.pvconceitual.domain.PagamentoComBoleto;
import com.asuprojects.pvconceitual.domain.PagamentoComCartao;
import com.asuprojects.pvconceitual.domain.Pedido;
import com.asuprojects.pvconceitual.domain.Produto;
import com.asuprojects.pvconceitual.domain.enums.EstadoPagamento;
import com.asuprojects.pvconceitual.domain.enums.TipoCliente;
import com.asuprojects.pvconceitual.repositories.CategoriaRepository;
import com.asuprojects.pvconceitual.repositories.CidadeRespository;
import com.asuprojects.pvconceitual.repositories.ClienteRepository;
import com.asuprojects.pvconceitual.repositories.EnderecoRepository;
import com.asuprojects.pvconceitual.repositories.EstadoRepository;
import com.asuprojects.pvconceitual.repositories.ItemPedidoRepository;
import com.asuprojects.pvconceitual.repositories.PagamentoRepository;
import com.asuprojects.pvconceitual.repositories.PedidoRepository;
import com.asuprojects.pvconceitual.repositories.ProdutoRepository;

@SpringBootApplication
public class PvConceitualApplication implements CommandLineRunner{

	
	
	public static void main(String[] args) {
		SpringApplication.run(PvConceitualApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		
		
		
	}
}
