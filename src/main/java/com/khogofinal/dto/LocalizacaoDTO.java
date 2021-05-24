package com.khogofinal.dto;

import java.io.Serializable;

import com.khogofinal.domain.Cliente;
import com.khogofinal.domain.Location;

public class LocalizacaoDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String username;

	private String veiculo;
	private String cor;
	private String placa;
	private String telefone;
	private String telefone1;
	private String latitude;
	private String longitude;

	
	public LocalizacaoDTO() {
		
	}
	
	public LocalizacaoDTO(Cliente obj, Location objLoc) {
		/*aparelho = obj.getAparelho();
		veiculo = obj.getVeiculo();
		cor = obj.getCor();
		placa = obj.getPlaca();*/
		telefone = obj.getTelefone();
		telefone1 = obj.getTelefone1();
		latitude = objLoc.getLatitude();
		longitude = objLoc.getLongitude();
		username = objLoc.getUsername();
	
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

}
