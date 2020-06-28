package src;

import java.util.ArrayList;
import java.util.Collections;

public class Estatistica {
	
	public static ArrayList<ArrayList<Integer>> kMeans(ArrayList<Integer> amostra, int k){
		
		ArrayList<Integer> amostraCopia = new ArrayList<>();
		
		for(Integer i : amostra) {
			amostraCopia.add(i);
		}
		
		ArrayList<Double> centroids = new ArrayList<>();
		ArrayList<ArrayList<Integer>> clusters = new ArrayList<>();
		ArrayList<ArrayList<Integer>> clustersCopia = new ArrayList<>();
		
		//seleciona k centroids aleat�rios e inicializa clusters
		Collections.shuffle(amostraCopia);
		
		for(int i = 0; i < k; i++) {			
			double centroid = amostraCopia.get(i);			
			centroids.add(centroid);
			ArrayList<Integer> cluster = new ArrayList<>();
			ArrayList<Integer> clusterCopia = new ArrayList<>();
			clusters.add(cluster);
			clustersCopia.add(clusterCopia);
		}
				
		Collections.sort(amostra);		
		int maiorValor = amostra.get(amostra.size()-1);
		
		int parada = 10;
		
		while(parada != 0) {
						
			//calcula a dist�ncia dos valores da amostra para os centroids
			for(Integer valor : amostra) {				
				
				double menorDistancia = maiorValor;
				
				ArrayList<Double> distanciaPorValor = new ArrayList<>();
				
				//adiciona no vetor a dist�ncia do valor para cada centroid
				for(Double centroid : centroids) {
					double valorDouble = valor;
					distanciaPorValor.add(distanciaEuclidiana(centroid, valorDouble));
				}		
				
				//determina a menor dist�ncia do vetor sem perder a ordena��o
				for(int i = 0; i < distanciaPorValor.size(); i++) {
					if(distanciaPorValor.get(i) < menorDistancia) {
						menorDistancia = distanciaPorValor.get(i);
					}
				}
				
				
				//atribui valor ao cluster do mesmo �ndice da menor dist�ncia
				for(int i = 0; i < distanciaPorValor.size(); i++) {
					if(menorDistancia == distanciaPorValor.get(i)) {
						clusters.get(i).add(valor);
					}
				}
			}
			
			centroids.clear();
			clustersCopia = clusters;
					
			//calcula a m�dia de cada cluster e cria novos centroids
			for(int i = 0; i < clusters.size(); i++) {				
				centroids.add(media(clusters.get(i)));
			}
			
			//reinicia clusters para nova movimenta��o
			clusters = new ArrayList<>();
			for(int i = 0; i < k; i ++) {
				ArrayList<Integer> cluster = new ArrayList<>();
				clusters.add(cluster);
			}
			
			parada--;
		}
								
		return clustersCopia;
	}
	
	public static double distanciaEuclidiana(Double num1, Double num2) {
		
		return Math.sqrt(Math.pow((num1 - num2), 2));	
	}
	
	public static double media(ArrayList<Integer> valores) {
		
		double soma = 0;
		
		for(Integer i : valores) {
			soma = soma + i;
		}
		
		return soma/valores.size();
	}
	
	public static double mediaD(ArrayList<Double> valores) {
		
		double soma = 0;
		
		for(Double d : valores) {
			soma = soma + d;
		}
		
		return soma/valores.size();
	}
}
