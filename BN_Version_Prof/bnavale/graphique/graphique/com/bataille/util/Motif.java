package graphique.com.bataille.util;

import java.awt.Image;

/**
 * Enumération permettant de définir les motifs des cases
 * 
 * @author Sylvain METAYER - Kevin DESSIMOULIE
 *
 */
public enum Motif {
	NAVIRESIZE1, NAVIRESIZE2, NAVIRESIZE3, NAVIRESIZE4, NAVIRESIZE5, TOUCHE, EAU, COUPJOUE;

	@Override
	public String toString() {
		switch (this) {
		case EAU:
			return "~";
		case TOUCHE:
			return "X";
		case COUPJOUE:
			return "@";
		case NAVIRESIZE1:
			return "V";
		case NAVIRESIZE2:
			return "T";
		case NAVIRESIZE3:
			return "D";
		case NAVIRESIZE4:
			return "C";
		case NAVIRESIZE5:
			return "P";
		default:
			return null;
		}
	}

	// Pour le graphique ?
	public Image toImage() {
		return null;

	}

}
