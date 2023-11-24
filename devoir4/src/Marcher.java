
/**
 * La classe Marcher étend la classe Commande et représente la commande de
 * déplacement dans le contexte de la chasse au trésor.
 */
public class Marcher extends Commande {

    /**
     * La direction dans laquelle effectuer le déplacement.
     */
    private Direction direction;

    /**
     * Le nombre de pas à effectuer.
     */
    private int nombrePas;

    /**
     * Construit un objet Marcher avec la direction et le nombre de pas spécifiés.
     *
     * @param d La direction du déplacement.
     * @param n Le nombre de pas à effectuer.
     */
    public Marcher(Direction d, int n) {
        this.direction = d;
        this.nombrePas = n;
    }

    /**
     * Exécute la commande de déplacement en effectuant le nombre de pas spécifié
     * dans la direction donnée.
     *
     * @param jeu          L'objet ChasseAuTresor sur lequel la commande doit être
     *                     exécutée.
     * @param montrerCarte Indique si la carte doit être affichée pendant
     *                     l'exécution de la commande.
     */
    @Override
    public void executerCommande(ChasseAuTresor jeu, boolean montrerCarte) {
        for (int i = 0; i < this.nombrePas; i++) {
            jeu.getCarte().pas(this.direction);
        }
        if (montrerCarte)
            System.out.println(jeu.getCarte().toString());
    }

}
