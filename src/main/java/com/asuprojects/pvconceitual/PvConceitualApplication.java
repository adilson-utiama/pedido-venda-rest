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

	@Autowired
	private CategoriaRepository categorias;
	@Autowired
	private ProdutoRepository produtos;
	@Autowired
	private EstadoRepository estados;
	@Autowired
	private CidadeRespository cidades;
	@Autowired
	private ClienteRepository clientes;
	@Autowired
	private EnderecoRepository enderecos;
	@Autowired
	private PedidoRepository pedidos;
	@Autowired
	private PagamentoRepository pagamentos;
	@Autowired
	private ItemPedidoRepository itemPedidos;
	
	public static void main(String[] args) {
		SpringApplication.run(PvConceitualApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		//Apenas para teste, populando banco ao startar spring boot
		Categoria c1 = new Categoria(null, "Informatica");
		Categoria c2 = new Categoria(null, "Escritorio");
		Categoria c3 = new Categoria(null, "Cama mesa e banho");
		Categoria c4 = new Categoria(null, "Eletrônicos");
		Categoria c5 = new Categoria(null, "Jardinagem");
		Categoria c6 = new Categoria(null, "Decoração");
		Categoria c7 = new Categoria(null, "Perfumaria");
		
		Produto p1 = new Produto(null, "Computador", new BigDecimal(2000.0));
		Produto p2 = new Produto(null, "Impressora", new BigDecimal(800.0));
		Produto p3 = new Produto(null, "Mouse", new BigDecimal(80.0));
		
		c1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		c2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().add(c1);
		p2.getCategorias().addAll(Arrays.asList(c1, c2));
		p3.getCategorias().add(c1);
				
		categorias.saveAll(Arrays.asList(c1, c2, c3, c4, c5, c6, c7));
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
		
		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "36378912377", TipoCliente.PESSOAFISICA);
		
		cli1.getTelefones().addAll(Arrays.asList("27363323", "93838393"));
		
		Endereco e1 = new Endereco(null, "Rua Flores", "300", "Apto 303", "Jardim", "38220834", cli1, cid1);
		Endereco e2 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Centro", "38777012", cli1, cid2);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));
		
		clientes.saveAll(Arrays.asList(cli1));
		enderecos.saveAll(Arrays.asList(e1, e2));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cli1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), cli1, e2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));
		
		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);
		
		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"), null);
		ped2.setPagamento(pagto2);
		
		pedidos.saveAll(Arrays.asList(ped1, ped2));
		pagamentos.saveAll(Arrays.asList(pagto1, pagto2));
		
		
		ItemPedido ip1 = new ItemPedido(ped1, p1, BigDecimal.ZERO, 1, BigDecimal.valueOf(2000.00));
		ItemPedido ip2 = new ItemPedido(ped1, p3, BigDecimal.ZERO, 2, BigDecimal.valueOf(80.00));
		ItemPedido ip3 = new ItemPedido(ped2, p2, BigDecimal.valueOf(100.00), 1, BigDecimal.valueOf(800.00));
		
		ped1.getItens().addAll(Arrays.asList(ip1, ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));
		
		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));
		
		itemPedidos.saveAll(Arrays.asList(ip1, ip2, ip3));
		
		
	}
}
