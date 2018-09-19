package com.br.mom;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Recursos {
	
	public static String SEPARADOR = ",";
	
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
	
	private int transformaDigito(String s) {
		
		String aux = "";
		
		for(char c : s.toCharArray()) {
			
			if(Character.isDigit(c)){
				aux = aux + c;
			}		
		}
		
	
		return Integer.parseInt(aux);
	}
	
	private String removeSeparador(String s) {
		
		String aux = "";
		
		for(char c : s.toCharArray()) {
			
			if(!String.valueOf(c).equals(SEPARADOR) && c != '\n') {
				aux = aux + c;
			}
		}
		
		return aux;
	}
	
	private Commit lerCommit(String[] linha) {
		
		
			int hash = transformaDigito(linha[0]);
			String tipoModificacao = linha[1];
			String nomeArquivoAtual = linha[2];
			String nomeArquivoAntigo = linha[3];
			String nomeArquivoNovo = linha[4];
			String nomeAutor = linha[6];
			String emailAutor = linha[7];
			String nomeCommitter = linha[9];
			String emailCommitter = linha[10];
			//String mensagem = removeSeparador(linha[12]);		
			
			Desenvolvedor autor = new Desenvolvedor(nomeAutor, emailAutor);
			Desenvolvedor committer = new Desenvolvedor(nomeCommitter, emailCommitter);			
			Modificacao modificacao = new Modificacao(tipoModificacao, nomeArquivoAtual, nomeArquivoAntigo, 
																								nomeArquivoNovo);
			
			ArrayList<Modificacao> modificacoes = new ArrayList<>();
			modificacoes.add(modificacao);
			
			Commit c = new Commit(hash, autor, committer, null, null, null, modificacoes);	
			
			return c;
		
	}
	
	public void criarRecursosRepoDriller(String diretorioArquivo) {
		
		try {
			BufferedReader leitor = new BufferedReader(new FileReader(diretorioArquivo));
			
			String texto = leitor.readLine();	
			
			if (texto != null) {
			    texto = texto.substring (0, texto.length() - 1);
			}
									
			while(texto != null){	
								
				Commit commit;				
				String[] linha = texto.split(SEPARADOR);	
				boolean flag = false;
								
				if(linha.length != 12) {
					texto = leitor.readLine();
					
					if (texto != null) {
					    texto = texto.substring (0, texto.length() - 1);
					}
					
					continue;
				}
				
				int hash = transformaDigito(linha[0]);
				
				for(int i = 0; i < commits.size(); i++) {
					
					if(commits.get(i).getHash() == hash){
											
						String tipoModificacao = linha[1];
						String nomeArquivoAtual = linha[2];
						String nomeArquivoAntigo = linha[3];
						String nomeArquivoNovo = linha[4];
						
						Modificacao modificacao = new Modificacao(tipoModificacao, nomeArquivoAtual, 
																	     nomeArquivoAntigo, nomeArquivoNovo);
						
						commits.get(i).getModificacoes().add(modificacao);
						flag = true;
						break;
					}					
				}	
				
				if(!flag){
				
					commit = lerCommit(linha);
					if(commit != null) {
						this.commits.add(commit);
						System.out.println(commit.toString());
					}	
						
				}
							
				texto = leitor.readLine();	
				
				if (texto != null) {
				    texto = texto.substring (0, texto.length() - 1);
				}
				
			}
			
			leitor.close();
						
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
	public void criarRecursosScanCode() {
		
	}	
}
