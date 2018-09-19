package com.br.mom;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Recursos {
	
	public static Recursos rec = null;
	
	private ArrayList<Commit> commits;
	private ArrayList<String> arquivosScanCode;
	
	public static Recursos getInstance() {
		if(rec == null) {
			rec = new Recursos();
		}
		
		return rec;
	}
	
	private Recursos() {
		commits = new ArrayList<>();
		arquivosScanCode = new ArrayList<>();
	}
	
	public ArrayList<Commit> getCommits(){
		return this.commits;
	}
	
	public ArrayList<String> getArquivosScanCode(){
		return arquivosScanCode;
	}
	
	public void criarRecursosRepoDriller(String nomeArquivo) {
		try {
			BufferedReader leitor = new BufferedReader(new FileReader(nomeArquivo));
			
			String[] linha = leitor.readLine().split(",");					
			
			Desenvolvedor autor = new Desenvolvedor(linha[6], linha[7]);
			Desenvolvedor committer = new Desenvolvedor(linha[9], linha[10]);			
			Modificacao modificacao = new Modificacao(linha[1], linha[2], linha[3], linha[4]);
			
			ArrayList<Modificacao> modificacoes = new ArrayList<>();
			modificacoes.add(modificacao);
			
			Commit commit = new Commit(Integer.parseInt(linha[0]), autor, committer, linha[12], null, null, modificacoes);			
												
			while(linha != null){	
				
				
				
				//texto = leitor.readLine();				
			}			
			
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
	public void criarRecursosScanCode() {
		
	}
}
