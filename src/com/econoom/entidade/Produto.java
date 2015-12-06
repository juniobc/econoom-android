package com.econoom.entidade;

public class Produto extends NotaValor {
	
	private String tpUnidadeMedida;
	private double codigoBarras;
	private float qtUnMedida;
	private int quantidade;
	private double latitude;
	private double longitude;

	public Produto(String nome, float valor, double latitude, double longitude, String dataValidade, int tpPagamento,
	String tpUnidadeMedida, double codigoBarras, float qtUnMedida, int quantidade) {
		
		super(nome, valor, dataValidade,tpPagamento);
		
		this.tpUnidadeMedida = tpUnidadeMedida;
		this.codigoBarras = codigoBarras;
		this.qtUnMedida = qtUnMedida;
		this.quantidade = quantidade;
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

	public String getTpUnidadeMedida() {
		return tpUnidadeMedida;
	}

	public void setTpUnidadeMedida(String tpUnidadeMedida) {
		this.tpUnidadeMedida = tpUnidadeMedida;
	}

	public double getCodigoBarras() {
		return codigoBarras;
	}

	public void setCodigoBarras(long codigoBarras) {
		this.codigoBarras = codigoBarras;
	}

	public float getQtUnMedida() {
		return qtUnMedida;
	}

	public void setQtUnMedida(float qtUnMedida) {
		this.qtUnMedida = qtUnMedida;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	
	

}
