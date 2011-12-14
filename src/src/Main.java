import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
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
			//System.out.println("Lien Url vers votre fichier : ");
			//url=sc.nextLine();
			url="C:\\Users\\N33d-W33d\\Documents\\eclipse\\eclipse\\workspace\\nf20-choron-larue\\instance_PCC.dat";
			
			String fichier="";
			try{
				lectureFichier = new BufferedReader(new FileReader(url));
				while ((ligne = lectureFichier.readLine()) != null){
					fichier += ligne+"-";
					
				}
				
				lectureFichier.close();
				fichier = fichier.replaceAll(" ","");
				fichier = fichier.replaceAll("-","/");
				fichier = fichier.replaceAll("\t","/");
				String tableau[] = fichier.split("/");
				int nbrNodes = 0;
				int nbrArcs = 0;
				int indiceDepart =0;
				ArrayList<Arc> listArc = new ArrayList<Arc>();
				ArrayList<Node> listNode = new ArrayList<Node>();
				
				
				for(int i=0; i< tableau.length; i++){
					if(tableau[i].equalsIgnoreCase("nb_nodes")){
						nbrNodes = Integer.parseInt(tableau[i+1]);
					}
					else if(tableau[i].equalsIgnoreCase("nb_arcs")){
						nbrArcs = Integer.parseInt(tableau[i+1]);
					}
					else if(tableau[i].startsWith("LIST")){
						indiceDepart = i+1;
					}
				}
				//creation des noeuds
				for(int k=0; k<nbrArcs;k++){
					listNode.add(new Node(k));
				}
				
				//Creation des arcs
				for(int j=indiceDepart;j<tableau.length;j++){
					if(tableau[j].equals("END")){
						break;
					}
					//Tout les 3 elements
					if((j-indiceDepart)%3 == 0){
						//on cr�e un nouvel arc(node depart, node Arriv�, int cost)
						//On l'ajoute � la listeArc
						listArc.add(new Arc(listNode.get(Integer.parseInt(tableau[j])),listNode.get(Integer.parseInt(tableau[j+1])),Integer.parseInt(tableau[j+2])));
					}
									
				}
				
				fichierLu = true;
			}
			catch(Exception e){
				System.out.println("Le Fichier n'existe pas !");
			}
		}
		
		
	}

}
