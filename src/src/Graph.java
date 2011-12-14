import java.util.ArrayList;


public class Graph {

	ArrayList<Arc> listArc = new ArrayList<Arc>();
	ArrayList<Node> listNode = new ArrayList<Node>();
	
	public void setListArc(ArrayList<Arc> _list) {
		listArc = _list;
	}
	
	public void setListNode(ArrayList<Node> _list) {
		listNode = _list;
	}
	
	public int getSize() {
		return listNode.size();
	}

}
