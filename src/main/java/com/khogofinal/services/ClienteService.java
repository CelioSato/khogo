package com.khogofinal.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.khogofinal.domain.Cliente;
import com.khogofinal.domain.Endereco;
import com.khogofinal.domain.Usuario;
import com.khogofinal.domain.Veiculo;
import com.khogofinal.domain.enums.TipoCliente;
import com.khogofinal.dto.ClienteDTO;
import com.khogofinal.dto.ClienteEnderecoDTO;
import com.khogofinal.dto.ClienteNewDTO;
import com.khogofinal.repositories.ClienteRepository;
import com.khogofinal.repositories.EnderecoRepository;
import com.khogofinal.repositories.UsuarioRepository;
import com.khogofinal.repositories.VeiculoRepository;


@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repo;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private VeiculoRepository veiculorepository;
	
	@Autowired
	private UsuarioService userService;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	
	public Cliente find(Integer id) {
		Optional<Cliente> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado") );	
	}
	
	public List<Veiculo> findVeiculoByClienteId(Integer id) {
		return veiculorepository.findVeiculoById(id);
		//return obj.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Veiculo não encontrado") );	
	}
	
	public List<Cliente> findClienteByUsuarioId(Integer id) {
		return repo.findClienteByUsuarioId(id);
		//return obj.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Veiculo não encontrado") );	
	}
	
//----------------------------INSERT----------------------------------------
	
	@Transactional
	public Cliente insert(Cliente obj) {
		obj.setId(null);
		obj.setUsuario(userService.find(obj.getUsuario().getId()));
		try {
			obj = repo.save(obj);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Confira os dados do Cliente");
		}
		
		try {
			enderecoRepository.saveAll(obj.getEnderecos());
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Confira os dados do endereço");
		}
		
		try {
			veiculorepository.saveAll(obj.getVeiculos());
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Nº do Aparelho já existe");
		}
		return obj;
	}
	
	
//-----------------------------UPDATE------------------------------------------

	public Cliente update(Cliente obj) {
		Cliente newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}

	private void updateData(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
		newObj.setTelefone(obj.getTelefone());
		newObj.setTelefone1(obj.getTelefone1());
	}
	
	public Cliente fromDTO(ClienteDTO objDto) {
		return new Cliente(objDto.getId(), objDto.getNome(), objDto.getEmail(), objDto.getTelefone(), objDto.getTelefone1(),  null, null, null, null);
		//return new Cliente(objDto.getId(), objDto.getNome(), objDto.getEmail(), objDto.getTelefone(), objDto.getTelefone1(), null, null, null);
	}

	
// Atualizar um Cliente e Endereço por ID	
	public Cliente fromCliEndDTO(ClienteEnderecoDTO cliEndDto) {
		return new Cliente(cliEndDto.getId(), cliEndDto.getNome(), cliEndDto.getEmail(), cliEndDto.getTelefone(), cliEndDto.getTelefone1(), null, null, null, null);
	}
	
//------------------------------------------------------------------------------
	
	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Não é possivel excluir um cliente que tenha veiculo associado");
		}
	}

	public List<Cliente> findAll() {
		return repo.findAll();
	}
	
	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);	
	}
	
	
	public Cliente fromDTO(ClienteNewDTO objDto) {
		Usuario user = new Usuario();
		user = userService.find(objDto.getId());
		int tipo;
		int count = objDto.getCpfOuCnpj().length();
		if(count > 11) {
			tipo = 2;
		} else {
			tipo = 1;
		}
		Cliente cli = new Cliente(null, objDto.getNome(), objDto.getEmail(), objDto.getTelefone(), objDto.getTelefone1(), objDto.getCpfOuCnpj(), TipoCliente.toEnum(tipo), objDto.getDataCadastro(), user);
		Endereco end = new Endereco(null, objDto.getRua(), objDto.getNum(), objDto.getBairro(), objDto.getCep(), objDto.getCidade(), objDto.getUf(), cli);
		Veiculo vei = new Veiculo(null, objDto.getAparelho(), objDto.getVeiculo(), objDto.getCor(), objDto.getPlaca(), cli, objDto.getDataCadastro());
		
		cli.getEnderecos().add(end);
		cli.getVeiculos().add(vei);
		
		
		return cli;	
	}

	public ClienteEnderecoDTO fromCliEndDTO(Cliente cliObj, Endereco endObj) {
		ClienteEnderecoDTO cliEndDto = new ClienteEnderecoDTO();
		cliEndDto.setId(cliObj.getId());
		cliEndDto.setNome(cliObj.getNome());
		cliEndDto.setEmail(cliObj.getEmail());
		cliEndDto.setTelefone(cliObj.getTelefone());
		cliEndDto.setTelefone1(cliObj.getTelefone1());
		cliEndDto.setRua(endObj.getRua());
		cliEndDto.setNum(endObj.getNum());
		cliEndDto.setBairro(endObj.getBairro());
		cliEndDto.setCep(endObj.getCep());
		cliEndDto.setCidade(endObj.getCidade());
		cliEndDto.setUf(endObj.getUf());
		return cliEndDto;
	}
	
	public Usuario findUser(String user) {
		Optional<Usuario> usuario = usuarioRepository.findByUsername(user);
		return usuario.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario não encontrado") );	
	}
	
	
}
