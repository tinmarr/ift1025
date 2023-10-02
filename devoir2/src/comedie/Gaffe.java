package comedie;

public class Gaffe extends Accident {

	public Gaffe(int temps) {
		super(temps, "Bétise");
	}

	@Override
	public String genererRapport() {
		return super.genererRapport() + " (Faites pas attention à cette bétise.)";
	}

}
