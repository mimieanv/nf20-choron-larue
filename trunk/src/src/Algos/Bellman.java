package Algos;

import structure.Arc;
import structure.Graph;


public class Bellman {

	private int startNode;
	private Graph graphe;
	private CoutMinimumBellman[] tabMinCost;
	private CheminMinimumBellman[] tabChemins;
	
	public Bellman(int startNode, Graph graphe) {
		this.startNode = startNode;
		this.graphe = graphe;
		tabMinCost = new CoutMinimumBellman[graphe.getListNode().size()];
		for(int i = 0; i<graphe.getListNode().size(); i++) {
			tabMinCost[i] = new CoutMinimumBellman("infini",startNode);
		}

		tabMinCost[startNode] = new CoutMinimumBellman("0",startNode);

		
		this.tabChemins = new CheminMinimumBellman[graphe.getListNode().size()];
		for(int j = 0; j<graphe.getListNode().size(); j++) {
			tabChemins[j] = new CheminMinimumBellman();
			tabChemins[j].addNodeToEnd(j);
		}
		
	}
	
	public boolean algoBellman(){
		int currentCost;
		
		for(int i = 1 ; i< graphe.getListNode().size(); i++){
			for(Arc a : graphe.getListArc()){
				if(!tabMinCost[a.getStartNode().getNumber()].getCost().equals("infini")){
					currentCost = Integer.parseInt(tabMinCost[a.getStartNode().getNumber()].getCost()) + a.getCost();
					if(tabMinCost[a.getEndNode().getNumber()].getCost().equals("infini") || currentCost < Integer.parseInt(tabMinCost[a.getEndNode().getNumber()].getCost())){
						tabMinCost[a.getEndNode().getNumber()].setCost(String.valueOf(currentCost));
						tabMinCost[a.getEndNode().getNumber()].setPredecesseur(a.getStartNode().getNumber());
					}
				}
			}
		}
		for(Arc a : graphe.getListArc()){
			if(!tabMinCost[a.getStartNode().getNumber()].getCost().equals("infini") && Integer.parseInt(tabMinCost[a.getStartNode().getNumber()].getCost()) + a.getCost() < Integer.parseInt(tabMinCost[a.getEndNode().getNumber()].getCost())){
				return false;
			}
		}
	
		//Calcul des chemins
		int sommetTemp;
		for(int i = 0 ; i < graphe.getNbNodes() ; i++){
			sommetTemp = i;
			while(sommetTemp != startNode){
				tabChemins[i].addNodeToFirst(tabMinCost[sommetTemp].getPredecesseur());
				sommetTemp = tabMinCost[sommetTemp].getPredecesseur();
			}
		}
		
		return true;
	}

	public void afficher() {
		System.out.println("Resultat de l'algorithme de Bellman : ");
		System.out.println("(Sommet de départ : " + startNode +")");
		for(int i = 0; i<graphe.getNbNodes(); i++) {
			if(i != startNode && !tabMinCost[i].getCost().equals("infini")){
				System.out.println("Chemin de " + startNode + " vers " + i + " a pour cout minimum " + tabMinCost[i].getCost() + "\t " + tabChemins[i]);
			}
			else if(tabMinCost[i].getCost().equals("infini")){
				System.out.println("Chemin de " + startNode + " vers " + i + " est impossible");
			}
		}
	}
	
}
