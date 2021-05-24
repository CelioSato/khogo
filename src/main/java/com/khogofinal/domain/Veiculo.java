package com.khogofinal.domain;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.validation.constraints.NotEmpty;

//import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Veiculo implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)  // MYSQL
	//@Id
	//@GeneratedValue(generator = "increment") // Para Postgrees	HEROKU				
	//@GenericGenerator(name = "increment", strategy = "increment") // Para Postgrees	HEROKU	
	private Integer id;
	
	@Column(unique = true)
	@NotEmpty(message="Preenchimento obrigatorio")
	//@Length(min = 7, max = 7, message = "O código do aparelho tem exatamente 7 caracteres!")
	private String aparelho;
	private String veiculo;
	private String cor;
	private String placa;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;
	
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private LocalDateTime dataCadastro;
	
	@PrePersist
	public void prePersist() {
		setDataCadastro(LocalDateTime.now());
	}
	
	public Veiculo() {
		
	}

	public Veiculo(Integer id, String aparelho, String veiculo, String cor, String placa, Cliente cliente,
			LocalDateTime dataCadastro) {
		super();
		this.id = id;
		this.aparelho = aparelho;
		this.veiculo = veiculo;
		this.cor = cor;
		this.placa = placa;
		this.cliente = cliente;
		this.dataCadastro = dataCadastro;
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

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public LocalDateTime getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(LocalDateTime dataCadastro) {
		this.dataCadastro = dataCadastro;
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
		Veiculo other = (Veiculo) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Veiculo [id=" + id + ", aparelho=" + aparelho + ", veiculo=" + veiculo + ", cor=" + cor + ", placa="
				+ placa + ", cliente=" + cliente + ", dataCadastro=" + dataCadastro + "]";
	}
	
	

	

}
