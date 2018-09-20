package com.br.mom;

import java.util.ArrayList;
import java.util.Hashtable;

public class MOM {
	
	private ArrayList<String> modulos;
	private ArrayList<Desenvolvedor> desenvolvedores;
	private ArrayList<Integer> propriedades;
	
	public MOM(){
		this.modulos = new ArrayList<>();
		this.desenvolvedores = Limpeza.detectarDesenvolvedores();
		this.propriedades = new ArrayList<>();
	}
	
	
	
	public ArrayList<String> principaisModulosCentralidade(){
		
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
				
		return null;
	}
	
}
