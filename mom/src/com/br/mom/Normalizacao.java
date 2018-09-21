package com.br.mom;

import java.util.ArrayList;
import java.util.Collections;

public class Normalizacao {
	
	public static ArrayList<Double> normalizar(ArrayList<Double> valores) {
		
		ArrayList<Double> valoresNormalizados = new ArrayList<>();
		double porcentagem = 0;

		Collections.sort(valores);
		Collections.reverse(valores);
		
		porcentagem = (1 - valores.get(0)) / valores.get(0);
		
		for(int i = 0; i < valores.size(); i++) {
			valoresNormalizados.add(valores.get(i) + porcentagem);
		}
		
		return valoresNormalizados;
	}

}
