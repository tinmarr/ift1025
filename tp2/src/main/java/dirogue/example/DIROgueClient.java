package dirogue.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 * Classe représentant un client pour l'application DIROgue. Ce client se
 * connecte à un serveur spécifique et peut envoyer des commandes pour charger,
 * sauvegarder des fichiers ou quitter l'application.
 */
public class DIROgueClient {
	public static void main(String[] args) {
		String serverAddress = "localhost";
		int serverPort = 1370;

		Socket socket = null;
		PrintWriter out = null; // utilisé pour écrire dans le socket avec des commandes comme println()

		try {
			socket = new Socket(serverAddress, serverPort);
			out = new PrintWriter(socket.getOutputStream(), true);
		} catch (UnknownHostException e) {
			System.out.println("Unknown host: " + serverAddress);
			System.exit(1);
		} catch (IOException e) {
			System.out.println("No I/O");
			System.exit(1);
		}

		Scanner scanner = new Scanner(System.in);
		String input;

		while (true) {
			System.out.println("Entrer une commande (load, save, exit):");
			input = scanner.nextLine().trim();

			if (input.equals("load")) {
				System.out.println("Entrez le chemin du fichier que vous souhaitez charger :");
				String filePath = scanner.nextLine().trim();

				try {
					File file = new File(filePath);
					Scanner fileScanner = new Scanner(file);

					while (fileScanner.hasNextLine()) {
						String line = fileScanner.nextLine();
						out.println(line);
					}

					fileScanner.close();
				} catch (FileNotFoundException e) {
					System.out.println("Le fichier n'a pas été trouvé.");
				}

			} else if (input.equals("save")) {
				System.out.println(" Entrez le chemin où vous voulez sauvegarder le rapport :");
				var reportPath = scanner.nextLine().trim();
				out.println(input + " " + reportPath);

			} else if (input.equals("exit")) {
				out.println(input);
				break;
			} else {
				System.out.println("Commande non valide. Veuillez entrer 'load', 'save' ou 'exit'.");
			}
		}

		System.out.println("Sortie du programme.");
		scanner.close();
		if (out != null) {
			out.close();
		}
		if (socket != null) {
			try {
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
