package nf20;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

import structure.Arc;
import structure.Graph;
import structure.Node;
import Algos.Bellman;
import Algos.Dijkstra;
import Algos.Ordonnencement;

public class Main {
	 
	/**
	 * @param args
	 */

	
	public static void main(String[] args) {
		
		
		
		
		ArrayList<Arc> listArc		= new ArrayList<Arc>();
		ArrayList<Node> listNode	= new ArrayList<Node>();
		
		Graph graphe=null;
		
		
		//Scanner sc = new Scanner(System.in);
		BufferedReader lectureFichier = null;
		String url="";
		String ligne;
		Scanner sc = new Scanner(System.in);
		boolean fichierLu = false;
		
		System.out.println("Que voulez vous faire?");
		System.out.println("Pour dijkstra ou Bellman Tapez 1");
		System.out.println("Pour l'ordonnencement Tapez 2");
		int choixDepart = sc.nextInt();
		
		if(choixDepart == 1){
		
			while(!fichierLu){
	
				url="C:\\Users\\N33d-W33d\\Desktop\\jeux_d_essai\\check_v5_s2.dat";			
				
				String fichier="";
				try{
					lectureFichier = new BufferedReader(new FileReader(url));
					while ((ligne = lectureFichier.readLine()) != null){
						ligne = ligne.replaceAll(" ", "");
						fichier += ligne+"-";
						
					}
					
					lectureFichier.close();
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
					for(int k=0; k<nbNodes; k++){
						listNode.add(new Node(k));
					}
					
					//Creation des arcs
					for(int j=indiceDepart; j<tableau.length; j++){
						if(tableau[j].equals("END")){
							break;
						}
						//Tous les 3 elements
						if((j-indiceDepart)%3 == 0 || j-indiceDepart == 0){
							//on crï¿½e un nouvel arc(node depart, node Arrivï¿½, int cost)
							//On l'ajoute ï¿½ la listeArc
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
		
			System.out.println("Quelle algorithm voulez-vous faire ?");
			System.out.println("Pour Dijkstra : tapez 1");
			System.out.println("Pour Bellman : tapez 2");
			int choix = sc.nextInt();
			if(choix == 1){
				System.out.println("Choisissez le noeud de départ ?");
				
				Dijkstra dijkstra = new Dijkstra(sc.nextInt(),graphe);
				dijkstra.calculerDijkstra();
				dijkstra.afficher();
			}
		    if(choix == 2){
		    	System.out.println("Choisissez le noeud de départ ?");
				
				Bellman belmann = new Bellman(sc.nextInt(),graphe);
				
				if(belmann.algoBellman()) {
					
					belmann.afficher();
					
				}
				else{
					System.out.println("Impossible de faire l'algorithme de Bellman car il y a un cycle absorbant");
				}
			}
		}
		//Ordonnencement
		else{
			//LES ARCS DEVIENNENTS LES CONSTRAINTES ET LES NOEUDS SONT MAINTENANT LES TACHES
			while(!fichierLu){
			
				url="C:\\Users\\N33d-W33d\\Desktop\\jeux_d_essai\\instance_projet1.dat";			
				
				String fichier="";
				try{
					lectureFichier = new BufferedReader(new FileReader(url));
					while ((ligne = lectureFichier.readLine()) != null){
						ligne = ligne.replaceAll(" ", "");
						fichier += ligne+"-";
						
					}
					
					lectureFichier.close();
					fichier = fichier.replaceAll("-","/");
					fichier = fichier.replaceAll("\t","/");
					fichier = fichier.replaceAll("//","/");
	
					String tableau[] = fichier.split("/");
					
					int nbNodes			= 0;
					int nbArcs			= 0;
					int indiceDepart	= 0;
				
					
					for(int i=0; i< tableau.length; i++){
						if(tableau[i].equalsIgnoreCase("nb_tasks")){
							//+2 pour le départ est l'arrivée du coup on choisit de dire que le départ c'est 0 et l'arrivé nbNode+1 et on decale tout de 1
							nbNodes = Integer.parseInt(tableau[i+1]) + 2;
							
						}
						else if(tableau[i].equalsIgnoreCase("nb_constraints")){
							nbArcs = Integer.parseInt(tableau[i+1]);
							
						}
						else if(tableau[i].endsWith("DURATION")){
							indiceDepart = i+1;
							//break;
						}
						
					}
					
					//creation des noeuds
					for(int k=0; k<nbNodes; k++){
						listNode.add(new Node(k));	
					}
					
					
					
					
					for(int l=indiceDepart; l<tableau.length;l = l+2){
						if(tableau[l].equalsIgnoreCase("constraints")){
							indiceDepart=l+1;
							break;
						}
						listNode.get(Integer.parseInt(tableau[l])+1).setDuree(Integer.parseInt(tableau[l+1]));
					}
										
					//Arc
					
					for(int j=indiceDepart; j<tableau.length; j=j+2){
						if(tableau[j].equals("END")){
							break;
						}
						
						listArc.add(new Arc(listNode.get(Integer.parseInt(tableau[j])+1),listNode.get(Integer.parseInt(tableau[j+1])+1),listNode.get(Integer.parseInt(tableau[j])+1).getDuree()));				
						listNode.get(Integer.parseInt(tableau[j+1])+1).setHaveConstraint(true);
						listNode.get(Integer.parseInt(tableau[j])+1).setTacheFin(false);
					}
					
					
					fichierLu = true;
					
					for(Node n : listNode){
						if(!n.haveConstraint() && n.getNumber()!=0 && n.getNumber()!=nbNodes-1){
							listArc.add(new Arc(listNode.get(0),n,0));
						}
						if(n.isTacheFin()&&n.getNumber()!=0&&n.getNumber()!=nbNodes-1){
							listArc.add(new Arc(n,listNode.get(nbNodes-1),n.getDuree()));
						}
					}
					
					/*for(Arc c :listArc){
						System.out.println("[" + c.getStartNode().getNumber() + "->"+c.getEndNode().getNumber() +"] = " + c.getCost());
					}*/
					
					
					
					
					
					// On "definit" le graphe
					graphe = Graph.getInstance();
					
					graphe.setNbNodes(nbNodes);
					graphe.setNbArcs(nbArcs);
					
					graphe.setListArc(listArc);
					graphe.setListNode(listNode);
					
					
					Ordonnencement ord = new Ordonnencement(graphe);
					ord.algoOrdo();
					ord.afficher();
					
				}
				catch(Exception e) {
					System.out.println("Le Fichier n'existe pas !");
				}
				
			}
			
		}
	}

}
