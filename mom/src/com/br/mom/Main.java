package com.br.mom;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Recursos.getInstance().criarRecursosRepoDriller("D:/Usuarios/Carlos/OneDrive/UFOP/TCC/Atividades/Resultados - mensal/pentaho-kettle/2006-8.csv");
				
		MOM mom = new MOM();		
		mom.calcularPropriedade();
		mom.save("D:/Usuarios/Carlos/OneDrive/UFOP/TCC/Atividades/Resultados - mensal/pentaho-kettle-matriz/2006-08.csv");
		
	}
}
