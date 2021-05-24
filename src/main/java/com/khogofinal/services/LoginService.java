package com.khogofinal.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.khogofinal.domain.Login;
import com.khogofinal.repositories.LoginRepository;

@Service
public class LoginService {
	
	@Autowired
	private LoginRepository repo;
	
	// Buscar por ID
	public Login findId(Integer id) {
		Optional<Login> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Login não encontrado"));
	}
	
	// Inserir Login 
	public Login insert(Login obj) {
		boolean exists = repo.existsByUsername(obj.getUsername());
		if(exists) {
			
			System.out.println("Login ja existe");
			//throw new LoginCadastradoException(obj.getUsername());
		}
		obj.setId(null);
		return repo.save(obj);
	}

}
