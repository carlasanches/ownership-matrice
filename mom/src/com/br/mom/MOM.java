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
	
	public void iniciarModulos(ArrayList<String> arquivos) {		
		for(String arquivo : arquivos) {							
				
			String[] dir = arquivo.split("/");
			
			if(!this.modulos.contains(dir[0])){				
				modulos.add(dir[0]);				
			}
		}
	}
		
	public void principaisModulosCentralidade(int mediaEquipe){
		
		iniciarModulos(Recursos.getInstance().getArquivos());		

		int maiorMediaInt = 0;
		int cont = 2;
		
		//System.out.println(modulos.toString());
		
		do {
			
			Hashtable<String, Integer> centralidadePorModulo = new Hashtable<String, Integer>();
			
			for(String modulo : this.modulos) {				
				centralidadePorModulo.put(modulo, Metodos.centralidade(modulo));
			}
			
			ArrayList<Integer> centralidadeArray = new ArrayList<>(centralidadePorModulo.values());
			
//			for(String modulo : this.modulos) {
//				System.out.println(centralidadePorModulo.get(modulo) + "	" + modulo);
//			}
			
			ArrayList<ArrayList<Integer>> resultadoKMeans = new ArrayList<>();
			
			for(int k = 0; k <= 5; k++) {
				if(k < centralidadeArray.size()) {
					resultadoKMeans = Estatistica.kMeans(centralidadeArray, k);
				}			
			}
			
			double maiorMedia = 0;
			ArrayList<Double> medias = new ArrayList<>();
			
			//determina a maior média entre os clusters sem desordenar
			for(ArrayList<Integer> array : resultadoKMeans) {
				double media = Estatistica.media(array);
				medias.add(media);
				
				if(media > maiorMedia) {
					maiorMedia = Estatistica.media(array);
				}
			}
			
			maiorMediaInt = (int) maiorMedia;
			
			ArrayList<Integer> maiorCluster = new ArrayList<>();
			
			//recupera o cluster com a maior média
			for(int i = 0; i < medias.size(); i++) {
				if(maiorMedia == medias.get(i)) {
					maiorCluster = resultadoKMeans.get(i);
				}
			}		
			
			ArrayList<String> modulosCopia = new ArrayList<>();
			for(String modulo : this.modulos) {
				modulosCopia.add(modulo);
			}
			
			for(Integer valor : maiorCluster) {
				for(String modulo : modulosCopia) {
					int centralidade = centralidadePorModulo.get(modulo);
					
					if(valor == centralidade) {
						modulos.remove(modulo);
						
						for(String arquivo : Recursos.getInstance().getArquivos()) {
							if(arquivo.startsWith(modulo)) {
								String[] dir = arquivo.split("/");
						
								if(cont <= dir.length) {
									String novoDir = criaModulo(dir, cont);
									
									if(!modulos.contains(novoDir)) {
										modulos.add(novoDir);
									}
								}				
							}
						}
					}
				}
			}				
			cont++;
			
		}while(maiorMediaInt > mediaEquipe);	
		
//		for(String modulo : modulos) {
//			System.out.println(Metodos.centralidade(modulo) + "	" + modulo);
//		}
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
		
		principaisModulosCentralidade(20);
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
						
						if(d.eProprietario(0.50, 3.293, arquivo)) {
							somaPropriedades++;
						}
					}
				}
				porcentagem = somaPropriedades/somaArquivos * 100;
				
				propriedadeList.add(porcentagem);

			}
			this.propriedades.add(propriedadeList);
		}
		
		seleciona();
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
			if(soma == 0) {
				propriedades.remove(i);
				modulos.remove(i);
			}
		}
	}
	
	public void save() {
		try {
			BufferedWriter escritor = new BufferedWriter(new FileWriter("mom-v3-mockito3.csv",true));				
			
			escritor.write(";");
			
			for(Desenvolvedor d : this.desenvolvedores) {
				escritor.write(d.getNome() + ";");
			}
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
