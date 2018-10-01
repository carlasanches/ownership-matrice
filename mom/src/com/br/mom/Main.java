package com.br.mom;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Recursos.getInstance().criarRecursosRepoDriller("");
				
		MOM mom = new MOM();		
		mom.calcularPropriedade();
		mom.save("");
		
	}

}
