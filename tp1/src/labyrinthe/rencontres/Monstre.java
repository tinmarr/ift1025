package labyrinthe.rencontres;

/**
 * Un Monstre
 *
 * @author Martin Chaperot
 */
public class Monstre extends Rencontre {
    @Override
    public String recontrer() {
        return String.format("Un %s affreux!", this.getClass().getSimpleName().toLowerCase());
    }
}
