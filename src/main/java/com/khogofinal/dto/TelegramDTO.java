package com.khogofinal.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;

import com.khogofinal.domain.Telegram;

public class TelegramDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	
	private Integer id;
	
	@Column(unique = true)
	@NotEmpty(message="{campo.token.obrigatorio}")
	private String token;
	
	private Integer chatId;
	
	@Column(unique = true)
	@NotEmpty(message="{campo.aparelho.obrigatorio}")
	private String aparelho;
	
	private String title;
	
	public TelegramDTO() {
		
	}
	
	public TelegramDTO(Telegram obj) {
		id = obj.getId();
		token = obj.getToken();
		chatId = obj.getChatId();
		aparelho = obj.getAparelho();
		title = obj.getTitle();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Integer getChatId() {
		return chatId;
	}

	public void setChatId(Integer chatId) {
		this.chatId = chatId;
	}

	public String getAparelho() {
		return aparelho;
	}

	public void setAparelho(String aparelho) {
		this.aparelho = aparelho;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
