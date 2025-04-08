package Atividade2;

import java.io.FileNotFoundException;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Busca b = new Busca();
		
		String[] chaves = { "GUIDO", "ADA", "MATIAS", "ROBERT", "PEDREIRO" };

	    for (String chave : chaves) {
	    	new Thread(() -> {
	    		try {
	    			b.buscar(chave);
	    		} catch (FileNotFoundException e) {
	    			e.printStackTrace();
	    		}
	    	}, "Thread-" + chave).start();
	    }
		
	}

}
