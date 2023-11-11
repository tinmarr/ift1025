
/**
 * Une Matrice carrée. Par exemple, un MatriceCaree de dimension 3:
 * 
 * <pre>
 * ---
 * [0, 1, 2]
 * [3, 4, 5]
 * [6, 7, 8]
 * ---
 * </pre>
 * 
 * @author Michalis Famelis
 *
 * @param <E> le type de chose stocké dans la matrice.
 */
public interface MatriceCarree<E> extends Iterable<E> {

	/**
	 * Retourne la dimension de la matrice.
	 * 
	 * @return la dimension
	 */
	public int dim();

	/**
	 * Retourne l'élément à la position (i,j).
	 * 
	 * @param i - nombre de ligne
	 * @param j - nombre de colonne
	 * @return l'élément
	 * @throws IndexOutOfBoundsException si {@code i} ou {@code j} sont égales ou
	 *                                   supérieures de la dimension du matrice.
	 *                                   (NB: il s'agit d'une
	 *                                   {@link RuntimeException})
	 */
	public E get(int i, int j) throws MatriceIndexOutOfBoundsException;

	/**
	 * Met la valeur {@code val} à la position (i,j).
	 * 
	 * @param i   - nombre de ligne
	 * @param j   - nombre de colonne
	 * @param val - la valeur à ajouter
	 * @throws IndexOutOfBoundsException si {@code i} ou {@code j} sont égales ou
	 *                                   supérieures de la dimension du matrice.
	 *                                   (NB: il s'agit d'une
	 *                                   {@link RuntimeException})
	 */
	public void set(int i, int j, E val) throws MatriceIndexOutOfBoundsException;

	/**
	 * Ajoute la valeur {@code val} à la première position disponible de la Matrice.
	 * Une position est considerée «disponible» si son valeur est {@code null}. Si
	 * (0,0) est null, c'est celle la première position disponible. Si deux
	 * positions différentes (x1,y1) et (x2,y2) sont disponibles, et x1&lt;x2, nous
	 * préférons (x1,y1). Si deux positions différentes (x1,y1) et (x1,y2) sont
	 * disponibles, et y1&lt;y2,nous préférons (x1,y1). </br>
	 * 
	 * Par exemple, prennez la matrice M:
	 * 
	 * <pre>
	 * ---
	 * [0, 1, 2]
	 * [null, 4, null]
	 * [6, null, 8]
	 * ---
	 * </pre>
	 * 
	 * M.ajoute(100) nous donnera:
	 * 
	 * <pre>
	 * ---
	 * [0, 1, 2]
	 * [100, 4, null]
	 * [6, null, 8]
	 * ---
	 * </pre>
	 * 
	 * Si ensuite on fait M.ajoute(200), cela nous donnera:
	 * 
	 * <pre>
	 * ---
	 * [0, 1, 2]
	 * [100, 4, 200]
	 * [6, null, 8]
	 * ---
	 * </pre>
	 * 
	 * Si ensuite on fait M.ajoute(300), cela nous donnera:
	 * 
	 * <pre>
	 * ---
	 * [0, 1, 2]
	 * [100, 4, 200]
	 * [6, 300, 8]
	 * ---
	 * </pre>
	 * 
	 * </br>
	 * Autrement dit, la matrice suivante peut être créée avec la boucle: {@code
	 * 		for(int x=0; x<3; x++)
	 * 			M.ajoute(x);
	 * }
	 * 
	 * <pre>
	 * ---
	 * [0, 1, 2]
	 * [3, 4, 5]
	 * [6, 7, 8]
	 * ---
	 * </pre>
	 * 
	 * 
	 * 
	 * @param val - la valeur à ajouter
	 * @throws MatricePleineException si la matrice n'a pas de case disponible
	 *                                (c'est à dire, aucune case de la matrice n'a
	 *                                pas la valeur {@code null}).
	 */
	public void ajoute(E val) throws MatricePleineException;

}
