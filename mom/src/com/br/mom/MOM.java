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
			System.out.println(modulo);
			
			propriedadeList = new ArrayList<>();
			for(int i = 0; i < this.desenvolvedores.size(); i++) {				
				double somaArquivos = 0;
				double somaPropriedades = 0;
				double porcentagemI = 0;
				double porcentagem = 0;
				double porcentagemArquivo = 0;
				
				int test = 0;
				
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
						System.out.println("soma: " + somaArquivos + " qtd: " + this.desenvolvedores.get(i).getArquivos().size());
						if(arquivo.startsWith(modulo)) {
							int num = hashProprietarios.get(arquivo).size();
							//System.out.println(hashProprietarios.get(arquivo).toString());
							divisao = (1.0/hashProprietarios.get(arquivo).size());
							
							for(Desenvolvedor dev : hashProprietarios.get(arquivo)) {
								int x = this.desenvolvedores.indexOf(dev);
								
//								if(x < propriedadeList.size()) {
//									double contNovo = this.desenvolvedores.get(i).getArquivos().size() - (1 - divisao);
//									double porcentNova = contNovo * porcentagemArquivo;
//									propriedadeList.set(x, porcentNova);							
//								}								
								
								this.desenvolvedores.get(x).getTemPropriedade().put(arquivo, divisao);
								double var = desenvolvedores.get(x).getTemPropriedade().get(arquivo);
								double aux = 0;
								
								if(x < propriedadeList.size()) {
									for(String arq : this.desenvolvedores.get(x).getArquivos()) {
										if(arq.startsWith(modulo)) {
											aux = aux + this.desenvolvedores.get(x).getTemPropriedade().get(arq);
										}
									}
									
									double t = propriedadeList.get(x);									
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
		
		//System.out.println(hashProprietarios.toString());
		
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
