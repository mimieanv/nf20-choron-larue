package structure;

import java.util.ArrayList;

public class Node {
	private int number;
	
	// variables utilisées par Dijkstra
	private int parcouru;
	private int precedent;
	
	public Node(int _number) {
		number = _number;
	}
	
	/**
	 * DANS LE CAS D'UN GRAPHE NON ORIENTÉ - Retourne un ArrayList contenant tous les fils du sommet
	 * @return ArrayList<Node>
	 */
	public ArrayList<Node> getFistons() {
		ArrayList<Node> listeFistons = new ArrayList<Node>();
		
		for(Arc arc : Graph.getInstance().getListArc()) {
			if(arc.getStartNode().getNumber()  == this.number)
				listeFistons.add(arc.getEndNode());
			else if(arc.getEndNode().getNumber()  == this.number)
				listeFistons.add(arc.getStartNode());
		}
		
		return listeFistons;
	}
	
	/**
	 * DANS LE CAS D'UN GRAPHE ORIENTÉ - Retourne un ArrayList contenant tous les fils du sommet
	 * @return ArrayList<Node>
	 */
	public ArrayList<Node> getFistonsOrienté() {
		ArrayList<Node> listeFistons = new ArrayList<Node>();
		
		for(Arc arc : Graph.getInstance().getListArc()) {
			if(arc.getStartNode().getNumber() == this.number)
				listeFistons.add(arc.getEndNode());
		}
		
		return listeFistons;
	}
	
/***************************
 **** GETTERS / SETTERS ****
 ***************************/
	
	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}
	
	public int getParcouru() {
		return parcouru;
	}
	
	public void setParcouru(int _parcouru) {
		this.parcouru = _parcouru;
	}
	
	public int getPrecedent() {
		return precedent;
	}
	
	public void setPrecedent(int _precedent) {
		this.precedent = _precedent;
	}
	
}
