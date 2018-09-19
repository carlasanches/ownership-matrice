package com.br.mom;

import java.sql.Date;
import java.util.ArrayList;

public class Commit {
	
	private int hash;
	private Desenvolvedor autor;
	private Desenvolvedor committer;
	private String mensagem;
	private Date dataAutoria;
	private Date dataCommit;
	private ArrayList<Modificacao> modificacoes;
	
	public Commit(int hash, Desenvolvedor autor, Desenvolvedor committer, String mensagem, Date dataAutoria,
			Date dataCommit, ArrayList<Modificacao> modificacoes) {
		super();
		this.hash = hash;
		this.autor = autor;
		this.committer = committer;
		this.mensagem = mensagem;
		this.dataAutoria = dataAutoria;
		this.dataCommit = dataCommit;
		this.modificacoes = modificacoes;
	}	
	
	public int getHash() {
		return hash;
	}

	public Date getDataAutoria() {
		return dataAutoria;
	}

	public Date getDataCommit() {
		return dataCommit;
	}

	public ArrayList<Modificacao> getModificacoes() {
		return modificacoes;
	}	
}
