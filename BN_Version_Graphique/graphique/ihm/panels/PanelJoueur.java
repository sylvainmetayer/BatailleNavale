/**
 * 
 */
package ihm.panels;

import ihm.panels.listeners.ListenerPlacementBateaux;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import metier.Jeu;
import metier.Plateau;

/**
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

	private Jeu jeu;
	private Plateau plateau;

	public PanelJoueur(String nomJoueur, Jeu jeu, Plateau plateau) {
		this.nomJoueur = nomJoueur;
		this.jeu = jeu;
		this.plateau = plateau;
		this.score = 0;

		// création du panel plateau
		jpp_panelPlateau = new PanelPlateau(plateau, plateau.getLongueur(), jeu);

		jl_detailsJoueur = new JLabel(this.nomJoueur + ", votre score : "
				+ this.score, JLabel.CENTER);
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
	 * @return
	 */
	public String getNomJoueur() {
		return nomJoueur;
	}

	/**
	 * Permet de définir l'état des boutons de la grille
	 * 
	 * @param enabled
	 */
	public void setEtatGrille(boolean enabled) {
		jpp_panelPlateau.setEtatGrille(false);
	}

	/**
	 * Permet de modifier le message présent en bas de la grille du joueur.
	 * 
	 * @param message
	 */
	public void setMessage(String message) {
		jl_messageDivers.setText(message);
		repaint();
	}

	public void setPlateauListener(ListenerPlacementBateaux l) {
		jpp_panelPlateau.setPlateauListener(l);
	}

	public PanelPlateau getPanelPlateau() {
		return jpp_panelPlateau;
	}

}
