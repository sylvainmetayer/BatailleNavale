package console.enums;

//TODO
/**
 * Permet de définir les motifs d'une case selon son état.
 * 
 * @author Sylvain
 *
 */
public enum Motif {

	VIDE, TOUCHE, COULE, BAT1, BAT2, BAT3, BAT4, BAT5;

	@Override
	public String toString() {
		switch (this) {
		case BAT1:
			return "";
		case BAT2:
			return "";
		case BAT3:
			return "";
		case BAT4:
			return "";
		case BAT5:
			return "";
		case COULE:
			return "";
		case TOUCHE:
			return "";
		case VIDE:
			return "";
		default:
			return "Erreur";
		}
	}
}
