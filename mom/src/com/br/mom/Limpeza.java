package com.br.mom;

import java.util.ArrayList;

public class Limpeza {
	
	public static ArrayList<Desenvolvedor> detectarDesenvolvedores(){
		
		ArrayList<Desenvolvedor> desenvolvedores = new ArrayList<>();
		boolean flag = false;
		
		for(Commit c : Recursos.getInstance().getCommits()) {
			
			for(int i = 0; i < desenvolvedores.size(); i++) {
				if(desenvolvedores.get(i).getNome().equals(c.getAutor().getNome())) {
					flag = true;
					break;
				}
			}
			
			if(!flag) {
				desenvolvedores.add(c.getAutor());
			}
			
			flag = false;
			
			for(int i = 0; i < desenvolvedores.size(); i++) {
				if(desenvolvedores.get(i).getNome().equals(c.getCommitter().getNome())) {
					flag = true;
					break;
				}
			}
			
			if(!flag) {
				desenvolvedores.add(c.getCommitter());
			}
			
			flag = false;
		}
		
		return desenvolvedores;		
	}
	
	public static ArrayList<String> detectarArquivos(){
		
		ArrayList<String> arquivos = new ArrayList<String>();
		boolean flag;
		
		for(Commit c: Recursos.getInstance().getCommits()) {
			
			flag = false;
			
			for(Modificacao m : c.getModificacoes()) {
				for(int i = 0; i < arquivos.size(); i++) {
					if(arquivos.get(i).equals(m.getNomeArquivoAtual())) {
						flag = true;
						break;
					}
				}
				
				if(!flag) {
					arquivos.add(m.getNomeArquivoAtual());					
				}
			}	
		}
		
		return arquivos;
	}
}
