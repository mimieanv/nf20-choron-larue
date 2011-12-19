package Algos;
import java.util.ArrayList;

import structure.*;

public class Dijkstra {
	
	private Graph graphe;
	int nbArcs;
	
	public Dijkstra(Graph _graphe) {
		graphe = _graphe;
	}
	
/*	public Dijkstra(Graph _graphe) {
	graphe = _graphe;
	int nbArcs = _graphe.getNbArcs();

	sommets = new ArrayList<String>();
	
	// Initialisation des tableaux
	for (int i=0; i<=nbArcs; i++)
		distance[i]	= -1;	// -1 <==> + infini
	distance[1] = 0;
	pere[1]		= 0;
}
*/
	
	public ArrayList<Node> algoWikipedia(Node _startNode, Node _endNode) {
		for(Node node : graphe.getListNode()) {
	         node.setParcouru(-1);
	         node.setPrecedent(null);
		}
			
		_startNode.setParcouru(0);
		ArrayList<Node> pasEncoreVu = graphe.getListNode();
		ArrayList<Node> chemin = new ArrayList<Node>();
		
		while(!pasEncoreVu.isEmpty()) {
			int indexNode = minimum(pasEncoreVu);
			Node node = pasEncoreVu.get(indexNode);
			pasEncoreVu.remove(indexNode);
			
			for(Node fils : node.getFistons()) {
				if(fils.getParcouru() > (node.getParcouru() + node.distance(fils))) {
					fils.setParcouru (node.getParcouru() + node.distance(fils));
					fils.setPrecedent(node);
				}
			}
		}
		
		Node lastNode = _endNode;
		
		while(lastNode != _startNode) {
			chemin.add(0, lastNode);
			lastNode = lastNode.getPrecedent();		//TODO BUG ICI !
		}
		
		chemin.add(0, _endNode);
		
		return chemin;		
	}
	
	/**
	 * Retourne l'index du noeud dans _listNode avec 'parcouru' le plus petit
	 * @param _listNode
	 * @return int 
	 */
	public int minimum (ArrayList<Node> _listNode) {
		int i = Integer.MAX_VALUE;
		int indexNode = -1;
		for (Node node : _listNode) {
			if(node.getParcouru() < i)
				i = node.getParcouru();
			indexNode++;
		}
		return indexNode;
	}

}
