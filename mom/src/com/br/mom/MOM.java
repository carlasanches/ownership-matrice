package com.br.mom;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;

public class MOM {
	
	private ArrayList<String> modulos;
	private ArrayList<Desenvolvedor> desenvolvedores;
	private ArrayList<ArrayList<Double>> propriedades;
	
	public MOM(){
		this.modulos = new ArrayList<>();
		this.desenvolvedores = Limpeza.detectarDesenvolvedores();
		this.propriedades = new ArrayList<>();
	}
	
	public void principaisModulosCentralidade(){
		
		Hashtable<String, Integer> centralidadePorArquivo = new Hashtable<String, Integer>();
		Hashtable<String, Integer> centralidadePorModulo = new Hashtable<String, Integer>();
		
		for(String arquivo : Limpeza.detectarArquivos()) {	
						
			centralidadePorArquivo.put(arquivo, Metodos.centralidade(arquivo));			
			
			String[] dir = arquivo.split("/");
			
			if(!this.modulos.contains(dir[0])){				
				modulos.add(dir[0]);				
			}
		}
		
		for(String modulo : this.modulos) {
			
			int soma = 0;
			
			for(String arquivo : Limpeza.detectarArquivos()) {
				
				if(arquivo.startsWith(modulo)) {
					soma = soma + centralidadePorArquivo.get(arquivo);
				}
			}
			
			centralidadePorModulo.put(modulo, soma);
		}
		
		ArrayList<Integer> centralidadeArray = new ArrayList<>(centralidadePorModulo.values());
		
		ArrayList<ArrayList<Integer>> resultadoKMeans = new ArrayList<>();
	
		for(int k = 0; k <= 3; k++) {
			if(k < centralidadeArray.size()) {
				resultadoKMeans = Estatistica.kMeans(centralidadeArray, k);
			}			
		}
		
		double maiorMedia = 0;
		ArrayList<Double> medias = new ArrayList<>();
		
		for(ArrayList<Integer> array : resultadoKMeans) {
			double media = Estatistica.media(array);
			medias.add(media);
			
			if(media > maiorMedia) {
				maiorMedia = Estatistica.media(array);
			}
		}
		
		ArrayList<Integer> maiorCluster = new ArrayList<>();
		
		for(int i = 0; i < medias.size(); i++) {
			if(maiorMedia == medias.get(i)) {
				maiorCluster = resultadoKMeans.get(i);
			}
		}			
		
		for(int i = 0; i < this.modulos.size(); i++) {
			for(Integer valor : maiorCluster) {
				if(centralidadePorModulo.get(modulos.get(i)) == valor) {
					
					for(String arquivo : Limpeza.detectarArquivos()) {
						if(arquivo.startsWith(modulos.get(i))) {
							System.out.println(arquivo);
							modulos.remove(i);
							String[] dir = arquivo.split("/");
							
							if(dir.length > 1) {
								modulos.add(dir[0] + "/" + dir[1]);
							}
							else modulos.add(dir[0]);							
						}
					}
				}
			}			
		}
		
	}
	
	public void print() {
		
		
		principaisModulosCentralidade();
		
		for(String modulo : this.modulos) {
			ArrayList<Double> propriedadeList = new ArrayList<>();
			double soma = 0;
			
			for(Desenvolvedor d : this.desenvolvedores) {
				d.calcularPropriedade();
				
				for(String arquivo : Limpeza.detectarArquivos()) {
					if(arquivo.startsWith(modulo)) {
						soma = soma + d.getPropriedades().get(arquivo);
					}
				}
				propriedadeList.add(soma);
			}
			System.out.println("modulo " + modulo);
			this.propriedades.add(propriedadeList);
		}
	}
	
	public void save() {
		try {
			BufferedWriter escritor = new BufferedWriter(new FileWriter("teste-meu-exemplo.csv",true));				
			
			escritor.write(";");
			
			for(Desenvolvedor d : this.desenvolvedores) {
				escritor.write(d.getNome() + ";");
			}
			
			escritor.write("\n");
			
			for(int i = 0; i < propriedades.size(); i++) {
				escritor.write(this.modulos.get(i) + ";");
				for(int j = 0; j < propriedades.get(i).size(); j++) {
					escritor.write(String.valueOf(this.propriedades.get(i).get(j) + ";"));
				}
				escritor.write("\n");
			}
			
			System.out.println("escrito com sucesso.");
			escritor.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
