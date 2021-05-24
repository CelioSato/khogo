package com.khogofinal.resources;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.khogofinal.domain.Login;
import com.khogofinal.domain.Telegram;
import com.khogofinal.services.LoginService;
import com.khogofinal.services.TelegramService;



@RestController
@RequestMapping(value="/login")
public class LoginResource {
	
	@Autowired
	private LoginService loginService;
	
	@Autowired
	private SendMessage sendMessage;
	
	@Autowired
	private TelegramService service;
	
	private String endereco = "https://khogo1.herokuapp.com/localizar/localizacao , coloque o login: Felipe";

	// Inserir Login 
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Login> insert(@RequestBody Login obj) {
		String aparelho = obj.getAparelho();
		Telegram telegramObj = service.findDevice(aparelho);
		//Envia a mensagem ao telegram
		//sendMessage.sendToTelegram(endereco);
		String token = telegramObj.getToken();
		String chatId = telegramObj.getChatId().toString();
		sendMessage.sendToTelegram(token, chatId, endereco);
		obj = loginService.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();		
	}
	
	
	
	
	
	
	
	// Buscar por ID
		/*@RequestMapping(value="/{id}", method=RequestMethod.GET)
		public ResponseEntity <Login> findId(@PathVariable Integer id) {
			Login obj = loginService.findId(id);
			System.out.println(obj);
			return ResponseEntity.ok().body(obj);
		}*/
	

}
