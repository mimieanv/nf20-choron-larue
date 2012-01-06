package Algos;

import structure.Arc;
import structure.Graph;

public class Ordonnencement {
	private int startNode;
	private Graph graphe;
	private CoutMinimumBellman[] tabMaxiCost;
	private CheminMinimum[] tabCheminMaxi;
	
	public Ordonnencement(Graph graphe) {
		this.startNode = 0;
		this.graphe = graphe;
		//initialisation du tableau pour les couts minimums à une taille égale au nombre de noeuds
		tabMaxiCost = new CoutMinimumBellman[graphe.getListNode().size()];
		
		//Tout les noeuds sont initialisé à 0
		for(int i = 0; i<graphe.getListNode().size(); i++) {
			tabMaxiCost[i] = new CoutMinimumBellman("0",startNode);
		}

		//Création du tableau du plus long chemin à une taille égale au nombre de noeud
		this.tabCheminMaxi = new CheminMinimum[graphe.getListNode().size()];
		for(int j = 0; j<graphe.getListNode().size(); j++) {
			//Création de tout les chemin
			tabCheminMaxi[j] = new CheminMinimum();
			//Ajout du noeud de fin
			tabCheminMaxi[j].addNodeToEnd(j);
		}
		
	}
	
	public boolean algoOrdo(){
		//Le cout pendant lalgo
		int currentCost;
		
		//Pour tout les noeuds du graphe
		for(int i = 1 ; i< graphe.getListNode().size(); i++){
			//pour tous les arcs
			for(Arc a : graphe.getListArc()){
     			//le cout actuel est égale au cout courant + celui du noeud actuel
				currentCost = Integer.parseInt(tabMaxiCost[a.getStartNode().getNumber()].getCost()) + a.getCost();
				//Si le noeud de fin est égale à 0 ou que le cout actuel currentCost est superieur à le cout actuel du chemin
				if(tabMaxiCost[a.getEndNode().getNumber()].getCost().equals("0") || currentCost > Integer.parseInt(tabMaxiCost[a.getEndNode().getNumber()].getCost())){
					//alors on modifie le cout du du chemin avec le cout actuel
					tabMaxiCost[a.getEndNode().getNumber()].setCost(String.valueOf(currentCost));
					//et on change le predecesseur avec le noeud actuel
					tabMaxiCost[a.getEndNode().getNumber()].setPredecesseur(a.getStartNode().getNumber());
				}	
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
				tabCheminMaxi[i].addNodeToFirst(tabMaxiCost[sommetTemp].getPredecesseur());
				//mise à jour du sommet temporaire par le prédécesseur de celui-ci
				sommetTemp = tabMaxiCost[sommetTemp].getPredecesseur();
			}
		}
		
		return true;
	}

	//Affichage des données
	public void afficher() {
		int res=0;
		for(int i = 0; i<graphe.getNbNodes(); i++) {
			if(i != startNode && res < Integer.parseInt(tabMaxiCost[i].getCost())){
				res = Integer.parseInt(tabMaxiCost[i].getCost());
			}


		}
		System.out.println("le résultat de l'ordonnencement a un cout de : "+ res +" jours");
	}
}
