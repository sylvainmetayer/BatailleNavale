/**
 * 
 */
package ihm.panels;

import ihm.composants.BoutonBN;
import ihm.panels.listeners.ListenerPlacementBateaux;

import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import metier.Jeu;
import metier.Plateau;

/**
 * Cette classe étend le comportement d'un {@link JPanel} pour permettre de
 * représenter un {@link Plateau} graphiquement <br>
 * Un tableau de {@link BoutonBN} est associé au plateau afin de pouvoir les
 * manipuler plus facilement
 * 
 * @author Sylvain - Kevin
 *
 */
public class PanelPlateau extends JPanel {

	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unused")
	private Jeu jeu;
	private Plateau plateau;

	private BoutonBN[][] tableauBoutonsBN;

	/**
	 * Constructeur
	 * 
	 * @param plateau
	 *            {@link Plateau}
	 * @param size
	 *            {@link Integer}
	 * @param jeu
	 *            {@link Jeu}
	 */
	public PanelPlateau(Plateau plateau, int size, Jeu jeu) {
		this.plateau = plateau;
		this.jeu = jeu;
		tableauBoutonsBN = new BoutonBN[size][size];

		this.setLayout(new GridLayout(plateau.getLongueur(), plateau
				.getLongueur()));
		initPlateau();

		// tableauBoutonsBN[5][3].getCase().setMotif("e");
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

	/**
	 * Permet d'acualiser le plateau de bouton en récupérant les motifs des
	 * cases du plateaux.
	 */
	@SuppressWarnings("deprecation")
	public void actualisation() {
		String motif;
		for (int i = 0; i < tableauBoutonsBN.length; i++) {
			for (int j = 0; j < tableauBoutonsBN.length; j++) {
				motif = plateau.getLstCases()[i][j].getMotif();
				tableauBoutonsBN[i][j].setMotifCaseEtPlateau(motif);
			}
		}
		repaint();
	}

	/**
	 * Permet d'activer ou de désactiver la grille de bouton
	 * 
	 * @param enabled
	 *            {@link Boolean}
	 */
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
	 *            {@link ActionListener}
	 */
	public void setPlateauListener(ActionListener l) {
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

	/**
	 * Retourne le plateau du joueur
	 * 
	 * @return {@link Plateau}
	 */
	public Plateau getPlateau() {
		return plateau;
	}

	/**
	 * Retourne le tableau de {@link BoutonBN} du {@link PanelPlateau}
	 * 
	 * @return {@link BoutonBN}[][]
	 */
	public BoutonBN[][] getTableauBoutonsBN() {
		return tableauBoutonsBN;
	}

	/**
	 * Permet de modifier un {@link BoutonBN} à une position x et y donnée
	 * 
	 * @param x
	 *            {@link Integer}
	 * @param y
	 *            {@link Integer}
	 * @param bouton
	 *            {@link BoutonBN}
	 */
	public void setTableauBoutonsBN(int x, int y, BoutonBN bouton) {
		this.tableauBoutonsBN[x][y] = bouton;
	}

}
