package nf20;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

import structure.Arc;
import structure.Graph;
import structure.Node;
import Algos.Bellman;
import Algos.Dijkstra;

public class Main {
	 
	/**
	 * @param args
	 */

	
	public static void main(String[] args) {
		ArrayList<Arc> listArc		= new ArrayList<Arc>();
		ArrayList<Node> listNode	= new ArrayList<Node>();
		
		Graph graphe=null;
		Dijkstra dijkstra;
		
		//Scanner sc = new Scanner(System.in);
		BufferedReader lectureFichier = null;
		String url="";
		String ligne;
		boolean fichierLu = false;
		
		
		while(!fichierLu){
			//System.out.println("Lien Url vers votre fichier : ");
			//url=sc.nextLine();

			url="C:\\Users\\N33d-W33d\\Desktop\\jeux_d_essai\\check_v5_s2.dat";			
			//url="C:\\Users\\N33d-W33d\\Documents\\eclipse\\eclipse\\workspace\\nf20-choron-larue\\instance_PCC.dat";
			//url = "D:\\COURS\\LO02\\CrazyEights\\nf20-choron-larue\\instance_PCC.dat";

			
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
				fichier = fichier.replaceAll("//","/");

				String tableau[] = fichier.split("/");
				
				int nbNodes			= 0;
				int nbArcs			= 0;
				int indiceDepart	= 0;
			
				
				for(int i=0; i< tableau.length; i++){
					if(tableau[i].equalsIgnoreCase("nb_nodes")){
						nbNodes = Integer.parseInt(tableau[i+1]);
						
					}
					else if(tableau[i].equalsIgnoreCase("nb_arcs")){
						nbArcs = Integer.parseInt(tableau[i+1]);
						
					}
					else if(tableau[i].endsWith("COSTS") || tableau[i].endsWith("COST")){
						indiceDepart = i+1;
					}
				}
				//creation des noeuds
				for(int k=0; k<nbArcs; k++){
					listNode.add(new Node(k));
				}
				
				//Creation des arcs
				for(int j=indiceDepart; j<tableau.length; j++){
					if(tableau[j].equals("END")){
						break;
					}
					//Tous les 3 elements
					if((j-indiceDepart)%3 == 0 || j-indiceDepart == 0){
						//on cr�e un nouvel arc(node depart, node Arriv�, int cost)
						//On l'ajoute � la listeArc
						listArc.add(new Arc(listNode.get(Integer.parseInt(tableau[j])),listNode.get(Integer.parseInt(tableau[j+1])),Integer.parseInt(tableau[j+2])));
					}
									
				}
				fichierLu = true;
				
				// On "definit" le graphe
				graphe = Graph.getInstance();
				
				graphe.setNbNodes(nbNodes);
				graphe.setNbArcs(nbArcs);
				
				graphe.setListArc(listArc);
				graphe.setListNode(listNode);
			}
			catch(Exception e) {
				System.out.println("Le Fichier n'existe pas !");
			}
			
		}
		
		/*for(Arc monArc : listArc){
			System.out.println(monArc.toString());
		}*/
		
		/*
		 * TEST DIJKSTRA
		 */
		//graphe = Graph.getInstance();
		dijkstra = new Dijkstra(0,Graph.getInstance());
		dijkstra.algoWikipedia();
		
		/*
		 * TEST BELLMAN
		 * */
		
		/*Bellman belmann = new Bellman(0, graphe);
		
		if(belmann.algoBellman()) {
			
			belmann.afficher();
			
		}
		else{
			System.out.println("Impossible de faire l'algorithme de Bellman car il y a un cycle absorbant");
		}*/
	}

}
