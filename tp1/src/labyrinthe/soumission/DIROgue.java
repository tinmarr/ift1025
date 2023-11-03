package labyrinthe.soumission;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;
import java.util.Scanner;

import labyrinthe.code_squelette.Aventure;
import labyrinthe.code_squelette.Exterieur;
import labyrinthe.code_squelette.Labyrinthe;
import labyrinthe.code_squelette.Piece;
import labyrinthe.code_squelette.RencontreType;
import labyrinthe.rencontres.ArtefactMagique;
import labyrinthe.rencontres.Boss;
import labyrinthe.rencontres.Gargouille;
import labyrinthe.rencontres.Gobelin;
import labyrinthe.rencontres.Orque;
import labyrinthe.rencontres.Potion;
import labyrinthe.rencontres.Rencontre;
import labyrinthe.rencontres.SacDeButin;

public class DIROgue {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Piece[] pieces = new Piece[] { Exterieur.getExterieur() };
        Labyrinthe labyrinthe = new MonLabyrinthe();
        String in = scanner.nextLine().strip();
        while (!in.equals("FIN")) {
            String[] tokens = in.split(" ");
            switch (tokens[0]) {
                case "piece":
                    Piece[] newPieces = new Piece[pieces.length + 1];
                    for (int i = 0; i < pieces.length; i++) {
                        newPieces[i] = pieces[i];
                    }
                    RencontreType rencontre = switch (tokens[2]) {
                        case "boss" -> RencontreType.BOSS;
                        case "monstre" -> RencontreType.MONSTRE;
                        case "tresor" -> RencontreType.TRESOR;
                        default -> RencontreType.RIEN;
                    };
                    newPieces[pieces.length] = new Piece(Integer.parseInt(tokens[1]), rencontre);
                    pieces = newPieces;
                    break;
                case "CORRIDORS":
                    Arrays.sort(pieces, Comparator.comparing(Piece::getID));
                    break;
                case "corridor":
                    if (Integer.parseInt(tokens[1]) >= pieces.length
                            || Integer.parseInt(tokens[2]) >= pieces.length) {
                        System.out.println("ERREUR: piece inexistante");
                        return;
                    }
                    labyrinthe.ajouteCorridor(pieces[Integer.parseInt(tokens[1])],
                            pieces[Integer.parseInt(tokens[2])]);
                    break;
            }
            in = scanner.nextLine().strip();
        }
        scanner.close();
        Aventure aventure = new MonAventure(labyrinthe);
        System.out.println(genererRapport(aventure));
        System.out.println(genererScenario(aventure));
    }

    public static String genererRapport(Aventure a) {
        String out = "Rapport:\n";
        Labyrinthe lab = a.getLabyrinthe();
        out += String.format("Donjon avec %d pieces :\n", lab.nombreDePieces());
        for (Piece p : lab.getPieces()) {
            if (p == null)
                continue;
            out += String.format("%s : [", p.toString());
            for (Piece c : lab.getPiecesConnectees(p)) {
                out += String.format("%s, ", c.toString());
            }
            out = out.substring(0, out.length() - 2) + "]\n";
        }
        if (a.estPacifique()) {
            out += "Pacifique.\n";
        } else {
            out += "Non pacifique.\n";
        }
        if (a.contientBoss()) {
            out += "Contient un boss.\n";
        } else {
            out += "Ne contient pas de boss.\n";
        }
        if (a.contientDuTresor()) {
            out += String.format("Contient %d tresors.\n", a.getTresorTotal());
        } else {
            out += "Ne contient pas de tresor.\n";
        }
        out += "Chemin jusqu'au boss :\n";
        Piece[] chemin = a.cheminJusquAuBoss();
        for (Piece p : chemin) {
            out += String.format("%s\n", p.toString());
        }
        return out;
    }

    public static String genererScenario(Aventure a) {
        String out = "Scenario : \n";
        Piece[] chemin = a.cheminJusquAuBoss();
        for (Piece p : chemin) {
            Rencontre x;
            int r = new Random().nextInt(3);
            switch (p.getRencontreType()) {
                case MONSTRE:
                    switch (r) {
                        case 0:
                            x = new Gobelin();
                            break;
                        case 1:
                            x = new Orque();
                            break;
                        default:
                            x = new Gargouille();
                    }
                    break;
                case TRESOR:
                    switch (r) {
                        case 0:
                            x = new SacDeButin();
                            break;
                        case 1:
                            x = new Potion();
                            break;
                        default:
                            x = new ArtefactMagique();
                    }
                    break;
                case BOSS:
                    x = new Boss();
                    break;
                default:
                    x = new Rencontre();
            }
            out += x.recontrer() + "\n";
        }
        return out;
    }

}
