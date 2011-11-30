
public abstract class Arc {
	private Node startNode;
	private Node endNode;
	private int cost;
	
	public Arc(Node _startNode, Node _endNode, int _cost){
		startNode = _startNode;
		endNode = _endNode;
		cost = _cost;
	}
	
}
