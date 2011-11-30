import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;


public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		BufferedReader lectureFichier = null;
		String url="";
		String ligne;
		
		System.out.println("Lien Url vers votre fichier : ");
		url=sc.nextLine();
		
		try{
			lectureFichier = new BufferedReader(new FileReader(url));
			while ((ligne = lectureFichier.readLine()) != null){
				System.out.println(ligne);
				
			}
			lectureFichier.close();

		}
		catch(Exception e){
		
		}
	}

}
