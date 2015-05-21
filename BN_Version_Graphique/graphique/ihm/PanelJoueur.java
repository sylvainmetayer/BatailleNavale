/**
 * 
 */
package ihm;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * @author Sylvain - Kevin
 *
 */
public class PanelJoueur extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private PanelPlateau jp_panelPlateau;

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
		jp_panelPlateau = new PanelPlateau(plateau, plateau.getLongueur(), jeu);

		jl_detailsJoueur = new JLabel(this.nomJoueur + ", votre score : "
				+ this.score, JLabel.CENTER);
		jl_messageDivers = new JLabel("Placement des bateaux...", JLabel.CENTER);

		this.setLayout(new BorderLayout());

		this.add(jl_detailsJoueur, BorderLayout.NORTH);
		this.add(jl_messageDivers, BorderLayout.SOUTH);
		this.add(jp_panelPlateau, BorderLayout.CENTER);
		repaint();
	}

	public String getNomJoueur() {
		return nomJoueur;
	}

	public void setEtatGrille(boolean enabled) {
		jp_panelPlateau.setEtatGrille(false);
	}

	public void setMessage(String message) {
		jl_messageDivers.setText(message);
		repaint();
	}
}
