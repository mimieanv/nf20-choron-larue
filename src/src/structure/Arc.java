package structure;


public class Arc {
	private Node startNode;
	private Node endNode;
	private int cost;
	
	
	public Arc(Node _startNode, Node _endNode, int _cost){
		startNode	= _startNode;
		endNode		= _endNode;
		cost		= _cost;
	}
	
/***************************
 **** GETTERS / SETTERS ****
 ***************************/

	public Node getStartNode() {
		return startNode;
	}

	public Node getEndNode() {
		return endNode;
	}

	public int getCost() {
		return cost;
	}

	public void setStartNode(Node startNode) {
		this.startNode = startNode;
	}
	
	public void setEndNode(Node endNode) {
		this.endNode = endNode;
	}
	
	public void setCost(int cost) {
		this.cost = cost;
	}
	
	
	public String toString(){
		String res = "";
		res += "noeud de depart : " + startNode.getNumber() + " noeud d'arriv� : " + endNode.getNumber() + " cout : " + cost;
		
		return res;
	}
	
}
