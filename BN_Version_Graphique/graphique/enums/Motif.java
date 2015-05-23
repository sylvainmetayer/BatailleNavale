/**
 * 
 */
package enums;

import java.awt.Image;

/**
 * Enumération permettant de définir les motifs des cases
 * 
 * @author Sylvain METAYER - Kevin DESSIMOULIE
 *
 */
public enum Motif {

	// TODO Si le temps, rechercher chaque appel et remplacer le .toString par
	// le .getMotif() qui est plus propre

	TOUCHE("X"), EAU("~"), COUPJOUE("@"), COULE("$");

	private final String motif;

	private Motif(final String motif) {
		this.motif = motif;
	}

	public String getMotif() {
		return motif;
	}

	@Override
	public String toString() {
		switch (this) {
		case EAU:
			return "~";
		case TOUCHE:
			return "X";
		case COUPJOUE:
			return "@";
		case COULE:
			return "$";
		default:
			return null;
		}
	}

	// TODO gérer le renvoi d'une image pour l'interface graphique
	public Image toImage() {
		return null;

	}

}
