package com.khogofinal.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import com.khogofinal.domain.Endereco;

public class EnderecoDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer id;
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
	
	public EnderecoDTO() {
		
	}
	
	public EnderecoDTO(Endereco obj) {
		id = obj.getId();
		rua = obj.getRua();
		num = obj.getNum();
		bairro = obj.getBairro();
		cep = obj.getCep();
		cidade = obj.getCidade();
		uf = obj.getUf();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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
