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
		
		for(String modulo : modulosList) {
			
			int centralidade = centralidadePorModulo.get(modulo);
			System.out.println(centralidade);
			
			if(centralidade >= mediaEquipe) {
				
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
						
	public String gerarModulo(String modulo, String arquivo, int mediaEquipe){
		
		if(arquivo.startsWith(modulo)) {
						
			if(arquivo.equals(modulo)) {
				return arquivo;
			}
			
			String[] moduloSplit = modulo.split("/");
			String[] arquivoSplit = arquivo.split("/");
			
			try {
				String novoModulo = arquivo.substring(0, (modulo.length() + 1) + arquivoSplit[moduloSplit.length].length());					
				int centralidade = Metodos.centralidade(novoModulo);
				
				if(centralidade >= mediaEquipe) {		
					return novoModulo + "," + gerarModulo(novoModulo, arquivo, mediaEquipe);
				}
			}
			catch(Exception e) {
				return null;
			}
		}		
		
		return null;
	}
	
	public void calcularPropriedade() {
		
		principaisModulosCentralidade(5);
		ArrayList<Double> propriedadeList;
		
		for(Desenvolvedor d : this.desenvolvedores) {
			d.calcularPropriedade();
		}
		
		ArrayList<ArrayList<Double>> propriedadesArquivos = new ArrayList<>();
 		
		for(String arquivo : Recursos.getInstance().getArquivos()) {	
			ArrayList<Double> propriedadesArquivo = new ArrayList<>();
			for(Desenvolvedor d : this.desenvolvedores) {
				propriedadesArquivo.add(d.getPropriedades().get(arquivo));
			}	
			propriedadesArquivos.add(propriedadesArquivo);
		}
		
		ArrayList<ArrayList<Double>> propriedadesArquivosNormalizadas = new ArrayList<>();
		
		for(ArrayList<Double> propriedadesArquivoList : propriedadesArquivos) {
			ArrayList<Double> propriedadesNormalizadas = new ArrayList<>();
			propriedadesNormalizadas = Normalizacao.normalizar(propriedadesArquivoList);
			propriedadesArquivosNormalizadas.add(propriedadesNormalizadas);
		}		
		
		Hashtable<String, ArrayList<Desenvolvedor>> hashProprietarios = new Hashtable<>();
				
		for(String modulo : this.modulos) {
			
			propriedadeList = new ArrayList<>();
			for(int i = 0; i < this.desenvolvedores.size(); i++) {				
				double somaArquivos = 0;
				double somaPropriedades = 0;
				double porcentagemI = 0;
				double porcentagem = 0;
				double porcentagemArquivo = 0;
				
				
				for(int j = 0; j < Recursos.getInstance().getArquivos().size(); j ++) {
					if(Recursos.getInstance().getArquivos().get(j).startsWith(modulo)) {
						somaArquivos++;						
						
						if(this.desenvolvedores.get(i).eProprietario(0.75, 3.293, 
									propriedadesArquivosNormalizadas.get(j).get(i), propriedadesArquivos.get(j).get(i))) {							
							somaPropriedades++;
							
							if(!this.desenvolvedores.get(i).getArquivos().contains(Recursos.getInstance().getArquivos().get(j))) {
								this.desenvolvedores.get(i).getArquivos().add(Recursos.getInstance().getArquivos().get(j));
							}							
							
							if(!hashProprietarios.containsKey(Recursos.getInstance().getArquivos().get(j))) {
								hashProprietarios.put(Recursos.getInstance().getArquivos().get(j), new ArrayList<>());
								hashProprietarios.get(Recursos.getInstance().getArquivos().get(j)).add(this.desenvolvedores.get(i));
							}
							else if(!hashProprietarios.get(Recursos.getInstance().getArquivos().get(j))
																			.contains(this.desenvolvedores.get(i))) {
								
								hashProprietarios.get(Recursos.getInstance().getArquivos().get(j)).
																			add(this.desenvolvedores.get(i));
							}
						}
					}					
				}	
								
				porcentagemI = somaPropriedades/somaArquivos * 100;
				porcentagemArquivo = 100/somaArquivos;
				double divisao = 0;
				double cont = 0;
				
				if(porcentagemI >= 5) {
					for(String arquivo : this.desenvolvedores.get(i).getArquivos()) {
						if(arquivo.startsWith(modulo) && hashProprietarios.get(arquivo) != null) {	
							
							divisao = (1.0/hashProprietarios.get(arquivo).size());
							
							for(Desenvolvedor dev : hashProprietarios.get(arquivo)) {
								int x = this.desenvolvedores.indexOf(dev);
								
								this.desenvolvedores.get(x).getTemPropriedade().put(arquivo, divisao);
								double aux = 0;
								
								if(x < propriedadeList.size()) {
									for(String arq : this.desenvolvedores.get(x).getArquivos()) {
										if(arq.startsWith(modulo)) {
											
											if(this.desenvolvedores.get(x).getTemPropriedade().get(arq) != null)
											aux = aux + this.desenvolvedores.get(x).getTemPropriedade().get(arq);
										}
									}
																
									double porcentNova = aux * porcentagemArquivo;
									propriedadeList.set(x, porcentNova);	
								}						
							}
							cont = cont + divisao;							
						}
					}
			
					
				}
				
				
				porcentagem = cont * porcentagemArquivo;				
				propriedadeList.add(porcentagem);

			}
			this.propriedades.add(propriedadeList);
		}
			
	}
	
	public void save(String nomeArquivo) {
		try {
			BufferedWriter escritor = new BufferedWriter(new FileWriter(nomeArquivo,true));				
			
			escritor.write(",");
			
			for(Desenvolvedor d : this.desenvolvedores) {
				escritor.write(d.getNome() + ",");
			}
			
			escritor.write("\n");
			
			escritor.write(",");
			
			for(Desenvolvedor d : this.desenvolvedores) {
				escritor.write(d.getEmail() + ",");
			}
			
			escritor.write("\n");
			
			for(int i = 0; i < propriedades.size(); i++) {
				escritor.write(this.modulos.get(i) + ",");
				for(int j = 0; j < propriedades.get(i).size(); j++) {
					escritor.write(String.valueOf(this.propriedades.get(i).get(j) + ","));
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
