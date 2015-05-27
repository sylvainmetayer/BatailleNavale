/**
 * 
 */
package outils;

import javax.swing.ImageIcon;

/**
 * Enumération permettant de définir les motifs des cases
 * 
 * @author Sylvain METAYER - Kevin DESSIMOULIE
 *
 */
public enum MotifsDivers {

	TOUCHE("X", "touche.jpg", "Représente une case d'un navire touché"), EAU(
			"~", "eau.jpg", "Réprésente l'eau"), COUPJOUE("@", "coupJoue.jpg",
			"Représente un tir n'ayant touché aucun navire"), COULE("#",
			"coule.jpg", "Représente un navire coulé");

	private final String motif;
	private final String cheminIcon;
	private final String textEnrichi;

	private MotifsDivers(final String motif, final String cheminIcon,
			final String textEnrichi) {
		this.motif = motif;
		this.cheminIcon = cheminIcon;
		this.textEnrichi = textEnrichi;
	}

	/**
	 * Retourne le motif associé
	 * 
	 * @return {@link String}
	 */
	public String getMotif() {
		return motif;
	}

	/**
	 * Retourne l'icone correspondant au {@link MotifsDivers}
	 * 
	 * @return {@link ImageIcon}
	 */
	public ImageIcon getIcon() throws NullPointerException {

		try {
			ImageIcon ic = new ImageIcon(this.getClass().getResource(
					Options.getPrefixeDossierImage() + cheminIcon));
			return ic;
		} catch (NullPointerException e) {
			return null;
		}

	}

	/**
	 * Retourne la description détaillée du {@link MotifsDivers}
	 * 
	 * @return {@link String}
	 */
	public String getTextEnrichi() {
		return textEnrichi;
	}

}
