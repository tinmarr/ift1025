/**
 * La classe abstraite Commande représente une commande générique dans le contexte de la chasse au trésor.
 */
public abstract class Commande {
	
    /**
     * Exécute la commande spécifique dans le contexte de la chasse au trésor.
     * 
     * @param jeu           L'objet ChasseAuTresor sur lequel la commande doit être exécutée.
     * @param montrerCarte  Indique si la carte doit être affichée après l'exécution de la commande.
     */
	public abstract void executerCommande(ChasseAuTresor jeu, boolean montrerCarte);
	
}
