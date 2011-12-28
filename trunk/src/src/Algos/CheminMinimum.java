package Algos;

import java.util.LinkedList;
import java.util.ListIterator;

public class CheminMinimum {

	private LinkedList<Integer> NodeList = new LinkedList<Integer>();


	public void updateList(CheminMinimum nouveauChemin, int endNode) {
		
		//efface le chemin mini existant
		NodeList.clear();
		//Nouvelle liste de chemin
		LinkedList<Integer> newList = nouveauChemin.getNodeList();
		//Création d'un iterator pour boucler
		ListIterator<Integer> it = newList.listIterator();
		while(it.hasNext()){
			//Ajoute toute la liste au chemin
			NodeList.addLast(it.next());
			
		}
		//Ajoute le sommet d'arriver au chemin
		NodeList.addLast(endNode);
	}

	//Ajoute le noeud à la fin
	public void addNodeToEnd(int startNode) {
		NodeList.addLast(startNode);
	}
	//Ajoute le noeud au début
	public void addNodeToFirst(int startNode) {
		NodeList.addFirst(startNode);
	}
	
	public LinkedList<Integer> getNodeList(){
		return this.NodeList;
	}

	@Override
	public String toString() {
		return "Le chemin Minimum  est " + NodeList;
	}

	
	
}
