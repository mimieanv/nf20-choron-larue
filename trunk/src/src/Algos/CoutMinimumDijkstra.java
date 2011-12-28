package Algos;

public class CoutMinimumDijkstra {

	private String cost;
	private boolean lock;
	
	public CoutMinimumDijkstra(String cost) {
		this.cost = cost;
		this.lock = false;
	}

	public String getCost() {
		return cost;
	}
	
	public void setCost(String poids) {
		this.cost = poids;
	}
	
	public boolean isLock() {
		return lock;
	}
	
	public void setLock(boolean lock) {
		this.lock = lock;
	}
	
	@Override
	public String toString() {
		return "CoutMinimum [poids=" + cost + ", lock=" + lock + "]";
	}


}
