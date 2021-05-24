package com.khogofinal.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.PrePersist;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.br.CPF;

//import org.hibernate.validator.constraints.Length;
//import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ClienteNewDTO implements Serializable{
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
	@NotEmpty(message="CPF/CNPJ, preenchimento obrigatorio")
	@CPF
	private String cpfOuCnpj;
	private Integer tipo;
	
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private LocalDateTime dataCadastro;
	
	@NotEmpty(message="Rua, preenchimento obrigatorio")
	private String rua;
	private String num;
	private String bairro;
	private String cep;
	@NotEmpty(message="Cidade, preenchimento obrigatorio")
	private String cidade;
	@NotEmpty(message="Estado, preenchimento obrigatorio")
	private String uf;
	
	@NotEmpty(message="Código do aparelho, preenchimento obrigatorio")
	//@Length(min = 7, max = 7, message = "O código do aparelho tem exatamente 7 caracteres!")
	private String aparelho;
	@NotEmpty(message="Veiculo, preenchimento obrigatorio")
	private String veiculo;
	@NotEmpty(message="Cor, preenchimento obrigatorio")
	private String cor;
	@NotEmpty(message="Placa, preenchimento obrigatorio")
	private String placa;

	
	private String device;
	
	@PrePersist
	public void prePersist() {
		setDataCadastro(LocalDateTime.now());
	}
	
	public ClienteNewDTO() {
		
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

	public String getCpfOuCnpj() {
		return cpfOuCnpj;
	}

	public void setCpfOuCnpj(String cpfOuCnpj) {
		this.cpfOuCnpj = cpfOuCnpj;
	}

	public Integer getTipo() {
		return tipo;
	}

	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}

	public LocalDateTime getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(LocalDateTime dataCadastro) {
		this.dataCadastro = dataCadastro;
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

	public String getAparelho() {
		return aparelho;
	}

	public void setAparelho(String aparelho) {
		this.aparelho = aparelho;
	}

	public String getVeiculo() {
		return veiculo;
	}

	public void setVeiculo(String veiculo) {
		this.veiculo = veiculo;
	}

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getDevice() {
		return device;
	}

	public void setDevice(String device) {
		this.device = device;
	}

	
	
}
