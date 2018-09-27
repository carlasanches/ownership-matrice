package com.br.mom;

import java.lang.reflect.Array;
import java.util.ArrayList;
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
	
	public void calcularPropriedade() {
		for(String arquivo : Recursos.getInstance().getArquivos()) {			
			propriedades.put(arquivo, Metodos.doa(this.getNome(), arquivo));			
		}
	}	
	
	public boolean eProprietario(double limNormalizado, double limAbsoluto, String arquivo) {
		
		ArrayList<Double> valoresProps = new ArrayList<>(this.propriedades.values());
		ArrayList<Double> doaNormalizados = Normalizacao.normalizar(valoresProps);
		Hashtable<String, Double> propsNormalizadas = new Hashtable<>();
		
		for(int i = 0; i < Recursos.getInstance().getArquivos().size(); i++) {
			propsNormalizadas.put(Recursos.getInstance().getArquivos().get(i), doaNormalizados.get(i));
		}
		
		if(propsNormalizadas.get(arquivo) > limNormalizado && propriedades.get(arquivo) >= limAbsoluto) {
			return true;
		}
		
		return false;
	}

	public Hashtable<String, Double> getPropriedades() {
		return propriedades;
	}

	@Override
	public String toString() {
		return "Desenvolvedor [nome=" + nome + ", email=" + email + "]" + "\n";
	}	
	
	
}
