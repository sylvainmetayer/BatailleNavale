package ihm;

import ihm.panels.PanelPrincipal;

import javax.swing.JFrame;

/**
 * Cette classe est la fenetre de l'application
 * 
 * @author Sylvain METAYER - Kevin DESSIMOULIE
 *
 */
public class FrameBatailleNavale extends JFrame {

	private static final int LONGUEUR = 1200;
	private static final int LARGEUR = 500;
	private static final long serialVersionUID = 1L;

	/**
	 * Constructeur qui instancie une nouvelle fenetre et ajoute le panel
	 * {@link PanelPrincipal}
	 */
	public FrameBatailleNavale() {
		this.setTitle("Jeu de la bataille navale");
		this.setSize(LONGUEUR, LARGEUR);
		this.setLocationRelativeTo(null);
		this.setContentPane(new PanelPrincipal());
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		this.setResizable(false);
		// tant qu'il y a des problèmes de repaint, ne pas mettre

		// TODO demander confirmation avant sortie
		this.setVisible(true);
	}
}
