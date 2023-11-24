import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class AutograderSansJunit {

	public static void main(String[] args) throws IOException {
		System.out.println("***********************************");
		testCreerCarte();
		System.out.println("***********************************");
		testCreerResultat();
		System.out.println("***********************************");
		testMarcher();
		System.out.println("***********************************");
		testChasseAuTresorComplet();
		System.out.println("***********************************");
		testChasseAuTresorCompletSansFichier();
		System.out.println("***********************************");
	}

	public static void success(String nomDeTest) {
		System.out.println("Test " + nomDeTest + " reussi.");
	}

	public static void echec(String nomDeTest) {
		System.err.println("Test " + nomDeTest + " echoue!");
	}

	public static void testCreerCarte() throws IOException {
		File tempFile = File.createTempFile("temp_commands", ".txt");

		try (BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
			writer.write("carte 10 10 1 1 5 5\n");
		}

		ChasseAuTresor chasseAuTresor = new ChasseAuTresor(tempFile.getAbsolutePath(), "");
		chasseAuTresor.jouer(false);

		var a1 = assertEquals(1, chasseAuTresor.getCarte().getPirateX());
		var a2 = assertEquals(1, chasseAuTresor.getCarte().getPirateY());

		var a3 = assertEquals(5, chasseAuTresor.getCarte().getTresorX());
		var a4 = assertEquals(5, chasseAuTresor.getCarte().getTresorY());

		if (a1 && a2 && a3 && a4)
			success("testCreerCarte");
		else
			echec("testCreerCarte");
	}

	public static void testCreerResultat() throws IOException {
		File tempFile = File.createTempFile("temp_commands", ".txt");
		File tempFile1 = File.createTempFile("temp_result", ".txt");

		try (BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
			writer.write("carte 10 10 1 1 1 1\n");
			writer.write("creuser\n");
		}

		ChasseAuTresor chasseAuTresor = new ChasseAuTresor(tempFile.getAbsolutePath(), tempFile1.getAbsolutePath());
		chasseAuTresor.jouer(false);

		String line;
		try (BufferedReader reader = new BufferedReader(new FileReader(tempFile1))) {
			line = reader.readLine();
		}

		var a1 = assertEquals("Le pirate a trouve le tresor!", line);

		if (a1)
			success("testCreerResultat");
		else
			echec("testCreerResultat");
	}

	public static void testMarcher() {
		ChasseAuTresor chasseAuTresor = new ChasseAuTresor();
		String[] vals = { "10", "10", "1", "1", "5", "5" };
		Commande c1 = new CreerCarte(vals);
		Commande c2 = new Marcher(Direction.EST, 5);
		Commande c3 = new Marcher(Direction.NORD, 5);

		chasseAuTresor.getCommandes().add(c1);
		chasseAuTresor.getCommandes().add(c2);
		chasseAuTresor.getCommandes().add(c3);
		chasseAuTresor.jouer(false);

		var a1 = assertEquals(6, chasseAuTresor.getCarte().getPirateX());
		var a2 = assertEquals(0, chasseAuTresor.getCarte().getPirateY());

		if (a1 && a2)
			success("testMarcher");
		else
			echec("testMarcher");
	}

	public static void testChasseAuTresorComplet() throws IOException {
		File tempFile = File.createTempFile("temp_commands", ".txt");
		File tempFile1 = File.createTempFile("temp_result", ".txt");

		try (BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
			writer.write("carte 4 4 0 3 3 1\n");
			writer.write("n 3\n");
			writer.write("e 3\n");
			writer.write("o 1\n");
			writer.write("s 1\n");
			writer.write("e 1\n");
			writer.write("creuser\n");
		}

		ChasseAuTresor chasseAuTresor = new ChasseAuTresor(tempFile.getAbsolutePath(), tempFile1.getAbsolutePath());
		chasseAuTresor.jouer(false);

		String line;
		try (BufferedReader reader = new BufferedReader(new FileReader(tempFile1))) {
			line = reader.readLine();
		}

		var a1 = assertEquals("Le pirate a trouve le tresor!", line);

		if (a1)
			success("testChasseAuTresorComplet");
		else
			echec("testChasseAuTresorComplet");
	}

	public static void testChasseAuTresorCompletSansFichier() {
		ChasseAuTresor chasseAuTresor = new ChasseAuTresor();
		String[] vals = { "4", "4", "0", "3", "3", "1" };

		chasseAuTresor.getCommandes().add(new CreerCarte(vals));
		chasseAuTresor.getCommandes().add(new Marcher(Direction.NORD, 3));
		chasseAuTresor.getCommandes().add(new Marcher(Direction.EST, 3));
		chasseAuTresor.getCommandes().add(new Marcher(Direction.OUEST, 1));
		chasseAuTresor.getCommandes().add(new Marcher(Direction.SUD, 1));
		chasseAuTresor.jouer(false);

		var a1 = assertEquals(2, chasseAuTresor.getCarte().getPirateX());
		var a2 = assertEquals(1, chasseAuTresor.getCarte().getPirateY());

		var a3 = assertEquals(false, chasseAuTresor.getCarte().verifierTresor());

		if (a1 && a2 && a3)
			success("testChasseAuTresorCompletSansFichier");
		else
			echec("testChasseAuTresorCompletSansFichier");
	}

	public static boolean assertEquals(Object expected, Object actual) {
		return expected.equals(actual);
	}
}
