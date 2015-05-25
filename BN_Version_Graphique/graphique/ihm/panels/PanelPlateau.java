/**
 * 
 */
package ihm.panels;

import ihm.composants.BoutonBN;

import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import enums.Motif;
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
		// TODO non fonctionnelle
		BoutonBN[][] boutons = this.getTableauBoutonsBN();
		int taillePlateau = boutons.length;

		// TODO ce qui est en commentaire devrait servir à cacher le plateau
		// adverse, mais ne fonctionne pas. A fixer

		
		// Eau sur tous les motifs
		// Mais autorise coups déjà touchées.
		for (int i = 0; i < taillePlateau; i++) {
			for (int j = 0; j < taillePlateau; j++) {
				if (boutons[i][j].getCase().isEstTouche() 
						|| boutons[i][j].getCase().getMotif() == Motif.COUPJOUE.getMotif())
					boutons[i][j].setMotifCaseUniquement(boutons[i][j].getCase().getMotif());
				else 
				boutons[i][j].setMotifCaseUniquement(Motif.EAU.getMotif());
			}
		}

		actualisation();
		// Après avoir masqué les cases non jouees, on bloque celle jouees
		// bloquerCaseOccupees();
		bloquerCoupJoues(); // à utiliser quand sera fonctionnelle

	}

	/**
	 * Cette méthode permet de bloquer tous les coups déjà joués afin d'éviter
	 * de recliquer dessus.<br>
	 * Ne fonctionne pour le moment pas, puisque elle laisse les coups tirés
	 * dans l'eau actifs.<br>
	 * Actuellement, la méthode {@link PanelPlateau#bloquerCaseOccupees()} est
	 * utilisée en solution temporaire
	 */
	private void bloquerCoupJoues() {
		// TODO fix it
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
				//System.out.println("bouton case touche ?"+ b.getCase().isEstTouche());
				// une vérification ne coute rien..
				if (b.getCase().isEstTouche() || b.getCase().getMotif() == Motif.COUPJOUE.getMotif())
					b.setEnabled(false);
				// else
				// b.setEnabled(true);
			}

		}

	}

	/**
	 * Cette méthode permet de bloquer les cases occupées afin d'éviter de
	 * recliquer dessus. <br>
	 * Cela parcourt chaque case de chaque navire, et desactive les cases pour
	 * lesquelles un tir a déjà été effectué
	 * 
	 * @param caseOccupees
	 */
	@SuppressWarnings("unused")
	private void bloquerCaseOccupees() {

		List<Case> caseOccupees = this.getPlateau().getCasesOccupees();

		List<BoutonBN> boutons = new ArrayList<BoutonBN>();

		BoutonBN tmp;

		if (!caseOccupees.isEmpty() || caseOccupees != null) {

			for (Case c : caseOccupees) {
				tmp = this.getTableauBoutonsBN()[c.getPosx()][c.getPosy()];
				boutons.add(tmp);
			}

			for (BoutonBN b : boutons) {
				// une vérification ne coute rien..
				if (b.getCase().isEstTouche())
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
			
			for (Case c : caseNavire) {
				if(!c.isEstTouche()) {
					switch (tailleNavire) {
					case 2 :c.setMotif("T");
						break;
					case 3 :c.setMotif("D");
						break;
					case 4 :c.setMotif("C");
						break;
					case 5 :c.setMotif("P");
						break;
					default:;
					}
				}
			}
		}
		
		actualisation();

	}

}
