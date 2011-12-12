
public class Arc {
	private Node startNode;
	private Node endNode;
	private int cost;
	
	public Arc(Node _startNode, Node _endNode, int _cost){
		startNode = _startNode;
		endNode = _endNode;
		cost = _cost;
	}

	public Node getStartNode() {
		return startNode;
	}

	public void setStartNode(Node startNode) {
		this.startNode = startNode;
	}

	public Node getEndNode() {
		return endNode;
	}

	public void setEndNode(Node endNode) {
		this.endNode = endNode;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}
	
}
