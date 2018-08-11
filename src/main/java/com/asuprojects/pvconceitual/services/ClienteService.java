package com.asuprojects.pvconceitual.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.asuprojects.pvconceitual.domain.Cidade;
import com.asuprojects.pvconceitual.domain.Cliente;
import com.asuprojects.pvconceitual.domain.Endereco;
import com.asuprojects.pvconceitual.domain.enums.TipoCliente;
import com.asuprojects.pvconceitual.dto.ClienteDTO;
import com.asuprojects.pvconceitual.dto.ClienteNewDTO;
import com.asuprojects.pvconceitual.repositories.ClienteRepository;
import com.asuprojects.pvconceitual.repositories.EnderecoRepository;
import com.asuprojects.pvconceitual.services.exceptions.DataIntegrityException;
import com.asuprojects.pvconceitual.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repo;
	
	@Autowired
	private EnderecoRepository endRepo;
	
	public Cliente findById(int id) {
		Optional<Cliente> optional = repo.findById(id);
		return optional.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado, ID: " + id + 
				" , Tipo: " + Cliente.class.getName()));
	}
	
	public Cliente insert(Cliente obj) {
		obj.setId(null);
		obj = repo.save(obj);
		endRepo.saveAll(obj.getEnderecos());
		return obj;
	}
	
	public Cliente update(Cliente obj) {
		Cliente cliente = findById(obj.getId());
		updateData(cliente, obj);
		return repo.save(cliente);
	} 
	
	public void delete(Integer id) {
		findById(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir porque há pedido(s) associado(s) a este cliente");
		}
	}
	
	public List<Cliente> findAll() {
		return repo.findAll();
		
	}
	
	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String direction, String orderBy){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	public Cliente fromDTO(ClienteDTO objDTO) {
		return new Cliente(objDTO.getId(), objDTO.getNome(), objDTO.getEmail(), null, null);
	}
	
	public Cliente fromDTO(ClienteNewDTO objDTO) {
		Cliente cli = new Cliente(null, objDTO.getNome(), objDTO.getEmail(), objDTO.getCpfOuCnpj(), TipoCliente.toEnum(objDTO.getTipo()));
		Cidade cid = new Cidade(objDTO.getCidadeId(), null, null);
		Endereco end = new Endereco(null, objDTO.getLogradouro(), objDTO.getNumero(), objDTO.getComplemento(),
				objDTO.getBairro(), objDTO.getCep(), cli, cid);
		
		cli.getEnderecos().add(end);
		cli.getTelefones().add(objDTO.getTelefone1());
		if(objDTO.getTelefone2() != null) {
			cli.getTelefones().add(objDTO.getTelefone2());
		}
		if(objDTO.getTelefone3() != null) {
			cli.getTelefones().add(objDTO.getTelefone3());
		}
		
		return cli;
	}
	

	private void updateData(Cliente newObj, Cliente cli) {
		newObj.setNome(cli.getNome());
		newObj.setEmail(cli.getEmail());
	}
}
