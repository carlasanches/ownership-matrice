package com.br.mom;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

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
//		for(Desenvolvedor d : Recursos.getInstance().getDesenvolvedores()) {
//			System.out.println(d.getNome());
//		}
		
		//3. Separa arquivos devidamente? OK
		
//		int cont = 0;
//		
//		for(String s : Recursos.getInstance().getArquivos()) {
//			System.out.println((++cont) + "	" + s);
//		}
		
		//4. Calcula DOA corretamente?		
		//FA? OK
		
//		int cont = 0;
//		
//		for(String arquivo : Limpeza.detectarArquivos()) {
//			for(Desenvolvedor d : Limpeza.detectarDesenvolvedores()) {
//				System.out.println((++cont) + "	" + d.getNome() + "	" + Metodos.firstAutorship(d.getNome(), arquivo) + "	" + arquivo);
//			}
//		}		
		
		//DL? OK
		
//		int cont = 0;
//		
//		for(String arquivo : Recursos.getInstance().getArquivos()) {
//			for(Desenvolvedor d : Recursos.getInstance().getDesenvolvedores()) {
//				System.out.println((++cont) + "	" + d.getNome() + "	" + Metodos.deliveries(d.getNome(), arquivo) + "	" + arquivo);
//			}
//		}	
		
		//AC? OK
		
//		int cont = 0;
//		
//		for(String arquivo : Recursos.getInstance().getArquivos()) {
//			for(Desenvolvedor d : Recursos.getInstance().getDesenvolvedores()) {
//				System.out.println((++cont) + "	" + d.getNome() + "	" + Metodos.acceptances(d.getNome(), arquivo) + "	" + arquivo);
//			}
//		}	
		
		//DOA? OK
		
//		int cont = 0;
//		
//		for(String arquivo : Limpeza.detectarArquivos()) {
//			for(Desenvolvedor d : Limpeza.detectarDesenvolvedores()) {
//				System.out.println((++cont) + "	" + d.getNome() + "	" + Metodos.doa(d.getNome(), arquivo) + "	" + arquivo);
//			}
//		}
		
		//5. Normaliza DOA corretamente? OK
		
//		int cont = 0;
//		
//		ArrayList<Double> valores;
//		
//		for(Desenvolvedor d : Limpeza.detectarDesenvolvedores()) {
//			valores = new ArrayList<>();
//			for(String arquivo : Limpeza.detectarArquivos()) {				
//				valores.add(Metodos.doa(d.getNome(), arquivo));				
//			}			
//			System.out.println(Normalizacao.normalizar(valores));
//			System.out.println(valores.toString());
//		}
		
		//6. Detecta principais módulos corretamente?		
		//Centralidade? OK
		
//		int cont = 0;
		
//		for(Desenvolvedor d : Recursos.getInstance().getDesenvolvedores()) {
//			d.calcularPropriedade();
//		}	
//		
//		ArrayList<String> modulos = new ArrayList<>();
//		
//		for(String arquivo : Recursos.getInstance().getArquivos()) {
//			String[] modulo = arquivo.split("/");
//			
//			if(!modulos.contains(modulo[0])) {
//				modulos.add(modulo[0]);	
//			}
//		}
//		
//		for(String modulo : modulos) {
//			System.out.println(Metodos.centralidade(modulo) + "	" + modulo);
//		}
		
		//Primeiros módulos? OK
		
		//Cálculo k-means?		
				
		MOM mom = new MOM();
		mom.principaisModulosCentralidade();
		
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
