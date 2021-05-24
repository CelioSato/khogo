package com.khogofinal.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khogofinal.domain.Location;
import com.khogofinal.repositories.LocalizacaoRepository;

@Service
public class LocalizacaoService {
	
	
	@Autowired
	private LocalizacaoRepository localizacaoRepository;
	
	public Location insert(Location obj) {
		obj.setId(null);
		obj = localizacaoRepository.save(obj);
		return obj;	
	}
		
		



}
