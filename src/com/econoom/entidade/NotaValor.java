package com.econoom.entidade;

public class NotaValor {
	
	protected int id;
	protected String nome;
	protected float valor;
	protected String dataValidade;
	protected int tpPagamento;
	
	public NotaValor(String nome, float valor, String dataValidade, int tpPagamento){
		
		this.nome = nome;
		this.valor = valor;
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
