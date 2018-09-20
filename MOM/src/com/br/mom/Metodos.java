package com.br.mom;

public class Metodos {
	
	public static int centralidade(String arquivo) {
		
		//nessa abordagem é como se um arquivo muito alterado fosse mais importante
		
		int numDesenvolvedores = 0;
		
		for(Commit c : Recursos.getInstance().getCommits()) {
			
			for(Modificacao m : c.getModificacoes()) {
				if(arquivo.equals(m.getNomeArquivoAtual())) {
					if(c.getAutor().getNome().equals(c.getCommitter().getNome())) {						
						numDesenvolvedores++;
					}
					else numDesenvolvedores = numDesenvolvedores + 2;
					
					break;
				}
			}
		}
		
		return numDesenvolvedores;
	}

}
