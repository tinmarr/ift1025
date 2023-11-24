import java.io.FileWriter;
import java.io.IOException;

/**
 * La classe Creuser étend la classe Commande et représente la commande de
 * creusement dans le contexte de la chasse au trésor.
 */
public class Creuser extends Commande {

	/**
	 * Exécute la commande de creusement, vérifie si le trésor est trouvé et génère
	 * un rapport dans le fichier specifié par le jeu.
	 *
	 * @param jeu          L'objet ChasseAuTresor sur lequel la commande doit être
	 *                     exécutée.
	 * @param montrerCarte Indique si la carte doit être affichée pendant
	 *                     l'exécution de la commande.
	 */
	@Override
	public void executerCommande(ChasseAuTresor jeu, boolean montrerCarte) {
		String resultat;
		if (jeu.getCarte().verifierTresor()) {
			resultat = "Le pirate a trouve le tresor!";
		} else {
			resultat = "Tresor non trouve! Essayez a nouveau.";
		}
		if (montrerCarte)
			System.out.println(jeu.getCarte().toString());

		try {
			FileWriter writer = new FileWriter(jeu.getFileOut().getAbsolutePath());
			writer.write(resultat);
			writer.close();
			System.out.println("Rapport mis dans le fichier " + jeu.getFileOut().getName());
		} catch (IOException e) {
			System.out.println("Erreur lors de l'écriture du rapport dans le fichier " + jeu.getFileOut().getName());
		}

	}

}
