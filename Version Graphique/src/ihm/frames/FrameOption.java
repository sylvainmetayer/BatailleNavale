/**
 * 
 */
package ihm.frames;

import javax.swing.JFrame;

import metier.Jeu;
import outils.Options;
import ihm.panels.PanelOption;
import ihm.panels.PanelPrincipal;

/**
 * Cette {@link JFrame} représente une fenetre contenant les options de
 * {@link Jeu}. <br>
 * 
 * @author Sylvain METAYER - Kevin DESSIMOULIE
 * @see PanelOption pour les détails du panel
 */
public class FrameOption extends JFrame {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructeur qui instancie une nouvelle fenetre et ajoute le panel
	 * {@link PanelPrincipal}
	 * 
	 * @param jpp
	 *            {@link PanelPrincipal}
	 */
	public FrameOption(PanelPrincipal jpp) {
		this.setTitle("Options de jeu");
		this.setSize(Options.getLargeurFenetreOptions(),
				Options.getHauteurFenetreOptions());
		this.setLocationRelativeTo(null);
		this.setContentPane(new PanelOption(jpp));
		this.setDefaultCloseOperation(HIDE_ON_CLOSE);

		this.setResizable(false);

		this.setVisible(true);
	}
}
