package ihm;

import ihm.panels.PanelPrincipal;

import javax.swing.JFrame;

/**
 * @author Sylvain METAYER - Kevin DESSIMOULIE
 *
 */
public class FrameBatailleNavale extends JFrame {

	private static final int LONGUEUR = 900;
	private static final int LARGEUR = 600;
	private static final long serialVersionUID = 1L;

	public FrameBatailleNavale() {
		this.setTitle("Bataille Navale");
		// this.setAlwaysOnTop(isAlwaysOnTopSupported());
		// ne pas mettre pour le moment, car gène les popups
		this.setSize(LONGUEUR, LARGEUR);
		// this.setResizable(false);
		// tant qu'il y a des problèmes de repaint, ne pas mettre
		this.setLocationRelativeTo(null);

		// ajout du panel
		this.setContentPane(new PanelPrincipal());
		//this.setContentPane(new PanelBatailleNavale());

		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		// TODO demander confirmation
		this.setVisible(true);
		this.getContentPane().validate();
		this.getContentPane().repaint();
	}
}
