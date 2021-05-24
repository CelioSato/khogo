package com.khogofinal.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException.Unauthorized;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import com.khogofinal.domain.ChatMember;
import com.khogofinal.domain.Telegram;
import com.khogofinal.dto.TelegramDTO;
import com.khogofinal.repositories.TelegramRepository;

@Service
public class TelegramService {
	
	@Autowired
	private TelegramRepository repo;
	
	public Telegram find(Integer id) {
		Optional<Telegram> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Token Telegram não encontrado") );	
	}
	
	public Telegram insert(Telegram obj) throws IOException {
		obj.setId(null);
		try {
			obj = repo.save(obj);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Nº do aparelho já existe", e);
		}
		return obj;
	}	
	
	public Telegram fromDto(@Valid TelegramDTO objDto) {
		Telegram obj = new Telegram(null, objDto.getToken(), objDto.getChatId(), objDto.getAparelho(), objDto.getTitle());
		return obj;
	}

//-------------------------------------------------------------------------------------------------------------------------------------------
	public List<ChatMember> findChat(String token) throws IOException {
		List<ChatMember> chatID = new ArrayList<>();
		try {
			 chatID = idDoChat(token);
			 
		} catch (NullPointerException e) {
			 throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Confira os passos para a criação do bot e grupo no telegram ID DO CHAT");
		}
		System.out.println("Iniciando CAHTID: " + chatID.get(1).getTitle());
		  return chatID;		  
	}
//------------------------------------------------------------------------------------------------------------------------
	//CAPTURAR O JSON QUE CONTÉM O ID DO GRUPO DE CHAT DO TELEGRAM
	  private List<ChatMember> idDoChat(String token) throws IOException{
		  // Capturar o JSON deste endereço
		   String result;
		   
		  	String uri = "https://api.telegram.org/bot"+token+"/getUpdates";
	        RestTemplate restTemplate = new RestTemplate();
	        
	        try {
	        	result = restTemplate.getForObject(uri, String.class);
	        } catch (Unauthorized e) {
	        	throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Erro! Confira o token");
			}
	      
	        // Converter a String JSON em um objeto JSON
	        JSONObject obj = new JSONObject(result);  // aqui é um JSON OBJECT
	        
	        // Converter o objeto JSON em um ARRAYJSON
	        JSONArray myArray = obj.getJSONArray("result");
	   
	        List<ChatMember> listChat = new ArrayList<>();
	        //Navegar pelos objetos JSON dentro do ARRAYJSON até o id do Grupo de Chat
	        for(int i = 0; i < myArray.length(); i++) {
	        	JSONObject obj1;
	        	 JSONObject jsonObject = myArray.getJSONObject(i);
	        	 
	        	 if(jsonObject.has("my_chat_member")) {
	        		 try {
	        			  obj1 = jsonObject.getJSONObject("my_chat_member");
	        		 }catch (Exception e) {
	        			 throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Confira os passos para a criação do bot e grupo no telegram");
					 }
	        		 JSONObject chat = obj1.getJSONObject("chat");
	        		 ChatMember dados = new ChatMember();
	        		 dados.setChatId(chat.getInt("id"));
	        		 dados.setTitle(chat.getString("title"));
	        		 
	        		 listChat.add(dados);

	        		 System.out.println("");
	        		 System.out.println("ID DO CHAT : "+ dados.getChatId() + " NOME DO GRUPO: " + dados.getTitle());
	        		 System.out.println(listChat.size());		
	    
	        	 }
	        }
	        return listChat;
	}

	public Telegram findDevice(String aparelho) {
		Optional<Telegram> obj = repo.findDevice(aparelho);
		return obj.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Aparelho não encontrado") );
	}	

}
