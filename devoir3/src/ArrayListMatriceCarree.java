
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Une implémentation de l'interface {@link MatriceCaree} en utilisant la classe
 * {@link ArrayList}.
 *
 * @author Michalis Famelis
 *
 * @param <E> le type de chose stocké dans la matrice.
 */
public class ArrayListMatriceCarree<E> implements MatriceCarree<E>, Iterable<E> {

	private ArrayList<ArrayList<E>> lignes;
	private int dimension;

	/**
	 * Crée une matrice de dimension 0.
	 */
	public ArrayListMatriceCarree() {
		this(0);
	}

	/**
	 * Crée une matrice carée, avec une dimension donnée. Les cases de la matrice
	 * sont initialisées à {@code null}.
	 *
	 * @param dim - la dimension de la matrice à créer.
	 */
	public ArrayListMatriceCarree(int dim) {
		this.dimension = dim;
		this.lignes = new ArrayList<ArrayList<E>>(this.dimension);
		for (int i = 0; i < this.dimension; i++) {
			ArrayList<E> nouvelleLigne = new ArrayList<E>(this.dimension);
			for (int j = 0; j < this.dimension; j++)
				nouvelleLigne.add(null);
			this.lignes.add(nouvelleLigne);
		}
	}

	@Override
	public int dim() {
		return this.dimension;
	}

	@Override
	public E get(int i, int j) throws MatriceIndexOutOfBoundsException {
		if (i < 0 || i >= this.dim() || j < 0 || j >= this.dim())
			throw new MatriceIndexOutOfBoundsException();
		return this.lignes.get(i).get(j);
	}

	@Override
	public void set(int i, int j, E val) throws MatriceIndexOutOfBoundsException {
		if (i < 0 || i >= this.dim() || j < 0 || j >= this.dim())
			throw new MatriceIndexOutOfBoundsException();
		this.lignes.get(i).set(j, val);
	}

	@Override
	public void ajoute(E val) throws MatricePleineException {
		MatriceIterator<E> it = new MatriceIterator<>(this);
		while (it.hasNext()) {
			E n = it.next();
			if (n == null) {
				it.remplace(val);
				return;
			}
		}
		throw new MatricePleineException();
	}

	@Override
	public Iterator<E> iterator() {
		return new MatriceIterator<E>(this);
	}

	@Override
	public String toString() {
		String ret = "---\n";
		for (var ligne : this.lignes) {
			ret += ligne.toString() + "\n";
		}
		ret += "---\n";
		return ret;
	}

}
