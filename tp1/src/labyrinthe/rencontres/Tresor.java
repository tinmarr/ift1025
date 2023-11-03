package labyrinthe.rencontres;

/**
 * Un tresor.
 *
 * @author Martin Chaperot
 */
public class Tresor extends Rencontre {
    /**
     * Le nom du tresor.
     *
     * @return Le nom du tresor.
     */
    public String getNom() {
        return "Tresor";
    }

    @Override
    public String recontrer() {
        return String.format("%s ! Quelle chance !", this.getNom());
    }
}
