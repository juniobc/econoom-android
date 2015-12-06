package com.econoom.entidade;

public class Servico extends NotaValor {
	
	private double latitude;
	private double longitude;

	public Servico(String nome, float valor, double latitude, double longitude, String dataValidade, int tpPagamento) {
		
		super(nome, valor, dataValidade, tpPagamento);
		
		this.latitude = latitude;
		this.longitude = latitude;

	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	
	

}
