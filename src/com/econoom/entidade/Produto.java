package com.econoom.entidade;

public class Produto extends NotaValor {

	public Produto(String nome, float valor, double latitude, double longitude, String dataValidade, int tpPagamento,
	String tpUnidadeMedida, double codigoBarras, float qtUnMedida, int quantidade, String dataCadastro) {
		
		super(nome, valor, latitude, longitude, dataValidade, tpPagamento, tpUnidadeMedida, codigoBarras, qtUnMedida, quantidade, dataCadastro);
	}

}
