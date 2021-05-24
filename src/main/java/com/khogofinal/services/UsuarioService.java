package com.khogofinal.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.khogofinal.domain.Usuario;
import com.khogofinal.dto.UsuarioDTO;
import com.khogofinal.repositories.UsuarioRepository;
import com.khogofinal.services.exceptions.UsuarioCadastradoException;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository repo;
	
	public Usuario find(Integer id) {
		Optional<Usuario> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario não encontrado") );	
	}

	public Usuario insert(Usuario obj) {
		boolean exists = repo.existsByUsername(obj.getUsername());
		if(exists) {
			throw new UsuarioCadastradoException(obj.getUsername());
		}
		obj = repo.save(obj);
		return obj;
	}

	public Usuario findUser(String username) {
		Optional<Usuario> obj = repo.findByUsername(username);
		return obj.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario não encontrado") );	
	}
	
	public UsuarioDTO findDto(Usuario obj) {
		UsuarioDTO objDto = new UsuarioDTO();
		objDto.setId(obj.getId());
		objDto.setUsername(obj.getUsername());
		objDto.setPassword(obj.getPassword());
		return objDto;
		
	}

	public UsuarioDTO findUsuario(Usuario obj) {
		UsuarioDTO objDto = new UsuarioDTO();
		objDto.setId(obj.getId());
		objDto.setUsername(obj.getUsername());
		objDto.setPassword(obj.getPassword());
		return objDto;
	}

	
}







