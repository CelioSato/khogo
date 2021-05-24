package com.khogofinal.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.khogofinal.domain.Cliente;
import com.khogofinal.domain.Veiculo;

public class VeiculoDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	@NotEmpty(message="Aparelho, preenchimento obrigatorio")
	private String aparelho;
	
	@NotEmpty(message="Veiculo, preenchimento obrigatorio")
	private String veiculo;
	
	@NotEmpty(message="Cor, preenchimento obrigatorio")
	private String cor;
	
	@NotEmpty(message="Placa, preenchimento obrigatorio")
	private String placa;
	
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private LocalDateTime dataCadastro;
	
	private Cliente cliente;
	
	public VeiculoDTO() {
		
	}
	
	public VeiculoDTO(Veiculo obj) {
		id = obj.getId();
		aparelho = obj.getAparelho();
		veiculo = obj.getVeiculo();
		cor = obj.getCor();
		placa = obj.getPlaca();
		dataCadastro = obj.getDataCadastro();
		cliente = obj.getCliente();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public LocalDateTime getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(LocalDateTime dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	

}
