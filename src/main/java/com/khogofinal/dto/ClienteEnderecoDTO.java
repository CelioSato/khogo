package com.khogofinal.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import com.khogofinal.domain.Cliente;
import com.khogofinal.domain.Endereco;

public class ClienteEnderecoDTO {
	
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
	
	@NotEmpty(message="Rua, preenchimento obrigatorio")
	private String rua;
	private String num;
	@NotEmpty(message="Bairro, preenchimento obrigatorio")
	private String bairro;
	private String cep;
	@NotEmpty(message="Cidade, preenchimento obrigatorio")
	private String cidade;
	@NotEmpty(message="Estado, preenchimento obrigatorio")
	private String uf;
	
	public ClienteEnderecoDTO() {
		
	}
	
	public ClienteEnderecoDTO(Cliente cliObj, Endereco endObj) {
		id = cliObj.getId();
		nome = cliObj.getNome();
		email = cliObj.getEmail();
		telefone = cliObj.getTelefone();
		telefone1 = cliObj.getTelefone1();
		
		rua = endObj.getRua();
		num = endObj.getNum();
		bairro = endObj.getBairro();
		cep = endObj.getCep();
		cidade = endObj.getCidade();
		uf = endObj.getUf();
		
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

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}
	
}
