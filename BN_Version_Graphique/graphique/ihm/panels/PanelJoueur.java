/**
 * 
 */
package ihm.panels;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

import enums.NavireCaracteristique;
import metier.Jeu;
import metier.Plateau;

/**
 * Cette classe étend un {@link JPanel} et permet de représenter l'espace de jeu
 * d'un joueur.<br>
 * Elle contient un {@link PanelPlateau} ainsi que des {@link JLabel} pour
 * afficher les informations du joueur
 * 
 * @author Sylvain - Kevin
 *
 */
public class PanelJoueur extends JPanel {

	private static final long serialVersionUID = 1L;

	private PanelPlateau jpp_panelPlateau;

	// nom + score
	private JLabel jl_detailsJoueur;
	private JLabel jl_messageDivers;

	private String nomJoueur;
	private int score;

	@SuppressWarnings("unused")
	private Jeu jeu;
	@SuppressWarnings("unused")
	private Plateau plateau;

	/**
	 * Constructeur
	 * 
	 * @param nomJoueur
	 *            {@link String}
	 * @param jeu
	 *            {@link Jeu}
	 * @param plateau
	 *            {@link Plateau}
	 */
	public PanelJoueur(String nomJoueur, Jeu jeu, Plateau plateau) {
		this.nomJoueur = nomJoueur;
		this.jeu = jeu;
		this.plateau = plateau;

		jpp_panelPlateau = new PanelPlateau(plateau, plateau.getLongueur(), jeu);

		this.score = this.getPanelPlateau().getPlateau().getScore();
		jl_detailsJoueur = new JLabel(this.nomJoueur + ", votre score : "
				+ this.score + " /" + NavireCaracteristique.getScoreTotal(),
				JLabel.CENTER);
		jl_messageDivers = new JLabel("Placement des bateaux...", JLabel.CENTER);

		this.setLayout(new BorderLayout());

		this.add(jl_detailsJoueur, BorderLayout.NORTH);
		this.add(jl_messageDivers, BorderLayout.SOUTH);
		this.add(jpp_panelPlateau, BorderLayout.CENTER);
		repaint();
	}

	/**
	 * Retourne le nom du joueur
	 * 
	 * @return {@link String}
	 */
	public String getNomJoueur() {
		return nomJoueur;
	}

	/**
	 * Permet de définir l'état des boutons de la grille
	 * 
	 * @param enabled
	 *            {@link Boolean}
	 */
	public void setEtatGrille(boolean enabled) {
		jpp_panelPlateau.setEtatGrille(enabled);
	}

	/**
	 * Permet de modifier le message présent en bas de la grille du joueur.
	 * 
	 * @param message
	 *            {@link String}
	 */
	public void setMessage(String message) {
		jl_messageDivers.setText(message);
		repaint();
	}

	/**
	 * Permet de définit le listener à appliquer sur les boutons du plateau et
	 * retire tous les précédents listeners
	 * 
	 * @param l
	 *            {@link ActionListener}
	 */
	public void setPlateauListener(ActionListener l) {
		jpp_panelPlateau.setPlateauListener(l);
	}

	/**
	 * Retourne le {@link PanelPlateau} du joueur
	 * 
	 * @return {@link PanelPlateau}
	 */
	public PanelPlateau getPanelPlateau() {
		return jpp_panelPlateau;
	}

	/**
	 * Retourne le score du joueur
	 * 
	 * @return {@link Integer}
	 */
	public int getScore() {
		return jpp_panelPlateau.getPlateau().getScore();
	}

	/**
	 * Permet de definir le score
	 * 
	 * @param scoreToAdd
	 *            {@link Integer}
	 */
	public void setScore(int scoreToAdd) {
		jpp_panelPlateau.getPlateau().setScore(scoreToAdd);
		this.score = getScore();
	}

	public void actualisation() {
		this.getPanelPlateau().actualisation();
		String message = this.nomJoueur + ", votre score : " + getScore()
				+ " /" + NavireCaracteristique.getScoreTotal();
		jl_detailsJoueur.setText(message);
		this.repaint();
	}
}
