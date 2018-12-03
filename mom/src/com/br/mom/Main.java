package com.br.mom;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int mes = 9;
		int ano = 2018;
//		for(int i = 0; i < 5; i++) {
//			for(int j = 0; j < 12; j++) {
				Recursos.getInstance().criarRecursosRepoDriller("D:/Usuarios/Carlos/OneDrive/UFOP/TCC/Atividades/Resultados - mensal/pentaho-kettle/" + ano + "-" + mes + ".csv");
				
				MOM mom = new MOM();		
				mom.calcularPropriedade();
				mom.save("D:/Usuarios/Carlos/OneDrive/UFOP/TCC/Atividades/Resultados - mensal/pentaho-kettle-matriz/" + ano + "-0" + mes + ".csv");
//				mes++;
//			}	
//			mes = 1;
//			ano++;
//		}
//		
		
		
	}
}
