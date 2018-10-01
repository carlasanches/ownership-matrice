package com.br.mom;

import java.util.ArrayList;
import java.util.Collections;

public class Normalizacao {
	
	public static ArrayList<Double> normalizar(ArrayList<Double> valores) {
		
		ArrayList<Double> valoresNormalizados = new ArrayList<>();
		double porcentagem = 0;
		double maiorValor = 0;

		for(Double valor : valores) {
			if(valor > maiorValor) {
				maiorValor = valor;
			}
		}
				
		porcentagem = (1 - maiorValor) / maiorValor;
		
		for(int i = 0; i < valores.size(); i++) {
			valoresNormalizados.add(valores.get(i) + (porcentagem * valores.get(i)));
		}
		
		return valoresNormalizados;
	}

}
