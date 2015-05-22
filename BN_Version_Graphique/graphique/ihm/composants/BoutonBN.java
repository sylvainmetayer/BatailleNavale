/**
 * 
 */
package ihm.composants;

import ihm.panels.PanelPlateau;

import javax.swing.JButton;

import metier.Case;

/**
 * @author Sylvain - Kevin
 *
 */
public class BoutonBN extends JButton {

	private static final long serialVersionUID = 1L;
	private Case caseBouton;

	public Case getCase() {
		return caseBouton;
	}

	public void setCase(Case c) {
		this.caseBouton = c;
	}

	/**
	 * 
	 * @param c
	 *            {@link Case}
	 * @param motif
	 *            {@link String}
	 */
	public BoutonBN(Case c, String motif) {
		super(motif);
		this.caseBouton = c;
	}

	@Override
	public String toString() {
		return "BOUTONBN " + getCase();
	}

	/**
	 * Met à jour le motif de la case et le texte du motif. <br>
	 * Non recommandé, car n'a aucune interaction avec le plateau. <br>
	 * Il vaut mieux utiliser {@link BoutonBN#setMotifCaseUniquement(String)} et
	 * {@link PanelPlateau#actualisation()} afin de garantir une interaction
	 * avec le plateau.
	 * 
	 * @param motif
	 * @see PanelPlateau#actualisation()
	 * @see BoutonBN#setMotifCaseUniquement(String)
	 * @deprecated depuis que setMotifCaseUniquement est implémenté
	 */
	@Deprecated
	public void setMotifCaseEtPlateau(String motif) {
		caseBouton.setMotif(motif);
		this.setText(motif);
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
