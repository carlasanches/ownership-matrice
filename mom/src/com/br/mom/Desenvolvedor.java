package com.br.mom;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Hashtable;

public class Desenvolvedor {
	
	private String nome;
	private String email;
	private Hashtable<String, Double> propriedades;
	private Hashtable<String, Double> temPropriedade;
	private ArrayList<String> arquivos;

	public Desenvolvedor(String nome, String email) {
		this.nome = nome;
		this.email = email;
		this.propriedades = new Hashtable<>();
		this.temPropriedade = new Hashtable<>();
		this.arquivos = new ArrayList<>();
	}

	public String getNome() {
		return nome;
	}

	public String getEmail() {
		return email;
	}
	
	public void calcularPropriedade() {
		for(String arquivo : Recursos.getInstance().getArquivos()) {			
			propriedades.put(arquivo, Metodos.doa(this.getNome(), arquivo));			
		}
	}	
	
	public boolean eProprietario(double limNormalizado, double limAbsoluto, double valorNormalizado, double valorAbsoluto) {
						
		if(valorNormalizado >= limNormalizado && valorAbsoluto >= limAbsoluto) {
			return true;
		}
		
		return false;
	}

	public Hashtable<String, Double> getPropriedades() {
		return propriedades;
	}
	
	public Hashtable<String, Double> getTemPropriedade(){
		return temPropriedade;
	}
	
	public ArrayList<String> getArquivos(){
		return arquivos;
	}

	@Override
	public String toString() {
		return "Desenvolvedor [nome=" + nome + ", email=" + email + "]" + "\n";
	}	
	
	
}
