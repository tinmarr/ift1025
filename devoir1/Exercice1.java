import java.util.Arrays;
import java.util.Scanner;

/**
 * Classe pour l'Exercise1 du IFT1025 Programmation 2, Automne 2023.
 */
public class Exercice1 {

    /**
     * Cette fonction prend un tableau de Strings en paramètre et retourne un
     * nouveau tableau contenant les mêmes éléments, avec une case disponible de
     * plus.
     *
     * @param tab un tableau de Strings
     * @return un tableau de Strings avec tout contenu de l'entrée,
     *         plus une case avec la valeur null à la fin.
     *         Si l'entrée est null, la fonction retourne null aussi.
     */
    public static String[] agrandirTab(String[] tab) {
        String[] newTab = new String[tab.length + 1];
        for (int i = 0; i < tab.length; i++) {
            newTab[i] = tab[i];
        }
        return newTab; // votre solution va changer ceci.
    }

    /**
     * Cette fonction lit des mots sur la ligne de commande et les retourne dans
     * un tableau de Strings. Chaque "mot" est définit comme une ligne complète,
     * du début de la ligne entrée jusqu'au prochain \n.
     * Ne changez pas cette fonction!
     *
     * @return un tableau avec tous les mots lus sauf "stop".
     */
    public static String[] lireMots() {
        Scanner s = new Scanner(System.in);

        String[] mots = new String[1];

        String next = s.nextLine();
        while (!next.equalsIgnoreCase("stop")) {
            mots[mots.length - 1] = next;
            mots = agrandirTab(mots);
            next = s.nextLine();
        }
        s.close();

        return mots;
    }

    /**
     * Cette fonction prend en paramètre un tableau de mots et retourne un
     * nouveau tableau contenant ces mots triés en ordre croissant.
     * Ne changez pas cette fonction!
     *
     * @param mots le tableau à trier
     * @return copie du tableau d'entrée, dont les éléments sont triés
     */
    public static String[] trier(String[] mots) {
        String[] copy = Arrays.copyOf(mots, mots.length);
        Arrays.sort(copy);
        return copy;
    }

    /**
     * Fonction principale du programme.
     * Ne changez pas cette fonction!
     *
     * @param args paramètres de ligne de commande
     */
    public static void main(String[] args) {
        String[] mots = lireMots();

        mots = Arrays.copyOf(mots, mots.length - 1);

        mots = trier(mots);

        for (String m : mots)
            System.out.println(m);

    }
}
