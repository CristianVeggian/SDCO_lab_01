/**
 * Lab0: Leitura de Base de Dados N�o-Distribuida
 * 
 * Autor: Lucio A. Rocha
 * Ultima atualizacao: 20/02/2023
 * 
 * Referencias: 
 * https://docs.oracle.com/javase/tutorial/essential/io
 * 
 */

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Principal_v0 {

	public final static Path path = Paths			
			.get("src\\fortune-br.txt");
	private int NUM_FORTUNES = 0;

public class FileReader {

		public int countFortunes() throws FileNotFoundException {

			int lineCount = 0;

			InputStream is = new BufferedInputStream(new FileInputStream(
					path.toString()));
			try (BufferedReader br = new BufferedReader(new InputStreamReader(
					is))) {

				String line = "";
				while (!(line == null)) {

					if (line.equals("%"))
						lineCount++;

					line = br.readLine();

				}// fim while

				//System.out.println(lineCount);
			} catch (IOException e) {
				System.out.println("SHOW: Excecao na leitura do arquivo.");
			}
			return lineCount;
		}

		//Lê todo o arquivo
		public void parser(HashMap<Integer, String> hm)
				throws FileNotFoundException {

			InputStream is = new BufferedInputStream(
					new FileInputStream(
							path.toString()));
			try (BufferedReader br = new BufferedReader(new InputStreamReader(is))) {

				int lineCount = 0;

				String line = "";
				while (!(line == null)) {

					if (line.equals("%"))
						lineCount++;

					line = br.readLine();
					StringBuffer fortune = new StringBuffer();
					while (!(line == null) && !line.equals("%")) {
						fortune.append(line + "\n");
						line = br.readLine();
					}

					hm.put(lineCount, fortune.toString());
					//System.out.println(fortune.toString());

					//System.out.println(lineCount);
				}

			} catch (IOException e) {
				System.out.println("SHOW: Excecao na leitura do arquivo.");
			}
		}

		public void read(HashMap<Integer, String> hm)
				throws FileNotFoundException {
			
			
			// Escolher número aleatório
			// Usar esse numero como chave do HasMap
			
			SecureRandom sr = new SecureRandom();
			
			int numAleatorio = sr.nextInt(NUM_FORTUNES);
			
			System.out.print(hm.get(numAleatorio));
			
		}

		public void write(HashMap<Integer, String> hm, String novaFortuna)
				throws IOException {

			hm.put(++NUM_FORTUNES, novaFortuna);
			
			try {
		        Writer wr = new FileWriter(path.toString(), true); // criação de um escritor
		        BufferedWriter br = new BufferedWriter(wr); // adiciono a um escritor de buffer
		        
		        br.newLine();
		        br.write(novaFortuna);
		        br.newLine();
		        br.write("%");
		        br.close();
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void iniciar() {

		FileReader fr = new FileReader();
		try {
			this.NUM_FORTUNES = fr.countFortunes();
			HashMap<Integer, String> hm = new HashMap<Integer, String>();
			fr.parser(hm);
			fr.read(hm);
			fr.write(hm, "De acordo com os padrões atuais, Jesus Cristo foi a primeira pessoa a ser vítima da cultura do cancelamento");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		new Principal_v0().iniciar();
		
	}

}
