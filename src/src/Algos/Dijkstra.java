package Algos;
import java.util.ArrayList;

import structure.*;

public class Dijkstra {
	
	//private int[] distance;
	//private int[] sommets;
	//private int[] pere;
	
	ArrayList<String> distance	= new ArrayList<String>();
	ArrayList<String> sommets	= new ArrayList<String>();
	ArrayList<Arc> pere;
	
	private Graph graphe;
	int nbArcs;
	
	public Dijkstra(Graph _graphe) {
		graphe = _graphe;
		int nbArcs = _graphe.getNbArcs();

		sommets = new ArrayList<String>();
		
		// Initialisation des tableaux
		for (int i=0; i<=nbArcs; i++)
			distance[i]	= -1;	// -1 <==> + infini
		distance[1] = 0;
		pere[1]		= 0;
	}
	
	public int dijkstraAlgoProf(int _s) {
		for (int i=0; i <= nbArcs; i++) {
			int min = -1;	// -1 <==> + infini
			for (int j=0; j <= nbArcs; j++) {
				if(j)
			}
		}
		
		return 0;
	}
	
	public int dijkstraAlgoWikipedia(fils, distance, debut, fin) {
		for (Node node : graphe.getListNode()) {
	         node.setParcouru(-1);
	         node.setPrecedent(0);
		}
			
			startNode.setParcouru(0);
			ArrayList<Node> pasEncoreVu = graphe.getListNode();
			
			while(!pasEncoreVu.isEmpty()) {
				int indexNode = minimum(pasEncoreVu);
				pasEncoreVu.remove(indexNode);
				
				for (Node fils : node.getFistons()) {
					
				}
			}
			
			
			
		    Tant que pasEncoreVu != liste vide
		        n1 = minimum(pasEncoreVu)   // Le nœud dans pasEncoreVu avec parcouru le plus petit
		        pasEncoreVu.enlever(n1)
		        Pour n2 parcourant fils(n1)   // Les nœuds reliés à n1 par un arc
		    
		            Si n2.parcouru > n1.parcouru + distance(n1, n2)   // distance correspond au poids de l'arc reliant n1 et n2
		                n2.parcouru = n1.parcouru + distance(n1, n2)
		                n2.précédent = n1   // Dit que pour aller à n2, il faut passer par n1
		            Fin si
		        Fin pour
		    Fin tant que
		    chemin = liste vide
		    n = fin
		    Tant que n != début
		        chemin.ajouterAvant(n)
		        n = n.précédent
		    Fin tant que
		    chemin.ajouterAvant(fin)
		    Retourner chemin
		Fin fonction Dijkstra
	}
	
	/**
	 * Retourne l'index du noeud dans _listNode avec 'parcouru' le plus petit
	 * @param _listNode
	 * @return int 
	 */
	public int minimum (ArrayList<Node> _listNode) {
		int i;	// initialiser i
		for (Node node : _listNode) {
			if(node.getParcouru() < i)
				i = node.getParcouru();
		}
		// retourner l'index du node avec i le plus petit
	}
	
	/*
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
	*/
	
}
