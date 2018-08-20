package com.asuprojects.pvconceitual.services;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

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

@Service
public class DBService {
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

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
	
	public void instantiateTestDatabase() throws ParseException {
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
		Produto p4 = new Produto(null, "Mesa de escritório", BigDecimal.valueOf(300.00));
		Produto p5 = new Produto(null, "Toalha", BigDecimal.valueOf(50.00));
		Produto p6 = new Produto(null, "Colcha", BigDecimal.valueOf(200.00));
		Produto p7 = new Produto(null, "TV true color", BigDecimal.valueOf(1200.00));
		Produto p8 = new Produto(null, "Roçadeira", BigDecimal.valueOf(800.00));
		Produto p9 = new Produto(null, "Abajour", BigDecimal.valueOf(100.00));
		Produto p10 = new Produto(null, "Pendente", BigDecimal.valueOf(180.00));
		Produto p11 = new Produto(null, "Shampoo", BigDecimal.valueOf(90.00));
		
		c1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		c2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().add(c1);
		p2.getCategorias().addAll(Arrays.asList(c1, c2));
		p3.getCategorias().add(c1);
		
		c2.getProdutos().addAll(Arrays.asList(p2, p4));
		c3.getProdutos().addAll(Arrays.asList(p5, p6));
		c4.getProdutos().addAll(Arrays.asList(p1, p2, p3, p7));
		c5.getProdutos().addAll(Arrays.asList(p8));
		c6.getProdutos().addAll(Arrays.asList(p9, p10));
		c7.getProdutos().addAll(Arrays.asList(p11));
		
		p1.getCategorias().addAll(Arrays.asList(c1, c4));
		p2.getCategorias().addAll(Arrays.asList(c1, c2, c4));
		p3.getCategorias().addAll(Arrays.asList(c1, c4));
		p4.getCategorias().addAll(Arrays.asList(c2));
		p5.getCategorias().addAll(Arrays.asList(c3));
		p6.getCategorias().addAll(Arrays.asList(c3));
		p7.getCategorias().addAll(Arrays.asList(c4));
		p8.getCategorias().addAll(Arrays.asList(c5));
		p9.getCategorias().addAll(Arrays.asList(c6));
		p10.getCategorias().addAll(Arrays.asList(c6));
		p11.getCategorias().addAll(Arrays.asList(c7));
				
		categorias.saveAll(Arrays.asList(c1, c2, c3, c4, c5, c6, c7));
		produtos.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11));
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		
		Cidade cid1 = new Cidade(null, "Uberlândia", est1);
		Cidade cid2 = new Cidade(null, "São Paulo", est2);
		Cidade cid3 = new Cidade(null, "Campinas", est2);
		
		est1.getCidades().add(cid1);
		est2.getCidades().addAll(Arrays.asList(cid2, cid3));
		
		estados.saveAll(Arrays.asList(est1, est2));
		cidades.saveAll(Arrays.asList(cid1, cid2, cid3));
		
		Cliente cli1 = new Cliente(null, "Maria Silva", "wilson.developer.tester@gmail.com", "36378912377", TipoCliente.PESSOAFISICA, passwordEncoder.encode("123"));
		
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
