package Algos;

import structure.Arc;
import structure.Graph;


public class Bellman {

	
	private int startNode;
	private Graph graphe;
	private CoutMinimumBellman[] tabMinCost;
	private CheminMinimum[] tabCheminMini;
	
	public Bellman(int startNode, Graph graphe) {
		this.startNode = startNode;
		this.graphe = graphe;
		//initialisation du tableau pour les couts minimums � une taille �gale au nombre de noeuds
		tabMinCost = new CoutMinimumBellman[graphe.getListNode().size()];
		
		//Tout les noeuds sont initialis� � infini
		for(int i = 0; i<graphe.getListNode().size(); i++) {
			tabMinCost[i] = new CoutMinimumBellman("infini",startNode);
		}
		
		//le premier noeud est initialis� � 0
		tabMinCost[startNode] = new CoutMinimumBellman("0",startNode);

		//Cr�ation du tableau du plus court chemin � une taille �gale au nombre de noeud
		this.tabCheminMini = new CheminMinimum[graphe.getListNode().size()];
		for(int j = 0; j<graphe.getListNode().size(); j++) {
			//Cr�ation de tout les chemin
			tabCheminMini[j] = new CheminMinimum();
			//Ajout du noeud de fin
			tabCheminMini[j].addNodeToEnd(j);
		}
		
	}
	
	public boolean algoBellman(){
		//Le cout pendant lalgo
		int currentCost;
		
		//Pour tout les noeuds du graphe
		for(int i = 1 ; i< graphe.getListNode().size(); i++){
			//pour tous les arcs
			for(Arc a : graphe.getListArc()){
				//si le cout minimum du noeud de d�part n'est �gale � infini
				if(!tabMinCost[a.getStartNode().getNumber()].getCost().equals("infini")){
					//le cout actuel est �gale au cout courant + celui du noeud actuel
					currentCost = Integer.parseInt(tabMinCost[a.getStartNode().getNumber()].getCost()) + a.getCost();
					//Si le noeud de fin est �gale � infini et que le cout actuel est inferieur � le cout actuel du chemin
					if(tabMinCost[a.getEndNode().getNumber()].getCost().equals("infini") || currentCost < Integer.parseInt(tabMinCost[a.getEndNode().getNumber()].getCost())){
						//alors on modifie le cout du du chemin avec le cout actuel
						tabMinCost[a.getEndNode().getNumber()].setCost(String.valueOf(currentCost));
						//et on change le predecesseur avec le noeud actuel
						tabMinCost[a.getEndNode().getNumber()].setPredecesseur(a.getStartNode().getNumber());
					}
				}
			}
		}
		// pour tous les arcs
		for(Arc a : graphe.getListArc()){
			//Regarde s'il existe un cycle absorbant
			if(!tabMinCost[a.getStartNode().getNumber()].getCost().equals("infini") && Integer.parseInt(tabMinCost[a.getStartNode().getNumber()].getCost()) + a.getCost() < Integer.parseInt(tabMinCost[a.getEndNode().getNumber()].getCost())){
				return false;
			}
		}
	
		//Calcul des chemins
		int sommetTemp;
		//pour tous les noeuds du graphe
		for(int i = 0 ; i < graphe.getNbNodes() ; i++){
			sommetTemp = i;
			//Tant que le sommet temporaire est diff�rent du noeud de d�part
			while(sommetTemp != startNode){
				//on ajoute au d�but le predecesseur du noeud au tableau du chemin mini
				tabCheminMini[i].addNodeToFirst(tabMinCost[sommetTemp].getPredecesseur());
				//mise � jour du sommet temporaire par le pr�d�cesseur de celui-ci
				sommetTemp = tabMinCost[sommetTemp].getPredecesseur();
			}
		}
		
		return true;
	}

	//Affichage des donn�es
	public void afficher() {
		System.out.println("R�sultat de l'algorithme de Bellman : \t ( Sommet de d�part : " + startNode +")");
		System.out.println("");
		for(int i = 0; i<graphe.getNbNodes(); i++) {
			if(i != startNode && !tabMinCost[i].getCost().equals("infini")){
				System.out.println("Chemin de " + startNode + " vers " + i + " a pour cout minimum " + tabMinCost[i].getCost() + "\t " + tabCheminMini[i]);
			}
			else if(tabMinCost[i].getCost().equals("infini")){
				System.out.println("Chemin de " + startNode + " vers " + i + " est impossible");
			}
		}
	}
	
}
