package com.br.mom;

public class Modificacao {
	
	private String tipo;
	private String nomeArquivoAtual;
	private String nomeArquivoAntigo;
	private String nomeArquivoNovo;
	
	public Modificacao(String tipo, String nomeArquivoAtual, String nomeArquivoAntigo, String nomeArquivoNovo){
		super();
		this.tipo = tipo;
		this.nomeArquivoAtual = nomeArquivoAtual;
		this.nomeArquivoAntigo = nomeArquivoAntigo;
		this.nomeArquivoNovo = nomeArquivoNovo;
	}

	public String getTipo() {
		return tipo;
	}

	public String getNomeArquivoAtual() {
		return nomeArquivoAtual;
	}

	public String getNomeArquivoAntigo() {
		return nomeArquivoAntigo;
	}

	public String getNomeArquivoNovo() {
		return nomeArquivoNovo;
	}		
}
