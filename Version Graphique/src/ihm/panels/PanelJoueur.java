/**
 * 
 */
package ihm.panels;

import ihm.panels.listeners.ListenerSauvegarder;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javax.swing.JLabel;
import javax.swing.JPanel;

import outils.NavireCaracteristique;
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
public class PanelJoueur extends JPanel implements Serializable {

	private static final long serialVersionUID = 1L;

	private PanelPlateau jpp_panelPlateau;

	private JLabel jl_detailsJoueur;
	private JLabel jl_messageDivers;

	private String nomJoueur;
	private int score;

	/**
	 * Constructeur
	 * 
	 * @param nomJoueur
	 *            {@link String}
	 * @param plateau
	 *            {@link Plateau}
	 */
	public PanelJoueur(String nomJoueur, Plateau plateau) {
		this.nomJoueur = nomJoueur;

		jpp_panelPlateau = new PanelPlateau(plateau, plateau.getLongueur());

		this.score = this.getPanelPlateau().getPlateau().getScore();
		jl_detailsJoueur = new JLabel(this.nomJoueur
				+ ", vos vies restantes : " + this.score + " /"
				+ NavireCaracteristique.getScoreTotal(), JLabel.CENTER);
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

	/**
	 * Permet d'actualiser le panel joueur, ainsi que tous ses composants.
	 */
	public void actualisation() {
		this.getPanelPlateau().actualisation();
		String message = this.nomJoueur + ", vos vies restantes : "
				+ getScore() + " /" + NavireCaracteristique.getScoreTotal();
		jl_detailsJoueur.setText(message);
		this.repaint();
	}

	/**
	 * Permet de sauvegarder la partie. <br>
	 * Est uniquement appelé par {@link ListenerSauvegarder}
	 * 
	 * @param oos
	 *            {@link ObjectOutputStream}
	 */
	public void save(ObjectOutputStream oos) {
		try {
			oos.writeObject(this);
		} catch (IOException e) {
		}

	}

	/**
	 * Permet de définir le {@link PanelPlateau}
	 * 
	 * @param panelPlateau
	 *            {@link PanelPlateau}
	 */
	public void setPanelPanelPlateau(PanelPlateau panelPlateau) {
		this.jpp_panelPlateau = panelPlateau;

	}
}
