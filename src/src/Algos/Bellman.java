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
		//initialisation du tableau pour les couts minimums à une taille égale au nombre de noeuds
		tabMinCost = new CoutMinimumBellman[graphe.getListNode().size()];
		
		//Tout les noeuds sont initialisé à infini
		for(int i = 0; i<graphe.getListNode().size(); i++) {
			tabMinCost[i] = new CoutMinimumBellman("infini",startNode);
		}
		
		//le premier noeud est initialisé à 0
		tabMinCost[startNode] = new CoutMinimumBellman("0",startNode);

		//Création du tableau du plus court chemin à une taille égale au nombre de noeud
		this.tabCheminMini = new CheminMinimum[graphe.getListNode().size()];
		for(int j = 0; j<graphe.getListNode().size(); j++) {
			//Création de tout les chemin
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
				//si le cout minimum du noeud de départ n'est égale à infini
				if(!tabMinCost[a.getStartNode().getNumber()].getCost().equals("infini")){
					//le cout actuel est égale au cout courant + celui du noeud actuel
					currentCost = Integer.parseInt(tabMinCost[a.getStartNode().getNumber()].getCost()) + a.getCost();
					//Si le noeud de fin est égale à infini et que le cout actuel est inferieur à le cout actuel du chemin
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
			//Tant que le sommet temporaire est différent du noeud de départ
			while(sommetTemp != startNode){
				//on ajoute au début le predecesseur du noeud au tableau du chemin mini
				tabCheminMini[i].addNodeToFirst(tabMinCost[sommetTemp].getPredecesseur());
				//mise à jour du sommet temporaire par le prédécesseur de celui-ci
				sommetTemp = tabMinCost[sommetTemp].getPredecesseur();
			}
		}
		
		return true;
	}

	//Affichage des données
	public void afficher() {
		System.out.println("Résultat de l'algorithme de Bellman : \t ( Sommet de départ : " + startNode +")");
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
