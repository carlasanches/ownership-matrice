package com.br.mom;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Teste {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Recursos.getInstance().criarRecursosRepoDriller("");
		
//		for(Commit c : Recursos.getInstance().getCommits()) {
//			try {
//				BufferedWriter escritor = new BufferedWriter(new FileWriter("test.txt",true));			
//				escritor.write(String.valueOf(c.getHash()) + "\n");
//				System.out.println("[INFO]: Escrita realizada com sucesso.");
//				escritor.close();
//				
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
		
	}

}