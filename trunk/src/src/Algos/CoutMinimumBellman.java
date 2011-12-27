package Algos;

public class CoutMinimumBellman {
	private String cost;
	private int predecesseur;
	
	public CoutMinimumBellman(String poids, int predecesseur) {
		this.cost = poids;
		this.predecesseur = predecesseur;
	}
	
	public String getCost() {
		return cost;
	}
	
	public void setCost(String poids) {
		this.cost = poids;
	}
	
	public int getPredecesseur() {
		return predecesseur;
	}
	
	public void setPredecesseur(int predecesseur) {
		this.predecesseur = predecesseur;
	}
	

}
