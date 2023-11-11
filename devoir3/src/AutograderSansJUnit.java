
import java.util.Iterator;

/**
 * Executer les tests du Autograder sans dependre du JUnit4.
 * 
 * @author Michalis Famelis
 *
 */
public class AutograderSansJUnit {

	public static void main(String[] args) throws MatricePleineException {
		testSet();
		testGetLanceExceptionCorrectement();
		testAjouteLanceExceptionCorrectement();
		testAjoute();
		testIterateurHasNext();
	}

	public static void success(String nomDeTest) {
		System.out.println("Test " + nomDeTest + " reussi.");
	}

	public static void echec(String nomDeTest) {
		System.err.println("Test " + nomDeTest + " echoue!");
	}

	public static void testSet() {
		String ceTest = "testSet";
		MatriceCarree<Integer> x = new ArrayListMatriceCarree<>(2);
		x.set(1, 0, 2);
		Integer expected = 2;
		Integer actual = x.get(1, 0);
		if (expected == actual)
			success(ceTest);
		else
			echec(ceTest);
	}

	public static void testGetLanceExceptionCorrectement() {
		String ceTest = "testGetLanceExceptionCorrectement";
		try {
			MatriceCarree<Integer> x = new ArrayListMatriceCarree<>(2);
			x.set(0, 0, 0);
			x.set(0, 1, 1);
			x.set(1, 0, 2);
			x.set(1, 1, 3);
			x.get(4, 4);
			echec(ceTest);
		} catch (MatriceIndexOutOfBoundsException e) {
			success(ceTest);
		} catch (Exception e) {
			echec(ceTest);
		}
	}

	public static void testAjouteLanceExceptionCorrectement() {
		String ceTest = "testAjouteLanceExceptionCorrectement";
		try {
			MatriceCarree<Integer> x = new ArrayListMatriceCarree<>(2);
			x.set(0, 0, 0);
			x.set(0, 1, 1);
			x.set(1, 0, 2);
			x.set(1, 1, 3);
			x.ajoute(4);
			echec(ceTest);
		} catch (MatricePleineException e) {
			success(ceTest);
		} catch (Exception e) {
			echec(ceTest);
		}
	}

	public static void testAjoute() {
		String ceTest = "testAjoute";
		MatriceCarree<Integer> x = new ArrayListMatriceCarree<>(2);
		try {
			x.ajoute(1);
			x.ajoute(2);
			Integer expected = 2;
			Integer actual = x.get(0, 1);
			if (expected == actual)
				success(ceTest);
			else
				echec(ceTest);
		} catch (MatricePleineException e) {
			echec(ceTest);
		}
	}

	public static void testIterateurHasNext() {
		String ceTest = "testIterateurHasNext";
		MatriceCarree<Integer> x = new ArrayListMatriceCarree<>(2);
		x.set(0, 0, 0);
		x.set(0, 1, 1);
		x.set(1, 0, 2);
		x.set(1, 1, 3);
		Iterator<Integer> it = x.iterator();
		it.next();
		it.next();
		it.next();
		it.next();
		boolean expected = false;
		boolean actual = it.hasNext();
		if (expected == actual)
			success(ceTest);
		else
			echec(ceTest);
	}

}
