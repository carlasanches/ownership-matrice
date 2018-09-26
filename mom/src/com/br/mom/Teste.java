package com.br.mom;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Teste {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Recursos.getInstance().criarRecursosRepoDriller("");
		
		//1. Separa os commits devidamente? OK
//		for(Commit c : Recursos.getInstance().getCommits()) {
//			System.out.print(c.getHash() + "	");
//			System.out.println(c.getModificacoes().size());
//		}
		
		//2. Separa desenvolvedores devidamente? OK
//		for(Desenvolvedor d : Limpeza.detectarDesenvolvedores()) {
//			System.out.println(d.getNome());
//		}
		
		//3. Separa arquivos devidamente? OK
//		for(String s : Limpeza.detectarArquivos()) {
//			System.out.println(s);
//		}
		

		
		
		//System.out.println(Limpeza.detectarDesenvolvedores().toString());
		//System.out.println(Limpeza.detectarArquivos().toString());
		//System.out.println(Metodos.centralidade("junit/swingui/TestRunner.java"));
		
		//MOM mom = new MOM();
		//mom.principaisModulosCentralidade();
		//mom.print();
		//mom.save();
		
		
//		for(Desenvolvedor d : Limpeza.detectarDesenvolvedores()) {
//			d.calcularPropriedade();	
//			//System.out.println(d.getPropriedades().toString());
//			ArrayList<Double> valores = new ArrayList<Double>(d.getPropriedades().values());
//			System.out.println(Normalizacao.normalizar(valores).toString());
//		}
		
		
		
		
		
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
