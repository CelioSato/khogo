package com.khogofinal.resources;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.khogofinal.domain.Cliente;
import com.khogofinal.domain.Endereco;
import com.khogofinal.domain.Usuario;
import com.khogofinal.domain.Veiculo;
import com.khogofinal.dto.ClienteDTO;
import com.khogofinal.dto.ClienteEnderecoDTO;
import com.khogofinal.dto.ClienteNewDTO;
import com.khogofinal.services.ClienteService;
import com.khogofinal.services.EnderecoService;


/*
 * String principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		System.out.println("NOME , SENHA E ID: "+ principal);
*/


@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {
	
	@Autowired
	private ClienteService service;
	
	@Autowired
	private EnderecoService endService;
	
	String username;
	int userId;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)// verbos GET, POST, DELETE
	public ResponseEntity<Cliente> find(@PathVariable Integer id){
		Cliente obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(value="/usuarioFind/{id}", method=RequestMethod.GET)// verbos GET, POST, DELETE
	public ResponseEntity< List<ClienteDTO> > findClienteByUsuarioId(@PathVariable Integer id){
		
		idUsuario();
		
		List<Cliente> list = service.findClienteByUsuarioId(userId);
		List<ClienteDTO> listDto = list.stream().map(obj -> new ClienteDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}
	
//------------------------------------------------------------------------------------------------------	
	public int idUsuario() {
		username = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		Usuario usuario = service.findUser(username);
		userId = usuario.getId(); 
		return userId;	
	}	
//------------------------------------------------------------------------------------------------------	
	
	@RequestMapping(value="/veiculoFind/{id}", method=RequestMethod.GET)// verbos GET, POST, DELETE
	public ResponseEntity< List<Veiculo> > findVeiculoByClienteId(@PathVariable Integer id){
		//Veiculo obj = service.findVeiculoByClienteId(id);
		List<Veiculo> obj = service.findVeiculoByClienteId(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(method=RequestMethod.POST)// verbos GET, POST, DELETE
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente insert(@Valid @RequestBody ClienteNewDTO objDto) {
		Cliente obj = service.fromDTO(objDto);
		obj = service.insert(obj); //Salva estes dados no banco
		return obj;		
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody ClienteDTO objDto, @PathVariable Integer id){
		Cliente obj = service.fromDTO(objDto);
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)// verbos GET, POST, DELETE
	public ResponseEntity<Void> delete(@PathVariable Integer id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(method=RequestMethod.GET)// verbos GET, POST, DELETE
	public ResponseEntity<List<ClienteDTO>> findAll(){
		List<Cliente> list = service.findAll();
		List<ClienteDTO> listDto = list.stream().map(obj -> new ClienteDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}
	
// ---------Atualizar Cliente e Endereco-------------
	@RequestMapping(value="/atualizar/{id}", method=RequestMethod.PUT)// verbos GET, POST, DELETE
	public ResponseEntity<Void> updateCliEnd(@RequestBody ClienteEnderecoDTO cliEndDto, @PathVariable Integer id){
		
		// Atualizar Cliente
		Cliente obj = service.fromCliEndDTO(cliEndDto);	
		obj.setId(id);
		obj = service.update(obj);
		
		// Atualizar Endereco
		Endereco endObj = endService.fromCliEndDTO(cliEndDto);
		endObj.setId(id);
		endObj = endService.update(endObj);
		return ResponseEntity.noContent().build();
	}
	
// ---------Buscar Cliente e Endereco-------------	
	@RequestMapping(value="/clienteEnd/{id}", method=RequestMethod.GET)// verbos GET, POST, DELETE
	public ResponseEntity<ClienteEnderecoDTO> findClienteEndereco(@PathVariable Integer id){
		Cliente cliObj = service.find(id);
		Endereco endObj = endService.find(id);
		ClienteEnderecoDTO cliEndDto = service.fromCliEndDTO(cliObj, endObj);
		return ResponseEntity.ok().body(cliEndDto);
	}
	
	
	@RequestMapping(value="/page",method=RequestMethod.GET)// verbos GET, POST, DELETE
	public ResponseEntity<Page<ClienteDTO>> findPage(
			@RequestParam(value = "page", defaultValue = "0") Integer page, 
			@RequestParam(value = "linesPerPage", defaultValue = "24")Integer linesPerPage, 
			@RequestParam(value = "orderBy", defaultValue = "nome")String orderBy, 
			@RequestParam(value = "direction", defaultValue = "ASC")String direction){
		Page<Cliente> list = service.findPage(page, linesPerPage, orderBy, direction);
		Page<ClienteDTO> listDto =  list.map(obj -> new ClienteDTO(obj));
		return ResponseEntity.ok().body(listDto);
	}
	
}
