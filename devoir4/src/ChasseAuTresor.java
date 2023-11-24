import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * La classe ChasseAuTresor represente un jeu ou un Pirate cherche à decouvrir
 * un tresor.
 */
public class ChasseAuTresor {

	/**
	 * Liste des commandes à exécuter dans le cadre de la chasse au trésor.
	 */
	private ArrayList<Commande> commandes = new ArrayList<Commande>();

	/**
	 * Obtient la liste des commandes associée à la chasse au trésor.
	 *
	 * @return La liste des commandes enregistrées dans le cadre de la chasse au
	 *         trésor.
	 */
	public ArrayList<Commande> getCommandes() {
		return commandes;
	}

	/**
	 * Chemin du fichier d'entrée contenant les commandes.
	 */
	private String fileIn;

	/**
	 * Chemin du fichier de sortie pour les résultats.
	 */
	private String fileOut;

	/**
	 * Obtient l'objet File associé au fichier de sortie.
	 *
	 * @return L'objet File représentant le fichier de sortie.
	 */
	public File getFileOut() {
		return new File(this.fileOut);
	}

	/**
	 * Référence à l'objet Carte utilisé dans le cadre de la chasse au trésor.
	 */
	private Carte carte;

	/**
	 * Constructeur par défaut de la classe ChasseAuTresor. Initialise les fichiers
	 * d'entrée et de sortie par défaut ("commandes.txt" et "resultat.txt").
	 */
	public ChasseAuTresor() {
		this("commandes.txt", "resultat.txt");
	}

	/**
	 * Constructeur de la classe ChasseAuTresor avec des fichiers d'entrée et de
	 * sortie spécifiés.
	 *
	 * @param fileIn  Le chemin du fichier d'entrée contenant les commandes.
	 * @param fileOut Le chemin du fichier de sortie pour les résultats.
	 */
	public ChasseAuTresor(String fin, String fout) {

		this.fileIn = fin;
		this.fileOut = fout;
		try {
			this.parserCommandesDuFichier();
		} catch (CommandInconueException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Parse les commandes à partir du fichier d'entrée et les ajoute à la liste des
	 * commandes.
	 *
	 * @throws CommandeInconnueException Si une commande inconnue est rencontrée
	 *                                   lors du parsing.
	 */
	private void parserCommandesDuFichier() throws CommandInconueException {
		try {
			Scanner scanner = new Scanner(new File(this.fileIn));
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				String cmd = this.prendreStringDeCommande(line);
				String[] args = this.prendreStringDesArguments(line);
				this.commandes.add(this.creerCommande(cmd, args));
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			System.out.println("Le fichier de commandes n'a pas été trouvé.");
		}
	}

	/**
	 * Crée une commande en fonction de la commande spécifiée et de ses arguments.
	 *
	 * @param cmd  La commande à exécuter.
	 * @param args Les arguments associés à la commande.
	 * @return Une instance de la classe Commande correspondant à la commande
	 *         spécifiée.
	 * @throws CommandInconueException Si la commande spécifiée n'est pas reconnue.
	 */
	private Commande creerCommande(String cmd, String[] args) throws CommandInconueException {
		switch (cmd) {
			case "carte":
				return new CreerCarte(args);
			case "creuser":
				return new Creuser();
			case "n":
				return new Marcher(Direction.NORD, Integer.parseInt(args[0]));
			case "s":
				return new Marcher(Direction.SUD, Integer.parseInt(args[0]));
			case "e":
				return new Marcher(Direction.EST, Integer.parseInt(args[0]));
			case "o":
				return new Marcher(Direction.OUEST, Integer.parseInt(args[0]));
			default:
				throw new CommandInconueException();
		}
	}

	/**
	 * Récupère la commande à partir d'une ligne de texte.
	 *
	 * @param line La ligne de texte contenant la commande.
	 * @return La commande extraite de la ligne.
	 */
	private String prendreStringDeCommande(String line) {
		return line.split(" ")[0];
	}

	/**
	 * Récupère les arguments à partir d'une ligne de texte.
	 *
	 * @param ligne La ligne de texte contenant les arguments.
	 * @return Les arguments extraits de la ligne.
	 */
	private String[] prendreStringDesArguments(String ligne) {
		String[] parties = ligne.split(" ");
		List<String> args = new ArrayList<String>();
		if (parties.length > 1) {
			for (int i = 1; i < parties.length; i++) {
				args.add(parties[i]);
			}
		}
		return args.toArray(new String[0]);
	}

	/**
	 * Définit la carte utilisée dans le cadre de la chasse au trésor.
	 *
	 * @param c L'objet Carte à définir.
	 */
	public void setCarte(Carte c) {
		this.carte = c;
	}

	/**
	 * Obtient la carte utilisée dans le cadre de la chasse au trésor.
	 *
	 * @return L'objet Carte associé à la chasse au trésor.
	 */
	public Carte getCarte() {
		return this.carte;
	}

	/**
	 * Exécute la séquence de commandes de la chasse au trésor.
	 *
	 * @param imprimerCarte Indique si la carte doit être imprimée à chaque étape.
	 */
	public void jouer(boolean imprimerCarte) {
		for (Commande c : this.commandes) {
			c.executerCommande(this, imprimerCarte);
		}
	}

}
