import java.util.ArrayList;
import java.util.List;

/**
 * La classe CarteAuTresor représente une carte de trésor.
 * Elle permet de manipuler la carte, de déplacer le pirate et de vérifier la
 * présence du trésor.
 */
public class Carte {
	
    private List<List<String>> carte;
    private int pirateX;
    private int pirateY;
    private int tresorX;
    private int tresorY;

    /**
     * Constructeur de la carte au trésor.
     *
     * @param tailleX La largeur de la carte.
     * @param tailleY La hauteur de la carte.
     * @param pirateX La position horizontale initiale du pirate.
     * @param pirateY La position verticale initiale du pirate.
     * @param tresorX La position horizontale du trésor.
     * @param tresorY La position verticale du trésor.
     */
    public Carte(int tailleX, int tailleY, int pirateX, int pirateY, int tresorX, int tresorY) {
    	// Le syntaxe ici est : condition ? valeurSiVrai : valeurSiFaux
        tailleX = tailleX <= 0 ? 10 : tailleX; // equivalent à {if tailleX <=0 then this.tailleX=10 else this.tailleX=tailleX} 
        tailleY = tailleY <= 0 ? 10 : tailleY; // equivalent à {if tailleY <=0 then this.tailleY=10 else this.tailleY=tailleY}

        carte = new ArrayList<>();
        for (int i = 0; i < tailleY; i++) {
            List<String> ligne = new ArrayList<>(tailleX);
            for (int j = 0; j < tailleX; j++) {
                ligne.add("-");
            }
            carte.add(ligne);
        }

        // Le syntaxe ici est deux conditionnels imbriques
        this.pirateX = pirateX >= tailleX ? tailleX - 1 : (pirateX <= 0 ? 0 : pirateX);
        this.pirateY = pirateY >= tailleY ? tailleY - 1 : (pirateY <= 0 ? 0 : pirateY);

        this.tresorX = tresorX >= tailleX ? tailleX - 1 : (tresorX <= 0 ? 0 : tresorX);
        this.tresorY = tresorY >= tailleY ? tailleY - 1 : (tresorY <= 0 ? 0 : tresorY);

        carte.get(this.pirateY).set(this.pirateX, "P");

        if (verifierTresor())
            carte.get(this.tresorY).set(this.tresorX, "PX");
        else
            carte.get(this.tresorY).set(this.tresorX, "X");
    }

    /**
     * Déplace le pirate un seul pas dans la direction spécifiée.
     *
     * @param direction Direction dans laquelle déplacer le pirate (NORD, SUD,
     *                  OUEST, EST).
     */
    public void pas(Direction direction) {
        switch (direction) {
            case NORD:
                deplacerPirate(pirateX, pirateY - 1);
                break;
            case SUD:
                deplacerPirate(pirateX, pirateY + 1);
                break;
            case OUEST:
                deplacerPirate(pirateX - 1, pirateY);
                break;
            case EST:
                deplacerPirate(pirateX + 1, pirateY);
                break;
        }
    }

    // methode auxiliaire non-visible aux autres classes
    private void deplacerPirate(int newX, int newY) {
        if (estMouvementValide(newX, newY)) {
            if (verifierTresor())
                carte.get(pirateY).set(pirateX, "X");
            else
                carte.get(pirateY).set(pirateX, "-");

            pirateX = newX;
            pirateY = newY;

            if (verifierTresor())
                carte.get(pirateY).set(pirateX, "PX");
            else
                carte.get(pirateY).set(pirateX, "P");
        }
    }

    /**
     * Vérifie si le déplacement du pirate est valide dans la carte.
     *
     * @param x Position horizontale.
     * @param y Position verticale.
     * @return true si le déplacement est valide, sinon false.
     */
    private boolean estMouvementValide(int x, int y) {
        return x >= 0 && x < carte.get(0).size() && y >= 0 && y < carte.size();
    }

    /**
     * Vérifie si le pirate est sur le trésor.
     *
     * @return true si le pirate est sur le trésor, sinon false.
     */
    public boolean verifierTresor() {
        return pirateX == tresorX && pirateY == tresorY;
    }

    /**
     * Convertit la carte en une représentation textuelle.
     *
     * @return Représentation textuelle de la carte.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (List<String> ligne : carte) {
            sb.append(String.join(" ", ligne)).append("\n");
        }
        return sb.toString();
    }

    /**
     * Récupère la position horizontale du pirate.
     *
     * @return Position horizontale du pirate.
     */
    public int getPirateX() {
        return this.pirateX;
    }

    /**
     * Récupère la position verticale du pirate.
     *
     * @return Position verticale du pirate.
     */
    public int getPirateY() {
        return this.pirateY;
    }

    /**
     * Récupère la position horizontale du trésor.
     *
     * @return Position horizontale du trésor.
     */
    public int getTresorX() {
        return this.tresorX;
    }

    /**
     * Récupère la position verticale du trésor.
     *
     * @return Position verticale du trésor.
     */
    public int getTresorY() {
        return this.tresorY;
    }

    // Pour verifier la classe CarteAuTrésor
    public static void main(String[] args) {
        Carte carteAuTresor = new Carte(0, 0, -1, -1, 100, 100);
        System.out.println("Carte Initiale:");
        System.out.println(carteAuTresor);

        carteAuTresor.pas(Direction.SUD);
        carteAuTresor.pas(Direction.SUD);
        carteAuTresor.pas(Direction.EST);

        System.out.println("Carte après déplacement:");
        System.out.println(carteAuTresor);

        System.out.println("Le trésor est-il trouvé ? " + carteAuTresor.verifierTresor());
    }
}
