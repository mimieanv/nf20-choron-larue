package Algos;
import structure.Graph;

public class Dijkstra {
	
	public Dijkstra() {
		//On recupere les donnees necessaires ˆ l'exe de l'algo
	}
	
		// Dijkstra's algorithm to find shortest path from s to all other nodes
	public static int [] dijkstraAlgo (Graph _graphe, int _s) {
		
		int [] dist 		= new int [_graphe.getSize()];  	// shortest known distance from "s"
		int [] pred			= new int [_graphe.getSize()];  	// preceeding node in path
		boolean [] visited	= new boolean [_graphe.getSize()]; 	// all false initially
			  
		for (int i=0; i<dist.length; i++) {
			dist[i] = Integer.MAX_VALUE;
		}
		dist[_s] = 0;
		
		for (int i=0; i<dist.length; i++) {
			final int next = minVertex (dist, visited);
			visited[next] = true;

			 // The shortest path to next is dist[next] and via pred[next].

			final int [] n = _graphe.neighbors (next);
			for (int j=0; j<n.length; j++) {
				final int v = n[j];
				final int d = dist[next] + _graphe.getWeight(next,v);
				if (dist[v] > d) {
					dist[v] = d;
					pred[v] = next;
				}
			}
		}
		return pred;  // (ignore pred[s]==0!)
	}
	
	private void initialize(Graph _graphe, int _s) {
		final int [] dist = new int [_graphe.getSize()];  // shortest known distance from "s"
		final int [] pred = new int [_graphe.getSize()];  // preceeding node in path
		final boolean [] visited = new boolean [_graphe.getSize()]; // all false initially
			  
		for (int i=0; i<dist.length; i++) {
			dist[i] = Integer.MAX_VALUE;
		}
		dist[_s] = 0;
	}
	
	private static int minVertex (int [] _dist, boolean [] _v) {
		int x = Integer.MAX_VALUE;
		int y = -1;   // graph not connected, or no unvisited vertices
		for (int i=0; i<_dist.length; i++) {
			if (!_v[i] && _dist[i]<x) {y=i; x=_dist[i];}
		}
		return y;
	}
	
}
