package com.khogofinal.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.khogofinal.domain.Cliente;
import com.khogofinal.domain.Veiculo;
import com.khogofinal.dto.VeiculoDTO;
import com.khogofinal.repositories.VeiculoRepository;


@Service
public class VeiculoService {
	
	@Autowired
	private VeiculoRepository repo;
	
	@Autowired
	private ClienteService cliService;
	
	public Veiculo find(Integer id) {
		Optional<Veiculo> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Veiculo não encontrado") );	
	}
	
	@Transactional
	public Veiculo insert(Veiculo obj) {
		obj.setId(null);
		//obj.setCliente(cliService.find(obj.getCliente().getId()));
		try {
			obj = repo.save(obj);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Nº do Aparelho já existe");
		}
		return obj;
	}
	
// Busca de Veiculo pelo codigo do aparelho
	public Veiculo findDevice(String device) {
		Optional<Veiculo> obj = repo.findDevice(device);
		return obj.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Veiculo não encontrado") );
	}

//-----------------------------UPDATE------------------------------------------
	public Veiculo update(Veiculo obj) {
		Veiculo newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}
	
	private void updateData(Veiculo newObj, Veiculo obj) {
		newObj.setAparelho(obj.getAparelho());
		newObj.setVeiculo(obj.getVeiculo());
		newObj.setCor(obj.getCor());
		newObj.setPlaca(obj.getPlaca());
	}
	
	public Veiculo fromDTO(VeiculoDTO objDto) {
		return new Veiculo(objDto.getId(), objDto.getAparelho(), objDto.getVeiculo(), objDto.getCor(), objDto.getPlaca(), null, null);
	
	}
	
//--------------------------------------------------------------------------

	public void delete(Integer id) {
		find(id);
		repo.deleteById(id);
	}
	
	public List<Veiculo> findAll() {
		return repo.findAll();
	}

	public Veiculo fromNewDTO(VeiculoDTO objDto) {
		Cliente cli = new Cliente();
		cli = cliService.find(objDto.getId());
		Veiculo veic = new Veiculo(objDto.getId(), objDto.getAparelho(), objDto.getVeiculo(), objDto.getCor(), objDto.getPlaca(), cli, objDto.getDataCadastro());
		return veic;
	}
	
}



