package com.khogofinal.resources;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.khogofinal.domain.Endereco;
import com.khogofinal.dto.ClienteEnderecoDTO;
import com.khogofinal.dto.EnderecoDTO;
import com.khogofinal.services.EnderecoService;


@RestController
@RequestMapping(value = "/enderecos")
public class EnderecoResource {
	
	@Autowired
	private EnderecoService service;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)// verbos GET, POST, DELETE
	public ResponseEntity<Endereco> find(@PathVariable Integer id){
		Endereco obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(method=RequestMethod.POST)// verbos GET, POST, DELETE
	@ResponseStatus(HttpStatus.CREATED)
	public Endereco insert(@Valid @RequestBody Endereco obj) {
		obj.setId(null);
		obj = service.insert(obj); //Salva estes dados no banco
		return obj;		
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody EnderecoDTO objDto, @PathVariable Integer id){
		Endereco obj = service.fromDTO(objDto);
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
	public ResponseEntity<List<EnderecoDTO>> findAll(){
		List<Endereco> list = service.findAll();
		List<EnderecoDTO> listDto = list.stream().map(obj -> new EnderecoDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}
	
	@RequestMapping(value="/atualizar/{id}", method=RequestMethod.PUT)// verbos GET, POST, DELETE
	public ResponseEntity<Void> updateCliEnd(@RequestBody ClienteEnderecoDTO cliEndDto, @PathVariable Integer id){
		Endereco obj = service.fromCliEndDTO(cliEndDto);	
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	/*@RequestMapping(value="/page",method=RequestMethod.GET)// verbos GET, POST, DELETE
	public ResponseEntity<Page<EnderecoDTO>> findPage(
			@RequestParam(value = "page", defaultValue = "0") Integer page, 
			@RequestParam(value = "linesPerPage", defaultValue = "24")Integer linesPerPage, 
			@RequestParam(value = "orderBy", defaultValue = "nome")String orderBy, 
			@RequestParam(value = "direction", defaultValue = "ASC")String direction){
		Page<Endereco> list = service.findPage(page, linesPerPage, orderBy, direction);
		Page<EnderecoDTO> listDto =  list.map(obj -> new EnderecoDTO(obj));
		return ResponseEntity.ok().body(listDto);
	}*/

}
