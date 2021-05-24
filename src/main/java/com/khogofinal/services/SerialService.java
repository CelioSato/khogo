package com.khogofinal.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.khogofinal.domain.SerialNumber;
import com.khogofinal.repositories.SerialRepository;


@Service
public class SerialService {
	
	@Autowired
	private SerialRepository repo;
	
	
	public SerialNumber findDevice(String serialNumber) {
		Optional<SerialNumber> obj = repo.findDevice(serialNumber);
		return obj.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Este Número Serial não é válido") );
	}
	
	public SerialNumber insert(SerialNumber obj) {
		obj.setId(null);
		try {
			obj = repo.save(obj);
		} catch (Exception e){
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "O Número serial ja existe.");
		}
		return obj;	
	}

	public SerialNumber find(Integer id) {
		Optional<SerialNumber> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado") );	
	}


	
}



