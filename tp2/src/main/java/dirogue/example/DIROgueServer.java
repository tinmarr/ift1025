package dirogue.example;

import java.io.IOException;

import dirogue.example.code_squelette.Exterieur;
import dirogue.example.code_squelette.Piece;
import dirogue.example.code_squelette.RencontreType;
import dirogue.example.code_squelette.Server;

/**
 * Classe représentant le serveur pour l'application DIROgue.
 * Ce serveur écoute les commandes provenant du client, telles que la création
 * de pièces, l'ajout de corridors, la fin de la création du labyrinthe, la
 * sauvegarde du rapport d'aventure, etc.
 */
public class DIROgueServer {
	static boolean exterieurAjoute = false;
	static MonLabyrinthe2 l = new MonLabyrinthe2();
	static MonAventure m = null;

	public static void main(String[] args) {

		try {
			Server s = new Server(1370);

			s.addEventHandler((cmd, cmdArgs) -> {
				if (cmd.equals("piece")) {
					if (cmdArgs.length == 2) {
						int id = Integer.parseInt(cmdArgs[0]);
						RencontreType type = RencontreType.valueOf(cmdArgs[1].toUpperCase());
						if (!exterieurAjoute) {
							l.ajouteEntree(Exterieur.getExterieur(), new Piece(id, type));
							exterieurAjoute = true;
						} else {
							l.ajoutePiece(new Piece(id, type));
						}
					}
				}
			});

			s.addEventHandler((cmd, cmdArgs) -> {
				if (cmd.equals("CORRIDORS")) {
					System.out.println("CORRIDORS commande recue...");
				}
			});

			s.addEventHandler((cmd, cmdArgs) -> {
				if (cmd.equals("corridor")) {
					if (cmdArgs.length >= 2) {
						int id1 = Integer.parseInt(cmdArgs[0]);
						int id2 = Integer.parseInt(cmdArgs[1]);
						try {
							l.ajouteCorridor(id1, id2);
						} catch (Exception e) {
							System.out.println("Invalid arguments for corridor command");
						}
					} else {
						System.out.println("Invalid arguments for corridor command");
					}
				}
			});

			s.addEventHandler((cmd, cmdArgs) -> {
				if (cmd.equals("FIN")) {
					System.out.println("FIN commande recue...");
					m = new MonAventure(l);
				}
			});

			s.addEventHandler((cmd, cmdArgs) -> {
				if (cmd.equals("save")) {
					try {
						if (cmdArgs.length == 1) {
							m.sauvegarderRapport(cmdArgs[0]);
						}
					} catch (IOException e) {
						System.out.println("An error occurred while saving the report.");
					}
				}
			});

			s.listen();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
