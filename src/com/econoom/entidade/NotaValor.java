package com.econoom.entidade;

public class NotaValor {
	
	private int id;
	private String nome;
	private float valor;
	private double latitude;
	private double longitude;
	private String dataValidade;
	private int tpPagamento;
	
	public NotaValor(String nome, float valor, double latitude, double longitude, String dataValidade, int tpPagamento){
		
		this.nome = nome;
		this.valor = valor;
		this.latitude = latitude;
		this.longitude = longitude;
		this.dataValidade = dataValidade;
		this.tpPagamento = tpPagamento;
		
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public float getValor() {
		return valor;
	}
	public void setValor(float valor) {
		this.valor = valor;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(long latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(long longitude) {
		this.longitude = longitude;
	}
	public String getDataValidade() {
		return dataValidade;
	}
	public void setDataValidade(String dataValidade) {
		this.dataValidade = dataValidade;
	}
	public int getId() {
		return id;
	}

	public int getTpPagamento() {
		return tpPagamento;
	}

	public void setTpPagamento(int tpPagamento) {
		this.tpPagamento = tpPagamento;
	}
	
	

}
