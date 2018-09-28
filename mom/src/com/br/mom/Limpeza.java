package com.br.mom;

import java.util.ArrayList;

public class Limpeza {
	
	public static void alterarDiretoriosRenomeados() {
				
		for(Commit c : Recursos.getInstance().getCommits()) {
			for(Modificacao m : c.getModificacoes()) {
				if(m.getTipo().equals("RENAME")) {										
					for(Commit c1 : Recursos.getInstance().getCommits()) {
						for(Modificacao m1 : c1.getModificacoes()) {
							if(m1.getNomeArquivoAtual().equals(m.getNomeArquivoAntigo())) {										
								m1.setNomeArquivoAtual(m.getNomeArquivoAtual());								
							}
						}
					}
				}
			}
		}
	}
}
