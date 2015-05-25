/**
 * 
 */
package ihm.frames;

import ihm.panels.PanelAide;
import ihm.panels.PanelPrincipal;

import javax.swing.JFrame;

/**
 * Cette classe représente une fenetre contenant une aide du jeu. <br>
 * Elle contient le panel {@link PanelAide}
 * 
 * @author Sylvain METAYER - Kevin DESSIMOULIE
 * @see PanelAide
 */
public class FrameAideBatailleNavale extends JFrame {

	private static final int LONGUEUR = 1200;
	private static final int LARGEUR = 600;
	private static final long serialVersionUID = 1L;

	/**
	 * Constructeur qui instancie une nouvelle fenetre et ajoute le panel
	 * {@link PanelPrincipal}
	 */
	public FrameAideBatailleNavale() {
		this.setTitle("Aide du jeu de bataille navale");
		this.setSize(LONGUEUR, LARGEUR);
		this.setLocationRelativeTo(null);
		this.setContentPane(new PanelAide());
		this.setDefaultCloseOperation(HIDE_ON_CLOSE);

		this.setResizable(false);

		this.setVisible(true);
	}
}
