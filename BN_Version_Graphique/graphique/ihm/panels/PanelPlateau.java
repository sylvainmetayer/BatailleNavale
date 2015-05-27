/**
 * 
 */
package ihm.panels;

import ihm.composants.BoutonBN;

import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import outils.MotifsDivers;
import outils.NavireCaracteristique;
import metier.Case;
import metier.Jeu;
import metier.Navire;
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
public class PanelPlateau extends JPanel implements Serializable {

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
				if (enabled) {
					tableauBoutonsBN[i][j].setEnabled(true);
				} else {
					tableauBoutonsBN[i][j].setEnabled(false);
				}
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

	/**
	 * Cette méthode permet de masquer tous les boutons d'un plateau, ne
	 * laissant que ceux qui ont déjà été joués.
	 * 
	 */
	public void masquerPlateau() {
		BoutonBN[][] boutons = this.getTableauBoutonsBN();
		int taillePlateau = boutons.length;

		// Eau sur tous les motifs
		// Mais autorise coups déjà touchées.
		for (int i = 0; i < taillePlateau; i++) {
			for (int j = 0; j < taillePlateau; j++) {
				if (boutons[i][j].getCase().isEstTouche()
						|| boutons[i][j].getCase().getMotif() == MotifsDivers.COUPJOUE
								.getMotif())
					boutons[i][j].setMotifCaseUniquement(boutons[i][j]
							.getCase().getMotif());
				else
					boutons[i][j].setMotifCaseUniquement(MotifsDivers.EAU
							.getMotif());
			}
		}

		actualisation();
		// Après avoir masqué les cases non jouees, on bloque celle jouees
		bloquerCoupJoues(); // à utiliser quand sera fonctionnelle

	}

	/**
	 * Cette méthode permet de bloquer tous les coups déjà joués afin d'éviter
	 * de recliquer dessus.<br>
	 */
	private void bloquerCoupJoues() {
		boolean[][] coupJoues = this.getPlateau().getCoupsJoues();

		List<BoutonBN> boutons = new ArrayList<BoutonBN>();
		BoutonBN tmp;

		if (coupJoues != null) {
			for (int i = 0; i < this.getTableauBoutonsBN().length; i++) {
				for (int j = 0; j < this.getTableauBoutonsBN().length; j++) {
					if (coupJoues[i][j]) {
						tmp = this.getTableauBoutonsBN()[i][j];
						boutons.add(tmp);
					}
				}
			}

			for (BoutonBN b : boutons) {
				// une vérification ne coute rien..
				if (b.getCase().isEstTouche()
						|| b.getCase().getMotif() == MotifsDivers.COUPJOUE
								.getMotif())
					b.setEnabled(false);
				// else
				// b.setEnabled(true);
			}

		}

	}

	/**
	 * Cette méthode permet de mettre le plateau avec les motifs des bateaux du
	 * joueurs ainsi que leur emplacements.<br>
	 * A utiliser avant de jouer pour le joueurAttaquant
	 */
	public void unmaskPlateau() {

		List<Navire> listNav = this.getPlateau().getListeNav();

		for (Navire n : listNav) {
			int tailleNavire = n.getTaille();
			List<Case> caseNavire = n.getCases();
			NavireCaracteristique nc = NavireCaracteristique
					.getCaracteristiqueByTaille(n.getTaille());
			for (Case c : caseNavire) {
				if (!c.isEstTouche()) {
					switch (tailleNavire) {
					case 2:
						c.setMotif(nc.getMotif());
						break;
					case 3:
						c.setMotif(nc.getMotif());
						break;
					case 4:
						c.setMotif(nc.getMotif());
						break;
					case 5:
						c.setMotif(nc.getMotif());
						break;
					default:
						;
					}
				}
			}
		}

		actualisation();

	}

	public static ImageIcon getIconNavireByMotif(String motif) {

		for (NavireCaracteristique n : NavireCaracteristique.values()) {
			if (motif.equals(n.getMotif())) {
				try {
					return n.getIcon();
				} catch (NullPointerException ex) {
					PanelPrincipal.jta_message
							.append("Le motif est indisponible, utilisation du mode texte..");
				}

			}
		}
		return null;
	}

	public static ImageIcon getIconDiversByMotif(String motif) {
		for (MotifsDivers m : MotifsDivers.values()) {
			if (motif.equals(m.getMotif())) {
				try {
					return m.getIcon();
				} catch (NullPointerException ex) {
					PanelPrincipal.jta_message
							.append("Le motif est indisponible, utilisation du mode texte..");
				}
			}
		}
		return null;
	}

}
