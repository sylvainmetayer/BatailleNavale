/**
 * 
 */
package ihm.composants;

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

	public void setMotifCase(String motif) {
		caseBouton.setMotif(motif);
		this.setText(motif);
	}
}
