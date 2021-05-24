package com.khogofinal.domain;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Location implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)  // MYSQL
	//@Id
	//@GeneratedValue(generator = "increment") // Para Postgrees	HEROKU				
	//@GenericGenerator(name = "
	private Integer id;
	
	@Column(nullable = false, length = 10)
	private String username;
	private String latitude;
	private String longitude;
	private String aparelho;
	
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private LocalDateTime dataCadastro;
	
	@PrePersist
	public void prePersist() {
		setDataCadastro(LocalDateTime.now());
	}
	
	public Location() {
		
	}

	public Location(Integer id, String username, String latitude, String longitude, String aparelho, LocalDateTime dataCadastro) {
		super();
		this.id = id;
		this.username = username;
		this.latitude = latitude;
		this.longitude = longitude;
		this.aparelho = aparelho;
		this.dataCadastro = dataCadastro;
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

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getAparelho() {
		return aparelho;
	}

	public void setAparelho(String aparelho) {
		this.aparelho = aparelho;
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
		Location other = (Location) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Location [id=" + id + ", username=" + username + ", latitude=" + latitude + ", longitude=" + longitude
				+ ", aparelho=" + aparelho + ", dataCadastro=" + dataCadastro + "]";
	}

}
