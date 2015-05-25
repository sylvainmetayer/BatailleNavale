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

	// TODO gérer le renvoi d'une image pour l'interface graphique, penser à
	// gérer la taille de l'image
	public Image getIcon() {
		return null;

	}

}
