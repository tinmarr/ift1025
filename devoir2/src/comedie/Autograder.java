package comedie;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class Autograder {

	@Before
	public void resetIDs() {
		Accident.setNombreDesAccidents(0);
	}

	// Tester la generation de rapports des Accidents
	@Test
	public void AccidentRapportTest() {
		Accident a = new Accident(10, "Bétise");
		String actual = a.genererRapport();
		String expected = "10> Accident 0 à cause de: Bétise";
		Assert.assertEquals(expected, actual);
	}
	
	
	// Tester le constructeur de la Gaffe.
	@Test
	public void GaffeConstructorTest() {
		Accident x = new Gaffe(0);
		String actual = x.getCause();
		String expected = "Bétise";
		Assert.assertEquals(expected, actual);
	}
	
	// Tester la generation de rapports des Gaffes
	@Test
	public void GaffeRapportTest() {
		Accident a = new Gaffe(10);
		String actual = a.genererRapport();
		String expected = "10> Accident 0 à cause de: Bétise (Faites pas attention à cette bétise.)";
		Assert.assertEquals(expected, actual);
	}

	// Tester qu'on compte les accidents
	@Test
	public void NombreDesAccidents1() {
		Accident[] tragedie = { 
				new Accident(10, "Piano"), 
				new Accident(20, "Poisson"), 
				new Accident(20, "Guépard") };
		int actual = Accident.getNombreDesAccidents();
		int expected = 3;
		Assert.assertEquals(expected, actual);
	}
	
	// Tester qu'on compte les gaffes comme des accidents
	@Test
	public void NombreDesAccidents2() {
		Gaffe[] comedie = { 
				new Gaffe(100), 
				new Gaffe(200), 
				new Gaffe(300) };
		int actual = Accident.getNombreDesAccidents();
		int expected = 3;
		Assert.assertEquals(expected, actual);
	}

}
