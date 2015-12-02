package com.econoom.entidade;

public class Produto extends NotaValor {
	
	private String tpUnidadeMedida;
	private long codigoBarras;
	private float qtUnMedida;
	private int quantidade;

	public Produto(String nome, float valor, double latitude, double longitude, String dataValidade, int tpPagamento,
	String tpUnidadeMedida, long codigoBarras, float qtUnMedida, int quantidade) {
		
		super(nome, valor, latitude, longitude, dataValidade,tpPagamento);
		
		this.tpUnidadeMedida = tpUnidadeMedida;
		this.codigoBarras = codigoBarras;
		this.qtUnMedida = qtUnMedida;
		this.quantidade = quantidade;
		
	}

	public String getTpUnidadeMedida() {
		return tpUnidadeMedida;
	}

	public void setTpUnidadeMedida(String tpUnidadeMedida) {
		this.tpUnidadeMedida = tpUnidadeMedida;
	}

	public long getCodigoBarras() {
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
