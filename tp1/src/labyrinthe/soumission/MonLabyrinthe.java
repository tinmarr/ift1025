package labyrinthe.soumission;

import labyrinthe.code_squelette.Exterieur;
import labyrinthe.code_squelette.Labyrinthe;
import labyrinthe.code_squelette.Piece;

/**
 * Mon implementation de l'interface Labyrinthe.
 *
 * @author Martin Chaperot
 *
 */
public class MonLabyrinthe implements Labyrinthe {

    private int pieceCount = 0;
    private Piece[] pieces = new Piece[50];
    private Piece[][] adjacence = new Piece[50][0];

    @Override
    public Piece[] getPieces() {
        return this.pieces;
    }

    @Override
    public int nombreDePieces() {
        return this.pieceCount;
    }

    @Override
    public void ajouteEntree(Exterieur out, Piece e) {
        this.ajouteCorridor(out, e);
    }

    @Override
    public void ajouteCorridor(Piece e1, Piece e2) {
        if (this.existeCorridorEntre(e1, e2))
            return;

        if (this.pieces[e1.getID()] != null) {
            Piece[] newAdjacence = new Piece[this.adjacence[e1.getID()].length + 1];
            for (int i = 0; i < this.adjacence[e1.getID()].length; i++) {
                newAdjacence[i] = this.adjacence[e1.getID()][i];
            }
            newAdjacence[this.adjacence[e1.getID()].length] = e2;
            this.adjacence[e1.getID()] = newAdjacence;
        } else {
            this.pieceCount++;
            this.pieces[e1.getID()] = e1;
            this.adjacence[e1.getID()] = new Piece[] { e2 };
        }
        if (this.pieces[e2.getID()] != null) {
            Piece[] newAdjacence = new Piece[this.adjacence[e2.getID()].length + 1];
            for (int i = 0; i < this.adjacence[e2.getID()].length; i++) {
                newAdjacence[i] = this.adjacence[e2.getID()][i];
            }
            newAdjacence[this.adjacence[e2.getID()].length] = e1;
            this.adjacence[e2.getID()] = newAdjacence;
        } else {
            this.pieceCount++;
            this.pieces[e2.getID()] = e2;
            this.adjacence[e2.getID()] = new Piece[] { e1 };
        }
    }

    @Override
    public boolean existeCorridorEntre(Piece e1, Piece e2) {
        if (this.pieces[e1.getID()] != null && this.pieces[e2.getID()] != null) {
            for (Piece el : this.adjacence[e1.getID()]) {
                if (el.getID() == e2.getID()) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public Piece[] getPiecesConnectees(Piece e) {
        if (this.pieces[e.getID()] != null) {
            return this.adjacence[e.getID()];
        }
        return null;
    }
}
