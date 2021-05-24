package com.khogofinal.resources;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.khogofinal.domain.ChatMember;
import com.khogofinal.domain.Telegram;
import com.khogofinal.dto.TelegramDTO;
import com.khogofinal.services.TelegramService;

@RestController
@RequestMapping(value= "/telegrans")
public class TelegramResource {
	
	@Autowired
	private TelegramService service;
	
	//Salvar os dados do Telegram na banco de dados
	@RequestMapping(method=RequestMethod.POST)// verbos GET, POST, DELETE
	@ResponseStatus(HttpStatus.CREATED)
	public Telegram insert(@Valid @RequestBody TelegramDTO objDto) throws IOException {
	    Telegram obj = service.fromDto(objDto);
		obj = service.insert(obj);
		return obj;
	}
	
	//Buscar o chatId e titulo do grupo do Telegram
	@RequestMapping(method=RequestMethod.GET)// verbos GET, POST, DELETE
	public List<ChatMember> findChat(@RequestParam(value="token")String token) throws IOException {
		List<ChatMember> list = service.findChat(token);
		return list;
	}

}
