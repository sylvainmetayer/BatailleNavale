package ihm.frames;

import ihm.panels.PanelPrincipal;
import ihm.panels.listeners.AideListener;
import ihm.panels.listeners.ChargerPartieListener;
import ihm.panels.listeners.LancerPartieListener;
import ihm.panels.listeners.SauvegarderPartieListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 * Cette classe est la fenetre de l'application
 * 
 * @author Sylvain METAYER - Kevin DESSIMOULIE
 *
 */
public class FrameBatailleNavale extends JFrame {

	// Menu
	private JMenuBar menuBar = new JMenuBar();
	private JMenu contenant = new JMenu("Menu");

	private JMenuItem newGame = new JMenuItem("Nouvelle partie");
	private JMenuItem exitGame = new JMenuItem("Quitter");
	private JMenuItem loadGame = new JMenuItem("Charger partie");
	private JMenuItem saveGame = new JMenuItem("Sauvegarder partie");
	private JMenuItem helpGame = new JMenuItem("Aide");

	private static final int LONGUEUR = 1200;
	private static final int LARGEUR = 500;
	private static final long serialVersionUID = 1L;

	private PanelPrincipal jpp;

	/**
	 * Constructeur qui instancie une nouvelle fenetre et ajoute le panel
	 * {@link PanelPrincipal}
	 */
	public FrameBatailleNavale() {

		jpp = new PanelPrincipal();
		this.setTitle("Jeu de la bataille navale");
		this.setSize(LONGUEUR, LARGEUR);
		this.setLocationRelativeTo(null);
		this.setContentPane(jpp);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		// On initialise nos menus
		this.contenant.add(newGame);
		this.contenant.add(loadGame);
		this.contenant.add(saveGame);
		this.contenant.addSeparator();
		this.contenant.add(helpGame);
		this.contenant.addSeparator();
		this.contenant.add(exitGame);
		this.menuBar.add(contenant);
		this.setJMenuBar(menuBar);

		newGame.addActionListener(new LancerPartieListener(jpp));
		saveGame.addActionListener(new SauvegarderPartieListener(
				PanelPrincipal.jta_message, jpp.getPanelJoueurDeux(), jpp
						.getPanelJoueurUn()));
		helpGame.addActionListener(new AideListener());
		loadGame.addActionListener(new ChargerPartieListener(jpp));

		this.setResizable(false);
		this.setVisible(true);
	}
}
