/**
 * 
 */
package ihm.composants;

import ihm.panels.PanelPlateau;
import ihm.panels.PanelPrincipal;

import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import metier.Case;

/**
 * Classe qui etend le comportement d'un bouton. <br>
 * Cette classe contient une case afin de faciliter la gestion evenementielle.
 * 
 * @author Sylvain METAYER - Kevin DESSIMOULIE
 *
 */
public class BoutonBN extends JButton {

	@Override
	public String toString() {
		return "BOUTONBN " + getCase();
	}

	private static final long serialVersionUID = 1L;
	private Case caseBouton;

	/**
	 * Retourne la case contenue dans le bouton
	 * 
	 * @return {@link Case}
	 */
	public Case getCase() {
		return caseBouton;
	}

	/**
	 * Permet d'affecter une nouvelle case au bouton. <br>
	 * Ne devrait pas être utilisé après l'initialisation du jeu...
	 * 
	 * @param c
	 *            {@link Case}
	 */
	public void setCase(Case c) {
		this.caseBouton = c;
	}

	/**
	 * Constructeur permetttant d'instancier un nouveau {@link BoutonBN}
	 * 
	 * @param c
	 *            {@link Case}
	 * @param motif
	 *            {@link String}
	 */
	public BoutonBN(Case c, String motif) {
		super(motif);
		this.caseBouton = c;
		this.setPreferredSize(new Dimension(50, 50));
		// pour pouvoir mettre des icones de 50*50
	}

	/**
	 * <b>Cette méthode ne devrait etre utilisee que par
	 * {@link PanelPlateau#actualisation()}<b> Met à jour le motif de la case et
	 * le texte du motif. <br>
	 * Elle n'est pas recommandé pour d'autres méthodes, car elle n'a aucune
	 * interaction avec le plateau métier. <br>
	 * Il vaut mieux utiliser {@link BoutonBN#setMotifCaseUniquement(String)} et
	 * {@link PanelPlateau#actualisation()} afin de garantir une interaction
	 * avec le plateau. <br>
	 * 
	 * @param motif
	 * @see PanelPlateau#actualisation()
	 * @see BoutonBN#setMotifCaseUniquement(String)
	 * @deprecated depuis que setMotifCaseUniquement est implémenté
	 */
	@Deprecated
	public void setMotifCaseEtPlateau(String motif) {
		caseBouton.setMotif(motif);

		ImageIcon ic;
		ic = PanelPlateau.getIconDiversByMotif(motif);

		if (ic == null)
			ic = PanelPlateau.getIconNavireByMotif(motif);

		try {
			this.setIcon(ic);
		} catch (NullPointerException ex) {
			PanelPrincipal.jta_message
					.append("Le motif est indisponible, utilisation du mode texte..");
		}

		this.setText(motif); // si l'image n'apparait pas, on a quand meme le
								// motif
	}

	/**
	 * Cette méthode permet de mettre uniquement à jour le motif de la case,
	 * sans mettre à jour le motif du bouton. Il faut donc appeler la méthode
	 * {@link PanelPlateau#actualisation()} pour pouvoir faire la mise à jour
	 * ultérieurement.
	 * 
	 * @param motif
	 */
	public void setMotifCaseUniquement(String motif) {
		caseBouton.setMotif(motif);
	}

}
