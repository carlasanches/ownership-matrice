package src;

import java.util.ArrayList;

public class Metodos {
	
	public static int firstAutorship(String md, String fp) {
		
		for(Commit c : Recursos.getInstance().getCommits()) {
			if(c.getAutor().getNome().equals(md)) {
				for(Modificacao m : c.getModificacoes()) {
					if(m.getNomeArquivoAtual().equals(fp) && m.getTipo().equals("ADD")) {
						return 1;
					}
				}
			}
		}
		
		return 0;
	}
	
	public static int deliveries(String md, String fp) {
		
		int numDeliveries = 0;
		
		for(Commit c : Recursos.getInstance().getCommits()) {
			if(c.getAutor().getNome().equals(md) || c.getCommitter().getNome().equals(md)) {
				for(Modificacao m : c.getModificacoes()) {
					if(m.getNomeArquivoAtual().startsWith(fp)) {
						numDeliveries++;
					}
				}
			}
		}
		
		return numDeliveries;
	}
	
	public static int acceptances(String md, String fp) {
		int numAcceptances = 0;
		
		for(Commit c : Recursos.getInstance().getCommits()) {
			if(!c.getAutor().getNome().equals(md) && !c.getCommitter().getNome().equals(md)) {
				for(Modificacao m : c.getModificacoes()) {
					if(m.getNomeArquivoAtual().equals(fp)) {
						numAcceptances++;
					}
				}
			}
		}
		
		return numAcceptances;
	}
	
	public static double doa(String md, String fp) {
		return 3.293 + 1.098 * firstAutorship(md, fp) + 0.164 * deliveries(md, fp) - 0.321 * Math.log(1 + acceptances(md, fp));		
	}
	
	public static int centralidade(String modulo) {
				
		int numDesenvolvedores = 0;
		
		for(Desenvolvedor d : Recursos.getInstance().getDesenvolvedores()) {
			if(deliveries(d.getNome(), modulo) > 0) {
				numDesenvolvedores++;
			}
		}
		
		return numDesenvolvedores;
		
	}

}
