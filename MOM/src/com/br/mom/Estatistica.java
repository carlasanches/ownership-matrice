package com.br.mom;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class Estatistica {
	
	public static int[][] kMeans(ArrayList<Integer> amostra, int k){
		
		ArrayList<Integer> centroids = new ArrayList<>();
		
		Collections.shuffle(amostra);
		
		for(int i = 0; i < k; i++) {
			centroids.add(amostra.get(i));
		}
		System.out.println(amostra.toString());
		System.out.println(centroids.toString());		
		
		return null;
	}

}
