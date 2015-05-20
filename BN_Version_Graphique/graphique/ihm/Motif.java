/**
 * 
 */
package ihm;

import java.awt.Image;

/**
 * Enumération permettant de définir les motifs des cases
 * 
 * @author Sylvain METAYER - Kevin DESSIMOULIE
 *
 */
public enum Motif {
	TOUCHE, EAU, COUPJOUE;

	@Override
	public String toString() {
		switch (this) {
		case EAU:
			return "~";
		case TOUCHE:
			return "X";
		case COUPJOUE:
			return "@";

		default:
			return null;
		}
	}

	// Pour le graphique ?
	public Image toImage() {
		return null;

	}

}
