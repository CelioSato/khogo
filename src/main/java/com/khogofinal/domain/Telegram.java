package com.khogofinal.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

//import org.hibernate.validator.constraints.Length;

@Entity
public class Telegram implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)  // MYSQL
	//@Id
	//@GeneratedValue(generator = "increment") // Para Postgrees	HEROKU				
	//@GenericGenerator(name = "
	private Integer id;
	
	private String token;
	
	private Integer chatId;

	@Column(unique = true)
	//@Length(min = 7, max = 7, message = "O código do aparelho tem exatamente 7 caracteres!")
	private String aparelho;
	
	private String title;
	
	
	public Telegram() {
		
	}

	public Telegram(Integer id, String token, Integer chatId, String aparelho, String title) {
		super();
		this.id = id;
		this.token = token;
		this.chatId = chatId;
		this.aparelho = aparelho;
		this.title = title;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Telegram other = (Telegram) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	
}
