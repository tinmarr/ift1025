/**
 * La classe CreerCarte étend la classe Commande et représente la commande de
 * création de la carte dans le contexte de la chasse au trésor.
 */
public class CreerCarte extends Commande {

	private static final int NON_INITIALISE = -1;

	/**
	 * Dimensions de la carte en termes de lignes (tailleX) et de colonnes
	 * (tailleY).
	 */
	private int tailleX = NON_INITIALISE;
	private int tailleY = NON_INITIALISE;

	/**
	 * Position du pirate sur la carte.
	 */
	private int pirateX = NON_INITIALISE;
	private int pirateY = NON_INITIALISE;

	/**
	 * Position du trésor sur la carte.
	 */
	private int tresorX = NON_INITIALISE;
	private int tresorY = NON_INITIALISE;

	/**
	 * Construit un objet CreerCarte en analysant les arguments de la commande.
	 *
	 * @param cmdArgs Les arguments de la commande de création de carte.
	 */
	public CreerCarte(String[] cmdArgs) {
		if (cmdArgs.length == 6) {
			this.tailleX = Integer.parseInt(cmdArgs[0]);
			this.tailleY = Integer.parseInt(cmdArgs[1]);
			this.pirateX = Integer.parseInt(cmdArgs[2]);
			this.pirateY = Integer.parseInt(cmdArgs[3]);
			this.tresorX = Integer.parseInt(cmdArgs[4]);
			this.tresorY = Integer.parseInt(cmdArgs[5]);
		}
	}

	/**
	 * Exécute la commande de création de carte, mettant à jour la carte du jeu en
	 * fonction des paramètres spécifiés.
	 *
	 * @param jeu          L'objet ChasseAuTresor sur lequel la commande doit être
	 *                     exécutée.
	 * @param montrerCarte Indique si la carte doit être affichée après la création.
	 */
	@Override
	public void executerCommande(ChasseAuTresor jeu, boolean montrerCarte) {
		jeu.setCarte(new Carte(this.tailleX, this.tailleY, this.pirateX, this.pirateY, this.tresorX, this.tresorY));
		if (montrerCarte)
			System.out.println(jeu.getCarte().toString());

	}

}
