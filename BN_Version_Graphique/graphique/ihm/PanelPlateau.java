/**
 * 
 */
package ihm;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

/**
 * @author Sylvain - Kevin
 *
 */
public class PanelPlateau extends JPanel {

	private static final long serialVersionUID = 1L;

	private Jeu jeu;
	private Plateau plateau;

	private BoutonBN[][] tableauBoutonsBN;

	public PanelPlateau(Plateau plateau, int size, Jeu jeu) {
		this.plateau = plateau;
		this.jeu = jeu;
		tableauBoutonsBN = new BoutonBN[size][size];

		this.setLayout(new GridLayout(plateau.getLongueur(), plateau
				.getLongueur()));
		initPlateau();
		tableauBoutonsBN[5][3].getCase().setMotif("e");
		actualisation();
		repaint();

	}

	/**
	 * Méthode pour initialiser le plateau de boutons
	 */
	private void initPlateau() {
		for (int i = 0; i < tableauBoutonsBN.length; i++) {
			for (int j = 0; j < tableauBoutonsBN.length; j++) {
				tableauBoutonsBN[i][j] = new BoutonBN(
						plateau.getLstCases()[i][j],
						plateau.getLstCases()[i][j].getMotif());
				this.add(tableauBoutonsBN[i][j]);
			}
		}
		repaint();
	}

	public void actualisation() {
		String motif;
		for (int i = 0; i < tableauBoutonsBN.length; i++) {
			for (int j = 0; j < tableauBoutonsBN.length; j++) {
				motif = plateau.getLstCases()[i][j].getMotif();
				tableauBoutonsBN[i][j].setMotifCase(motif);
			}
		}
		repaint();
	}

	public void setEtatGrille(boolean enabled) {
		for (int i = 0; i < tableauBoutonsBN.length; i++) {
			for (int j = 0; j < tableauBoutonsBN.length; j++) {
				if (enabled)
					tableauBoutonsBN[i][j].setEnabled(true);
				else
					tableauBoutonsBN[i][j].setEnabled(false);
			}
		}

	}

	/**
	 * Permet d'ajouter un listener sur tous les boutons du plateau. <br>
	 * Cette méthode retire tous les listener précemment ajouté
	 * 
	 * @param l
	 *            {@link ListenerPlacementBateaux}
	 */
	public void setPlateauListener(ListenerPlacementBateaux l) {
		for (int i = 0; i < tableauBoutonsBN.length; i++) {
			for (int j = 0; j < tableauBoutonsBN.length; j++) {

				// on retire les anciens listeners
				for (ActionListener tmp : tableauBoutonsBN[i][j]
						.getActionListeners()) {
					tableauBoutonsBN[i][j].removeActionListener(tmp);
				}

				tableauBoutonsBN[i][j].addActionListener(l);
			}
		}
	}

	public Plateau getPlateau() {
		return plateau;
	}

	public BoutonBN[][] getTableauBoutonsBN() {
		return tableauBoutonsBN;
	}

	public void setTableauBoutonsBN(int x, int y, BoutonBN bouton) {
		this.tableauBoutonsBN[x][y] = bouton;
	}

}
