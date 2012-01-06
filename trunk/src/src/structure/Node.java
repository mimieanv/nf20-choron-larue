package structure;

import java.util.ArrayList;

public class Node {
	private int number;
	
	// Variables utilisees par Dijkstra
	private int parcouru;
	private Node precedent;
	private boolean haveConstraint = false;
	private boolean tacheFin = true;
	private int duree;
	
	public Node(int _number) {
		number = _number;
	}
	
	/**
	 * DANS LE CAS D'UN GRAPHE NON ORIENTE - Retourne un ArrayList contenant tous les fils du sommet
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
	 * DANS LE CAS D'UN GRAPHE ORIENTE - Retourne un ArrayList contenant tous les fils du sommet
	 * @return ArrayList<Node>
	 */
	public ArrayList<Node> getFistonsOriente() {
		ArrayList<Node> listeFistons = new ArrayList<Node>();
		
		for(Arc arc : Graph.getInstance().getListArc()) {
			if(arc.getStartNode().getNumber() == this.number)
				listeFistons.add(arc.getEndNode());
		}
		
		return listeFistons;
	}
	
	/**
	 * Retourne la distance correspond au poids de l'arc reliant n1 (this) et n2
	 * @return
	 */
	public int distance(Node _n2) {
		Arc arcReliant = null;
		int cost;

		for (Arc arc : Graph.getInstance().getListArc()) {
			if ( (arc.getStartNode().getNumber() == this.getNumber()
						&& arc.getEndNode().getNumber() == _n2.getNumber())
				|| (arc.getStartNode().getNumber() == _n2.getNumber()
						&& arc.getEndNode().getNumber() == this.getNumber()) )
				arcReliant = arc;
				break;
		}
		
		if(arcReliant != null)
			cost = arcReliant.getCost();
		else
			cost = 0;
		
		return cost;
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
	
	public Node getPrecedent() {
		return precedent;
	}
	
	public void setPrecedent(Node _precedent) {
		this.precedent = _precedent;
	}
	//Pour L'ordonnencement
	public int getDuree() {
		return duree;
	}
	//Pour L'ordonnencement
	public void setDuree(int duree) {
		this.duree = duree;
	}

	public boolean haveConstraint() {
		return haveConstraint;
	}

	public void setHaveConstraint(boolean haveConstraint) {
		this.haveConstraint = haveConstraint;
	}

	public boolean isTacheFin() {
		return tacheFin;
	}

	public void setTacheFin(boolean tacheFin) {
		this.tacheFin = tacheFin;
	}
	
	
}
