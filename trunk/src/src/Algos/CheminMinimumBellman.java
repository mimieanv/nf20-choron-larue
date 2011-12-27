package Algos;

import java.util.LinkedList;
import java.util.ListIterator;

public class CheminMinimumBellman {

	private LinkedList<Integer> NodeList = new LinkedList<Integer>();


	public void updateList(CheminMinimumBellman nouveauChemin, int sommetArrive) {
		
		NodeList.clear();
		LinkedList<Integer> newList = nouveauChemin.getNodeList();
		ListIterator<Integer> it = newList.listIterator();
		while(it.hasNext()){
			NodeList.addLast(it.next());
		}
		NodeList.addLast(sommetArrive);
	}

	public void addNodeToEnd(int sommetDep) {
		NodeList.addLast(sommetDep);
	}
	
	public void addNodeToFirst(int sommetDep) {
		NodeList.addFirst(sommetDep);
	}
	
	public LinkedList<Integer> getNodeList(){
		return this.NodeList;
	}

	@Override
	public String toString() {
		return "Le chemin Minimum  est " + NodeList;
	}

	
	
}
