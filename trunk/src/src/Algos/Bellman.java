package Algos;

import java.util.ArrayList;
import java.util.List;

public class Bellman {
	/**
	 * La liste des taches utilisé par cette algorithme
	 */
	Tache[] taches;

	
	
	/**
	 * L'algorithme a besoin pour s'executer de connaitre la liste des taches.
	 * 
	 * @param taches
	 */
	public Bellman(Tache[] taches) {
		this.taches = taches;
	}
	
	/**
	 * Renvoie les temps au plus tot en utilisant l'alorithme de Bellman.
	 * 
	 * @param taches
	 * @return
	 */
	public void calculeTempsAuPlusTot() {
		// cree la tache initiale fictive en lui trouvant pour
		// successeur toutes les taches qui n'ont pas de prédécesseurs
		Tache tacheIntiale = GrapheUtil.construitTInitiale(taches);
		// Cree la tache finale fictive en lui trouvant pour predecesseur
		// toutes les taches qui n'ont pas de successeur
		Tache tacheFinale = GrapheUtil.construitTFinale(taches.length, taches);
		// condition initiale à l'algo on marque T0 et on lui
		// affecte la date au plus tot nulle
		tacheIntiale.setMarque(true);
		tacheIntiale.setTempsAuPlusTot(0);
		// nonMarque est l'index de la premiere tache non marquee
		// trouvee si il vaut -1 c'est qu'on ne peut plus en trouver
		// la boucle s'arrete
		int nonMarque = GrapheUtil.trouveNonMarqueAvecTousPredecesseursMarque(taches);
		while (nonMarque != -1) {
			// on marque la tache traitee
			taches[nonMarque].setMarque(true);
			// on calcul son temps au plus tot
			int tempsAuPlusTot = GrapheUtil
					.calculTempsAuPlusTot(taches[nonMarque]);
			// et on le met dans la tache
			taches[nonMarque].setTempsAuPlusTot(tempsAuPlusTot);
			// et on continue avec la prochaine tache non marquee suivante
			nonMarque = GrapheUtil
					.trouveNonMarqueAvecTousPredecesseursMarque(taches);
		}
		// on finalise avec la dernière tache
		int tempsAuPlusTotFinal = GrapheUtil.calculTempsAuPlusTot(tacheFinale);
		tacheFinale.setTempsAuPlusTot(tempsAuPlusTotFinal);
	}

	/**
	 * Renvoie les taches sur le chemin critique cette méthode ne peut etre
	 * appelé qu'apres l'appel de la méthode {@link #calculeTempsAuPlusTot()}
	 * 
	 * @return
	 */
	public List<Tache> calculeCheminCritique() {
		// On intialise le chemin critique
		List<Tache> cheminCritique = new ArrayList<Tache>();
		// on ajoute la tache initiale
		Tache tacheInitale = taches[0].getPredecesseurs().iterator().next();
		cheminCritique.add(tacheInitale);
		Tache tacheSurLeChemin = tacheInitale;
		while ((tacheSurLeChemin = tacheSurLeChemin
				.getSuccesseurTempsAuPlusTotMax()) != null) {
			cheminCritique.add(tacheSurLeChemin);
		}
		return cheminCritique;
	}

	
}
