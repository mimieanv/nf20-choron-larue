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
		boolean fichierLu = false;
		
		while(!fichierLu){
			System.out.println("Lien Url vers votre fichier : ");
			url=sc.nextLine();
			
			String toto="";
			try{
				lectureFichier = new BufferedReader(new FileReader(url));
				while ((ligne = lectureFichier.readLine()) != null){
					toto += ligne;
					
				}
				
				lectureFichier.close();
				System.out.println(toto);
				toto = toto.replaceAll(" ","");
				//toto = toto.replace('')
				System.out.println(toto);
				String tableau[] = toto.split("/");
				for(int i=0; i< tableau.length; i++){
					System.out.println(tableau[i]+"-");
				}
				
				fichierLu = true;
			}
			catch(Exception e){
				System.out.println("Le Fichier n'existe pas !");
			}
		}
	}

}
