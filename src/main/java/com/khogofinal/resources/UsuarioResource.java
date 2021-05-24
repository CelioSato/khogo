package com.khogofinal.resources;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.khogofinal.domain.Usuario;
import com.khogofinal.dto.UsuarioDTO;
import com.khogofinal.services.SerialService;
import com.khogofinal.services.UsuarioService;
import com.khogofinal.services.exceptions.UsuarioCadastradoException;

@RestController
@RequestMapping(value="/usuarios")
public class UsuarioResource {
	
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private SerialService serialService;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)// verbos GET, POST, DELETE
	public ResponseEntity<Usuario> find(@PathVariable Integer id){
		Usuario obj = usuarioService.find(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(value="/user/{id}", method=RequestMethod.GET)// verbos GET, POST, DELETE
	public ResponseEntity<UsuarioDTO> findUsuario(@PathVariable Integer id){
		Usuario obj = usuarioService.find(id);
		UsuarioDTO objDto = usuarioService.findUsuario(obj);
		return ResponseEntity.ok().body(objDto);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public Usuario insert(@RequestBody @Valid Usuario obj) {
		String serial = obj.getSerialNumber();
		System.out.println(serialService.findDevice(serial));
			try {
				obj = usuarioService.insert(obj);
				return obj;	
			}catch (UsuarioCadastradoException e) {
				throw new  ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
			}
	}

	@RequestMapping(method=RequestMethod.GET)// verbos GET, POST, DELETE
	public ResponseEntity<UsuarioDTO> findUser(@RequestParam("username") String username){
		//String username = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString(); CAPTURAR O USUSARIO LOGADO
		Usuario obj = usuarioService.findUser(username);
		UsuarioDTO objDto = usuarioService.findDto(obj) ;
		return ResponseEntity.ok().body(objDto);
	}

}
