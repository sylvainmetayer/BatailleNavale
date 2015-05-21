/**
 * 
 */
package ihm;

import javax.swing.JPanel;

/**
 * @author Sylvain - Kevin
 *
 */
public class PanelPlateau extends JPanel {

	private static final long serialVersionUID = 1L;
	Plateau plateau;
	BoutonBN[][] tableau;

	public PanelPlateau(Plateau plateau, int size) {
		this.plateau = plateau;
		tableau = new BoutonBN[size][size];
		init();
	}

	private void init() {
		for (int i = 0; i < tableau.length; i++) {
			for (int j = 0; j < tableau.length; j++) {
				tableau[i][j] = new BoutonBN(plateau.getLstCases()[i][j], s)
			}
		}
	}

}
