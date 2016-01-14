package com.econoom.entidade;

public class NotaValor {
	
	protected int id;
	protected String nome;
	protected float valor;
	protected String dataValidade;
	protected int tpPagamento;
	private String tpUnidadeMedida;
	private double codigoBarras;
	private float qtUnMedida;
	private int quantidade;
	private double latitude;
	private double longitude;
	private int tpCad;
	private long dataCadastro;
	private String descNotaValor;
	
	public NotaValor(int id, String nome, float valor, double latitude, double longitude, String dataValidade, int tpPagamento,
			String tpUnidadeMedida, double codigoBarras, float qtUnMedida, int quantidade, long dataCadastro, String descNotaValor){
		
		this.id = id;
		this.nome = nome;
		this.valor = valor;
		this.dataValidade = dataValidade;
		this.tpPagamento = tpPagamento;		
		this.tpUnidadeMedida = tpUnidadeMedida;
		this.codigoBarras = codigoBarras;
		this.qtUnMedida = qtUnMedida;
		this.quantidade = quantidade;
		this.latitude = latitude;
		this.longitude = longitude;
		this.dataCadastro = dataCadastro;
		this.descNotaValor = descNotaValor;
		
	}
	
	public NotaValor(int id, String nome, float valor, double latitude, double longitude,
					 String dataValidade, int tpPagamento, long dataCadastro, String descNotaValor){
		
		this.id = id;
		this.nome = nome;
		this.valor = valor;
		this.dataValidade = dataValidade;
		this.tpPagamento = tpPagamento;
		this.latitude = latitude;
		this.longitude = longitude;
		this.dataCadastro = dataCadastro;
		this.descNotaValor = descNotaValor;
		
	}
	
	public NotaValor(int id, String nome, float valor, String dataValidade, int tpPagamento, long dataCadastro){
		
		this.id = id;
		this.nome = nome;
		this.valor = valor;
		this.dataValidade = dataValidade;
		this.tpPagamento = tpPagamento;
		this.dataCadastro = dataCadastro;
		
	}
	
	
	
	public int getTpCad() {
		return tpCad;
	}

	public void setTpCad(int tpCad) {
		this.tpCad = tpCad;
	}

	public long getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(long dataCadastro) {
		this.dataCadastro = dataCadastro;
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


	public String getDescNotaValor() {
		return descNotaValor;
	}

	public void setDescNotaValor(String descNotaValor) {
		this.descNotaValor = descNotaValor;
	}
}
