package labyrinthe.soumission;

import labyrinthe.code_squelette.Aventure;
import labyrinthe.code_squelette.Labyrinthe;
import labyrinthe.code_squelette.Piece;
import labyrinthe.code_squelette.RencontreType;

/**
 * Mon implementation d'une Aventure.
 *
 * @author Martin Chaperot
 *
 */
public class MonAventure extends Aventure {

    public MonAventure(Labyrinthe c) {
        super(c);
    }

    @Override
    public boolean estPacifique() {
        Piece[] pieces = this.getLabyrinthe().getPieces();
        for (int i = 0; i < pieces.length; i++) {
            if (pieces[i] == null)
                continue; // Next piece doesn't exist
            if (pieces[i].getRencontreType() == RencontreType.BOSS
                    || pieces[i].getRencontreType() == RencontreType.MONSTRE) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean contientDuTresor() {
        return this.getTresorTotal() > 0;
    }

    @Override
    public int getTresorTotal() {
        Piece[] pieces = this.getLabyrinthe().getPieces();
        int tresor = 0;
        for (int i = 0; i < pieces.length; i++) {
            if (pieces[i] == null)
                continue; // Next piece doesn't exist
            if (pieces[i].getRencontreType() == RencontreType.TRESOR) {
                tresor++;
            }
        }
        return tresor;
    }

    @Override
    public boolean contientBoss() {
        Piece[] pieces = this.getLabyrinthe().getPieces();
        for (int i = 0; i < pieces.length; i++) {
            if (pieces[i] == null)
                continue; // Next piece doesn't exist
            if (pieces[i].getRencontreType() == RencontreType.BOSS) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Piece[] cheminJusquAuBoss() {
        int index = 0;
        Piece[] pieces = this.getLabyrinthe().getPieces();
        Piece[] chemin = new Piece[0];
        if (!this.contientBoss())
            return chemin; // No boss in this labyrinth

        while (index < this.getLabyrinthe().nombreDePieces()) {
            if (pieces[index] == null)
                break; // Next piece doesn't exist
            if (index != 0 && !this.getLabyrinthe().existeCorridorEntre(pieces[index - 1], pieces[index]))
                break; // Next piece is not connected to previous one

            Piece[] tmp = new Piece[chemin.length + 1];
            for (int i = 0; i < chemin.length; i++) {
                tmp[i] = chemin[i];
            }
            tmp[chemin.length] = pieces[index];
            chemin = tmp;
            if (pieces[index].getRencontreType() == RencontreType.BOSS) {
                return chemin; // We found the boss
            }
            index++;
        }

        return new Piece[0];
    }
}
