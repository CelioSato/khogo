package com.khogofinal.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.khogofinal.domain.Endereco;
import com.khogofinal.dto.ClienteEnderecoDTO;
import com.khogofinal.dto.EnderecoDTO;
import com.khogofinal.repositories.EnderecoRepository;


@Service
public class EnderecoService {
	
	@Autowired
	private EnderecoRepository repo;
	
	public Endereco find(Integer id) {
		Optional<Endereco> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Endereco não encontrado") );	
	}
	
	public Endereco insert(Endereco obj) {
		obj.setId(null);
		try {
			obj = repo.save(obj);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Confira os dados do Endereço");
		}
		return obj;
	}
	
//-----------------------------UPDATE------------------------------------------
	public Endereco update(Endereco obj) {
		Endereco newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}
		
	private void updateData(Endereco newObj, Endereco obj) {
		newObj.setRua(obj.getRua());
		newObj.setNum(obj.getNum());
		newObj.setBairro(obj.getBairro());
		newObj.setCep(obj.getCep());
		newObj.setCidade(obj.getCidade());
		newObj.setUf(obj.getUf());
	}
	
	public Endereco fromDTO(EnderecoDTO objDto) {
		return new Endereco(objDto.getId(), objDto.getRua(), objDto.getNum(), objDto.getBairro(), objDto.getCep(), objDto.getCidade(), objDto.getUf(), null);        
	}
	
//-------------------------------------------------------------------------------------	
	public Endereco fromCliEndDTO(ClienteEnderecoDTO cliEndDto) {
		return new Endereco(cliEndDto.getId(),  cliEndDto.getRua(), cliEndDto.getNum(), cliEndDto.getBairro(), cliEndDto.getCep(), cliEndDto.getCidade(), cliEndDto.getUf(), null);
		
	}
		
//--------------------------------------------------------------------------

	public void delete(Integer id) {
		find(id);
		repo.deleteById(id);
	}
	
	public List<Endereco> findAll() {
		return repo.findAll();
	}

	
	
	/*public Page<Endereco> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);	
	}*/

	
	
}
