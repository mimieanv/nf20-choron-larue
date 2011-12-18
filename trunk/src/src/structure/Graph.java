package structure;

import java.util.ArrayList;

public class Graph {
	ArrayList<Arc>	listArc		= new ArrayList<Arc>();
	ArrayList<Node> listNode	= new ArrayList<Node>();
	
	private int nbArcs;
	private int nbNodes;
	
	private static Graph graphInstance = null;
	
	public static Graph getInstance() {
		if(graphInstance == null) 
			graphInstance = new Graph();
		return graphInstance;
	}
	
/***************************
 **** GETTERS / SETTERS ****
 ***************************/
	
	public int getSize() {
		return listNode.size();
	}
	
	public int getNbArcs() {
		return this.nbArcs;
	}
	
	public int getNbNodes() {
		return this.nbNodes;
	}
	
	public ArrayList<Arc> getListArc() {
		return listArc;
	}
	
	public ArrayList<Node> getListNode() {
		return listNode;
	}
	
	public void setListArc(ArrayList<Arc> _list) {
		listArc = _list;
	}
	
	public void setListNode(ArrayList<Node> _list) {
		listNode = _list;
	}
	
	public void setNbArcs(int _nbArcs) {
		this.nbArcs = _nbArcs;
	}
	
	public void setNbNodes(int _nbNodes) {
		this.nbNodes = _nbNodes;
	}

}
