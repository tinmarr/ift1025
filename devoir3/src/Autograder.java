
import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Test;

public class Autograder {

	@Test
	public void testSet() {
		MatriceCarree<Integer> x = new ArrayListMatriceCarree<>(2);
		x.set(1, 0, 2);
		Integer expected = 2;
		Integer actual = x.get(1, 0);
		assertEquals(expected, actual);
	}

	@Test(expected = MatriceIndexOutOfBoundsException.class)
	public void testGetLanceExceptionCorrectement() {
		MatriceCarree<Integer> x = new ArrayListMatriceCarree<>(2);
		x.set(0, 0, 0);
		x.set(0, 1, 1);
		x.set(1, 0, 2);
		x.set(1, 1, 3);
		x.get(4, 4);
	}

	@Test(expected = MatricePleineException.class)
	public void testAjouteLanceExceptionCorrectement() throws MatricePleineException {
		MatriceCarree<Integer> x = new ArrayListMatriceCarree<>(2);
		x.set(0, 0, 0);
		x.set(0, 1, 1);
		x.set(1, 0, 2);
		x.set(1, 1, 3);
		x.ajoute(4);
	}

	@Test
	public void testAjoute() {
		MatriceCarree<Integer> x = new ArrayListMatriceCarree<>(2);
		try {
			x.ajoute(1);
			x.ajoute(2);
			Integer expected = 2;
			Integer actual = x.get(0, 1);
			assertEquals(expected, actual);
		} catch (MatricePleineException e) {
			fail();
		}
	}

	@Test
	public void testIterateurHasNext() {
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
		assertEquals(expected, actual);
	}

}
