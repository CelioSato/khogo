package com.khogofinal.resources;

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

import com.khogofinal.domain.SerialNumber;
import com.khogofinal.services.SerialService;

@RestController
@RequestMapping(value = "/serialNumber")
public class SerialResource {
	
	@Autowired
	private SerialService service;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)// verbos GET, POST, DELETE
	public ResponseEntity<SerialNumber> find(@PathVariable Integer id){
		SerialNumber obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(method=RequestMethod.POST)// verbos GET, POST, DELETE
	@ResponseStatus(HttpStatus.CREATED)
	public SerialNumber insert(@Valid @RequestBody SerialNumber obj) {
		obj.setId(null);
		obj = service.insert(obj); //Salva estes dados no banco
		return obj;	
		
	}

}
