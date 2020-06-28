package src;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Main {

	public static void main(String[] args) {
		
		Recursos.getInstance().criarRecursosRepoDriller("../ownership-matrice/exemplos/resultados-repodriller/mockito.csv");
		
		MOM mom = new MOM();		
		mom.calcularPropriedade();
		mom.save("exemplo.csv");	
	}
}
