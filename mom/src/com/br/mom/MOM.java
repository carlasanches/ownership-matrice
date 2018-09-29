package com.br.mom;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.Iterator;

public class MOM {
	
	private ArrayList<String> modulos;
	private ArrayList<Desenvolvedor> desenvolvedores;
	private ArrayList<ArrayList<Double>> propriedades;
	
	public MOM(){
		this.modulos = new ArrayList<>();
		this.desenvolvedores = Recursos.getInstance().getDesenvolvedores();
		this.propriedades = new ArrayList<>();
	}
	
	public ArrayList<String> iniciarModulos(ArrayList<String> arquivos) {	
		
		ArrayList<String> modulosList = new ArrayList<String>();
		
		for(String arquivo : arquivos) {							
				
			String[] dir = arquivo.split("/");
			
			if(!modulosList.contains(dir[0])){				
				modulosList.add(dir[0]);				
			}
		}
		
		return modulosList;
	}
	
	public void principaisModulosCentralidade(int mediaEquipe){
		
		ArrayList<String> modulosList = new ArrayList<String>();
		
		modulosList = iniciarModulos(Recursos.getInstance().getArquivos());
		
		Hashtable<String, Integer> centralidadePorModulo = new Hashtable<String, Integer>();
		
		for(String modulo : modulosList) {				
			centralidadePorModulo.put(modulo, Metodos.centralidade(modulo));
		}
		
		ArrayList<Integer> centralidadeArray = new ArrayList<>(centralidadePorModulo.values());
		
		ArrayList<ArrayList<Integer>> resultadoKMeans = new ArrayList<>();
		
		for(int k = 0; k <= 5; k++) {
			if(k < centralidadeArray.size()) {
				resultadoKMeans = Estatistica.kMeans(centralidadeArray, k);
			}			
		}
		
		double maiorMedia = 0;
		ArrayList<Integer> maiorCluster = new ArrayList<>();
		
		//recupera o cluster com a maior média
		for(ArrayList<Integer> array : resultadoKMeans) {
			double media = Estatistica.media(array);
			
			if(media > maiorMedia) {
				maiorMedia = media;
				maiorCluster = array;
			}
		}
				
		for(Integer valor : maiorCluster) {
			if(valor >= mediaEquipe) {
				for(String modulo : modulosList) {
					
					int centralidade = centralidadePorModulo.get(modulo);
					
					if(centralidade == valor) {
						
						for(String arquivo : Recursos.getInstance().getArquivos()) {
							
							String novoModulo = gerarModulo(modulo, arquivo, mediaEquipe);
							
							if(novoModulo != null) {
								String[] aux = novoModulo.split(",");
								
								for(String s : aux) {
									if(!modulos.contains(s) && s != null) {
										modulos.add(s);
									}
								}
							}							
						}						
					}
				}
			}
		}		
		
		System.out.println(modulos.toString());
	}
	
	public String gerarModulo(String modulo, String arquivo, int mediaEquipe){
		
		if(arquivo.startsWith(modulo)) {
			
			if(arquivo.equals(modulo)) {
				return arquivo;
			}
			
			String[] moduloSplit = modulo.split("/");
			String[] arquivoSplit = arquivo.split("/");
			
			String novoModulo = arquivo.substring(0, (modulo.length() + 1) + arquivoSplit[moduloSplit.length].length());
			int centralidade = Metodos.centralidade(novoModulo);
			
			if(centralidade >= mediaEquipe) {		
				return novoModulo + "," + gerarModulo(novoModulo, arquivo, mediaEquipe);
			}	
		}		
		
		return null;
	}
	
	public String criaModulo(String[] dir, int nivelDir) {
		
		String novoDir = "";
		
		if(dir.length == 1) return dir[0];
		if(dir.length > 1) {
			for(int i = 0; i < nivelDir; i++) {
				novoDir += dir[i];				
				if(i < (nivelDir-1)) novoDir += "/";
			}			
		}
		
		return novoDir;
	}
	
	public void calcularPropriedade() {
		
		principaisModulosCentralidade(10);
		ArrayList<Double> propriedadeList;
		
		for(Desenvolvedor d : this.desenvolvedores) {
			d.calcularPropriedade();
		}
		
		for(String modulo : this.modulos) {
			propriedadeList = new ArrayList<>();
			for(Desenvolvedor d : this.desenvolvedores) {				
				double somaArquivos = 0;
				double somaPropriedades = 0;
				double porcentagem = 0;
				
				for(String arquivo : Recursos.getInstance().getArquivos()) {
					if(arquivo.startsWith(modulo)) {
						somaArquivos++;
						
						if(d.eProprietario(0.8, 2.0, arquivo)) {
							somaPropriedades++;
						}
					}
				}
				porcentagem = somaPropriedades/somaArquivos * 100;
				
				propriedadeList.add(porcentagem);

			}
			this.propriedades.add(propriedadeList);
		}
		
		//seleciona();
	}
	
	public void seleciona() {
		
		int soma;
		
		for(int i = 0; i < propriedades.size(); i++) {
			soma = 0;
			for(int j = 0; j < propriedades.get(i).size(); j++) {
				if(propriedades.get(i).get(j) > 0) {
					soma++;
				}
			}
			if(soma == 0 || soma < 80) {
				propriedades.remove(i);
				modulos.remove(i);
			}
		}
	}
	
	public void save() {
		try {
			BufferedWriter escritor = new BufferedWriter(new FileWriter("mom-v4-mockito-10-9.csv",true));				
			
			escritor.write(";");
			
			for(Desenvolvedor d : this.desenvolvedores) {
				escritor.write(d.getNome() + ";");
			}
			
			escritor.write("\n");
			
			escritor.write(";");
			
			for(Desenvolvedor d : this.desenvolvedores) {
				escritor.write(d.getEmail() + ";");
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
