package ihm.frames;

import ihm.panels.PanelPrincipal;
import ihm.panels.listeners.ListenerAide;
import ihm.panels.listeners.ListenerCharger;
import ihm.panels.listeners.ListenerLancerPartie;
import ihm.panels.listeners.ListenerSauvegarder;

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

	private JMenuBar menuBar = new JMenuBar();
	private JMenu contenant = new JMenu("Menu");

	private JMenuItem newGame = new JMenuItem("Nouvelle partie");
	private JMenuItem exitGame = new JMenuItem("Quitter");
	private JMenuItem loadGame = new JMenuItem("Charger partie");
	private static JMenuItem saveGame = new JMenuItem("Sauvegarder partie");
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
		setSaveOn(false);
		this.contenant.addSeparator();
		this.contenant.add(helpGame);
		this.contenant.addSeparator();
		this.contenant.add(exitGame);
		this.menuBar.add(contenant);
		this.setJMenuBar(menuBar);

		newGame.addActionListener(new ListenerLancerPartie(jpp));
		saveGame.addActionListener(new ListenerSauvegarder(jpp));
		helpGame.addActionListener(new ListenerAide());
		loadGame.addActionListener(new ListenerCharger(jpp));

		this.setResizable(false);
		this.setVisible(true);
	}

	public static void setSaveOn(boolean enabled) {
		saveGame.setEnabled(enabled);
	}

}
