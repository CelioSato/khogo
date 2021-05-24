package com.khogofinal.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import com.khogofinal.domain.Cliente;

public class ClienteDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	@NotEmpty(message="Nome, preenchimento obrigatorio")
	private String nome;
	
	@NotEmpty(message="Email, preenchimento obrigatorio")
	@Email(message="Email inválido")
	private String email;
	
	@NotEmpty(message="Telefone, preenchimento obrigatorio")
	private String telefone;
	
	@NotEmpty(message="Telefone para contato, preenchimento obrigatorio")
	private String telefone1;
	
	public ClienteDTO(){
		
	}
	
	public ClienteDTO(Cliente obj){
		id = obj.getId();
		nome = obj.getNome();
		email = obj.getEmail();
		telefone = obj.getTelefone();
		telefone1 = obj.getTelefone1();
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	public String getTelefone1() {
		return telefone1;
	}

	public void setTelefone1(String telefone1) {
		this.telefone1 = telefone1;
	}


}
