/**
 * 
 */
package ihm;

import java.awt.GridLayout;

import javax.swing.JPanel;

/**
 * @author Sylvain - Kevin
 *
 */
public class PanelPlateau extends JPanel {

	private static final long serialVersionUID = 1L;

	private Jeu jeu;
	private Plateau plateau;

	private BoutonBN[][] tableau;

	public PanelPlateau(Plateau plateau, int size, Jeu jeu) {
		this.plateau = plateau;
		this.jeu = jeu;
		tableau = new BoutonBN[size][size];

		this.setLayout(new GridLayout(plateau.getLongueur(), plateau
				.getLongueur()));
		initPlateau();
		repaint();

	}

	private void initPlateau() {
		for (int i = 0; i < tableau.length; i++) {
			for (int j = 0; j < tableau.length; j++) {
				tableau[i][j] = new BoutonBN(plateau.getLstCases()[i][j],
						plateau.getLstCases()[i][j].getMotif());
				this.add(tableau[i][j]);
			}
		}
	}

	private void actualisation() {
		// TODO

	}

	public void setEtatGrille(boolean enabled) {
		for (int i = 0; i < tableau.length; i++) {
			for (int j = 0; j < tableau.length; j++) {
				if (enabled)
					tableau[i][j].setEnabled(true);
				else
					tableau[i][j].setEnabled(false);
			}
		}

	}

}
