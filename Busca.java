package Atividade2;
import java.util.concurrent.Semaphore;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Busca {
	private Semaphore sem = new Semaphore(2);
	private Explorador exp = new Explorador();
	
	
	public void buscar(String chave) throws FileNotFoundException {
		String nome = Thread.currentThread().getName();
		try {
			sem.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		boolean achou = false;
		String arquivoEncontrou = "";
		String linhaEncontrada = "";
		int k = 0;
		
		System.out.println("A Thread "+nome+" está buscando pela chave "+chave+"...");
		
		ArrayList<File> caminhos = exp.procurar("C:\\Users\\jukal\\Desktop\\nomes", ".txt");
		
		for(int i = 0; i < caminhos.size(); i++) {
			if(!achou) {
				k = 0;
			}
			Scanner in = new Scanner(new FileReader(caminhos.get(i)));
			while (in.hasNextLine() && !achou) {
				k++;
			    String line = in.nextLine();
			    if(line.toLowerCase().contains(chave.toLowerCase())) {
			    	achou = true;
			    	arquivoEncontrou = caminhos.get(i).toString();
			    	linhaEncontrada = line;
			    }
			}
			in.close();
		}
		
		if(achou) {
			System.out.println("A chave "+chave+" foi encontrada pela Thread " +nome+ " no arquivo "+arquivoEncontrou+ " na linha "+k+ "."+ " Conteúdo da linha: "+linhaEncontrada+".");
		} else {
			System.out.println("A Thread "+nome+" não encontrou a chave "+chave+".");
		}
		
		sem.release();
	}
	
}
