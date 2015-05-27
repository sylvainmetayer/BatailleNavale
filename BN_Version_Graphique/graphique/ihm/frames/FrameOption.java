/**
 * 
 */
package ihm.frames;

import javax.swing.JFrame;

import ihm.panels.PanelOption;
import ihm.panels.PanelAide;
import ihm.panels.PanelPrincipal;

/**
 * @author Sylvain METAYER - Kevin DESSIMOULIE
 *
 */
public class FrameOption extends JFrame {

	private static final int LONGUEUR = 1200;
	private static final int LARGEUR = 600;
	private static final long serialVersionUID = 1L;

	/**
	 * Constructeur qui instancie une nouvelle fenetre et ajoute le panel
	 * {@link PanelPrincipal}
	 */
	public FrameOption(PanelPrincipal jpp) {
		this.setTitle("Options de jeu");
		this.setSize(LONGUEUR, LARGEUR);
		this.setLocationRelativeTo(null);
		this.setContentPane(new PanelOption(jpp));
		this.setDefaultCloseOperation(HIDE_ON_CLOSE);

		this.setResizable(false);

		this.setVisible(true);
	}

}
