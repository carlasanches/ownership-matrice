package com.br.mom;

import java.util.Hashtable;

public class Desenvolvedor {
	
	private String nome;
	private String email;
	private Hashtable<String, Double> propriedades;

	public Desenvolvedor(String nome, String email) {
		this.nome = nome;
		this.email = email;
		this.propriedades = new Hashtable<>();
	}

	public String getNome() {
		return nome;
	}

	public String getEmail() {
		return email;
	}	
}