package com.khogofinal.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;

@Entity
public class Usuario implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)  // MYSQL
	//@Id
	//@GeneratedValue(generator = "increment") // Para Postgrees	HEROKU				
	//@GenericGenerator(name = "
	private Integer id;
	
	@Column(unique = true)
	@NotEmpty(message="{campo.login.obrigatorio}")
	private String username;
	
	@NotEmpty(message="{campo.senha.obrigatorio}")
	private String password;
	
	@Column(unique = true)
	@NotEmpty(message="{campo.serial.obrigatorio}")
	private String serialNumber;
	
	@OneToMany(mappedBy = "usuario")
	private List<Cliente> clientes = new ArrayList<>();	
	
	
	public Usuario() {
		
	}

	public Usuario(Integer id, String username, String password, String serialNumber) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.serialNumber = serialNumber;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	
	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
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
		Usuario other = (Usuario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", username=" + username + ", password=" + password + ", clientes=" + clientes
				+ "]";
	}

}
