package Algos;

import java.util.ArrayList;

import structure.*;

public class Dijkstra {

	private Graph graphe;
	private Node startNode;

	
	public Dijkstra(int _startNodeNb, Graph _graphe) {
		graphe = _graphe;
		for (Node node : graphe.getListNode()) {
			if(node.getNumber() == _startNodeNb) {
				this.startNode = node;
				break;
			}
		}
	}

	public ArrayList<Node> algoWikipedia() {
		boolean deboguer = true;
		
		for (Node node : graphe.getListNode()) {
			node.setParcouru(-1);
			node.setPrecedent(null);
		}

		startNode.setParcouru(0);
		ArrayList<Node> pasEncoreVu	= graphe.getListNode();
		ArrayList<Node> chemin		= new ArrayList<Node>();

		while (!pasEncoreVu.isEmpty()) {
			int indexNode	= minimum(pasEncoreVu);
			Node node		= pasEncoreVu.get(indexNode);
			pasEncoreVu.remove(indexNode);
			/* DEBUG */
				if(deboguer) System.out.println("node (pasEncoreVu.get(indexNode) : " + node);
			/* DEBUG */

			for (Node fils : node.getFistons()) {
				if (fils.getParcouru() > (node.getParcouru() + node.distance(fils))) {
					fils.setParcouru(node.getParcouru() + node.distance(fils));
					fils.setPrecedent(node);
					/* DEBUG */
						if(deboguer) System.out.println(fils + ".setPrecedent() -> " + node);
					/* DEBUG */
				}
			}
		}

		Node lastNode = ;
		/* DEBUG */
			if(deboguer) System.out.println("lastNode : " + lastNode);
		/* DEBUG */

		while (lastNode != startNode) {
			chemin.add(0, lastNode);
			lastNode = lastNode.getPrecedent(); 	// TODO BUG ICI ! <!> UN NODE N'A PAS FORCEMENT DE PRECEDENT...? <!>
			/* DEBUG */
				if(deboguer) System.out.println("last node : " + lastNode);
			/* DEBUG */
		}

		chemin.add(0, _endNode);

		return chemin;
	}

	/**
	 * Retourne l'index du noeud dans _listNode avec 'parcouru' le plus petit
	 * @param _listNode
	 * @return int
	 */
	public int minimum(ArrayList<Node> _listNode) {
		int indexNode	= -1;
		int i			= Integer.MAX_VALUE;
		
		for (Node node : _listNode) {
			if (node.getParcouru() < i)
				i = node.getParcouru();
			indexNode++;
		}
		
		return indexNode;
	}

}
