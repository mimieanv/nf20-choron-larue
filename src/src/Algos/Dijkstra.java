package Algos;

import java.util.ArrayList;

import structure.Arc;
import structure.Graph;

public class Dijkstra {

	private int sommetDep;
	private Graph graphe;
	private CoutMinimumDijkstra[] tabMinCost;
	private CheminMinimum[] tabCheminMini;
	
	//Constructeur de dijkstra
	public Dijkstra(int sommetDep, Graph graphe) {
		this.sommetDep = sommetDep;
		this.graphe = graphe;
		initTabDijkstra();
		
	}
	
	/*Initialise les tableaux de couts des noeuds et des chemins*/
	public void initTabDijkstra(){
		
		//la taille du tableau de cout mini est le nombre de noeuds
		tabMinCost = new CoutMinimumDijkstra[graphe.getNbNodes()];
		//la taille du tableau du chemin mini est aussi le nombre de noeuds
		tabCheminMini = new CheminMinimum[graphe.getNbNodes()];
		
		//Tout le tableau est mis à infini
		for(int i = 0; i<graphe.getNbNodes(); i++) {
			tabMinCost[i] = new CoutMinimumDijkstra("infini");
		}
		
		//le sommet de départ est mis à 0
		tabMinCost[sommetDep].setCost("0");

		//Tout le tableau des chemins mini est créé
		// i étant
		for(int i = 0; i<graphe.getNbNodes(); i++) {
			tabCheminMini[i] = new CheminMinimum();
			tabCheminMini[i].addNodeToEnd(sommetDep);
		}	
	}

	public void calculerDijkstra() {
		int cost = 0;
		int startNode;
		//pour tout les nodes
		for(int i = 0; i<graphe.getNbNodes(); i++) {
			//recupere le node qui a la plus petite valeur
			startNode = getMin();
			//si il n'y a pas de mini
			if(startNode != -1) {
				//creation d'une liste d'arcs qui seront les succeseurs de startNode
				ArrayList<Arc> sommet_succ = getSuccesseur(startNode);
				//pour tous les successeurs
				for(Arc arc : sommet_succ) {
					//si le cout est égale à infini
					if(tabMinCost[arc.getEndNode().getNumber()].getCost().equals("infini")) {
						cost = Integer.parseInt((tabMinCost[startNode].getCost()))+ arc.getCost();
						//modifie le cout du noeuds de fin
						tabMinCost[arc.getEndNode().getNumber()].setCost(String.valueOf(cost));
						//mise à jour du tableau du cheminMini
						tabCheminMini[arc.getEndNode().getNumber()].updateList(tabCheminMini[startNode],arc.getEndNode().getNumber());
					}
					//si le noeud noeud n'est pas bloqué et que le cout est inferieur à celui d'avant
					else if((!tabMinCost[arc.getEndNode().getNumber()].isLock()) && (Integer.parseInt((tabMinCost[startNode].getCost()))+ arc.getCost()) < Integer.parseInt((tabMinCost[arc.getEndNode().getNumber()].getCost()))){
						//recupere le cout
						cost = (Integer.parseInt((tabMinCost[startNode].getCost()))+ arc.getCost());
						//modifie le cout du noeuds de fin
						tabMinCost[arc.getEndNode().getNumber()].setCost(String.valueOf(cost));
						//mise à jour du tableau du cheminMini
						tabCheminMini[arc.getEndNode().getNumber()].updateList(tabCheminMini[startNode],arc.getEndNode().getNumber());
					}
				}
			}
		}
	}
	
	//Recupere les successeurs d'un sommet
	public ArrayList<Arc> getSuccesseur(int sommet) {
		
		ArrayList<Arc> resultat = new ArrayList<Arc>();
		 for(Arc a : graphe.getListArc()) {
			 //recupere tout les successeurs qui ont comme valeur du startNode le sommet passé en param 
			 if(a.getStartNode().getNumber() == sommet)
				 //les rajoutes à la liste des successeurs
				 resultat.add(a);
		 }
		return resultat;
	}
	
	/*Recuperer le noeud mini qui n'est toujours pas bloque*/
	public int getMin() {
		
		int res = -1;
		int i = 0;
		//pour tout les noeuds
		for(i = 0; i < tabMinCost.length && res == -1; i++) {
			//si le noeud n'est pas bloqué et que le noeud est equals à infini
			if(!tabMinCost[i].isLock() && !tabMinCost[i].getCost().equals("infini")) {
				res = i;
			}
		}
		
		if(res == -1) return res;
		
		
		for(int j = i; j < tabMinCost.length; j++) {
			//si le noeud n'est pas égale à infini et que le noeud n'est pas bloqué et que le noeud à la plus petite valeur
			if(!tabMinCost[j].getCost().equals("infini") && !tabMinCost[j].isLock() && Integer.parseInt(tabMinCost[j].getCost()) < Integer.parseInt(tabMinCost[res].getCost())) {
				res= j;
			}
		}
		
		//bloque le noeud que l'on prend
		tabMinCost[res].setLock(true);
		
		return res;
	}
	
	public void afficher() {
		System.out.println("Resultats de l'algorithme de Dijkstra : \t (Sommet de départ : " + sommetDep+")");
		for(int i = 0; i<graphe.getNbNodes(); i++) {
			if(i != sommetDep && !tabMinCost[i].getCost().equals("infini")){
				System.out.println("Chemin de " + sommetDep + " vers " + i + " a un cout de " + tabMinCost[i].getCost() + "\t " + tabCheminMini[i]);
			}
			else if(tabMinCost[i].getCost().equals("infini")){
				System.out.println("Chemin de " + sommetDep + " vers " + i + " est impossible");
			}
		}
	}

}
