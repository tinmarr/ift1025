import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

public class Autograder {

	@Rule
	public TemporaryFolder tempFolder = new TemporaryFolder();

	@Before
	public void setUp() {
	}

	@Test
	public void testCreerCarte() throws IOException {
		File tempFile = tempFolder.newFile("temp_commands.txt");

		try (BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
			writer.write("carte 10 10 1 1 5 5\n");
		}

		ChasseAuTresor chasseAuTresor = new ChasseAuTresor(tempFile.getAbsolutePath(), "");
		chasseAuTresor.jouer(false);

		assertEquals(1, chasseAuTresor.getCarte().getPirateX());
		assertEquals(1, chasseAuTresor.getCarte().getPirateY());

		assertEquals(5, chasseAuTresor.getCarte().getTresorX());
		assertEquals(5, chasseAuTresor.getCarte().getTresorY());
	}

	@Test
	public void testCreerResultat() throws IOException {
		File tempFile = tempFolder.newFile("temp_commands.txt");
		File tempFile1 = tempFolder.newFile("temp_result.txt");

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

		assertEquals("Le pirate a trouve le tresor!", line);
	}

	@Test
	public void testMarcher() {
		ChasseAuTresor chasseAuTresor = new ChasseAuTresor();
		String[] vals = { "10", "10", "1", "1", "5", "5" };
		Commande c1 = new CreerCarte(vals);
		Commande c2 = new Marcher(Direction.EST, 5);
		Commande c3 = new Marcher(Direction.NORD, 5);

		chasseAuTresor.getCommandes().add(c1);
		chasseAuTresor.getCommandes().add(c2);
		chasseAuTresor.getCommandes().add(c3);
		chasseAuTresor.jouer(false);

		assertEquals(6, chasseAuTresor.getCarte().getPirateX());
		assertEquals(0, chasseAuTresor.getCarte().getPirateY());
	}

	@Test
	public void testChasseAuTresorComplet() throws IOException {
		File tempFile = tempFolder.newFile("temp_commands.txt");
		File tempFile1 = tempFolder.newFile("temp_result.txt");

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

		assertEquals("Le pirate a trouve le tresor!", line);
	}

	@Test
	public void testChasseAuTresorCompletSansFichier() {
		ChasseAuTresor chasseAuTresor = new ChasseAuTresor();
		String[] vals = { "4", "4", "0", "3", "3", "1" };

		chasseAuTresor.getCommandes().add(new CreerCarte(vals));
		chasseAuTresor.getCommandes().add(new Marcher(Direction.NORD, 3));
		chasseAuTresor.getCommandes().add(new Marcher(Direction.EST, 3));
		chasseAuTresor.getCommandes().add(new Marcher(Direction.OUEST, 1));
		chasseAuTresor.getCommandes().add(new Marcher(Direction.SUD, 1));
		chasseAuTresor.jouer(false);

		assertEquals(2, chasseAuTresor.getCarte().getPirateX());
		assertEquals(1, chasseAuTresor.getCarte().getPirateY());

		assertEquals(false, chasseAuTresor.getCarte().verifierTresor());
	}
}
