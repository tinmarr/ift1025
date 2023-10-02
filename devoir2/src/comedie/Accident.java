package comedie;

public class Accident {

	/**
	 * Chaque accident doit recevoir un identifiant unique.
	 * L'attribut identificateurs sert à connaître le dernier
	 * identifiant unique d'accident disponible
	 */
	private static int identificateurs = 0;

	/**
	 * Un int pour stocker l'identifiant d'un accident
	 */
	private int id;

	/**
	 * Un String pour stocker la cause d'un accident
	 */
	private String cause;

	/**
	 * Un int pour stocker la timestamp d'un accident
	 */
	private int horodatage;

	public Accident(int temps, String c) {
		id = prochainID();
		horodatage = temps;
		cause = c;
	}

	public String genererRapport() {
		return Integer.toString(horodatage) + "> Accident " + Integer.toString(id) + " à cause de: " + cause;
	}

	private static int prochainID() {
		identificateurs += 1;
		return identificateurs - 1;
	}

	public static int getNombreDesAccidents() {
		return identificateurs;
	}

	public static void setNombreDesAccidents(int n) {
		identificateurs = 0;
	}

	public String getCause() {
		return cause;
	}

	public void setCause(String c) {
		cause = c;
	}

}
