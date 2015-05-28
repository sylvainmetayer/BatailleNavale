package ihm.frames;

import ihm.panels.PanelPrincipal;
import ihm.panels.listeners.ListenerAide;
import ihm.panels.listeners.ListenerCharger;
import ihm.panels.listeners.ListenerLancerPartie;
import ihm.panels.listeners.ListenerSauvegarder;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import outils.Options;

import com.sun.glass.events.KeyEvent;

/**
 * Cette classe est la fenetre de l'application
 * 
 * @author Sylvain METAYER - Kevin DESSIMOULIE
 *
 */
public class FrameBatailleNavale extends JFrame {

	private JMenuBar menuBar = new JMenuBar();
	private JMenuItem helpGame = new JMenuItem("Aide");

	private JMenuItem newGame = new JMenuItem("Nouvelle partie");
	private JMenuItem exitGame = new JMenuItem("Quitter");
	private JMenuItem loadGame = new JMenuItem("Charger partie");
	private static JMenuItem saveGame = new JMenuItem("Sauvegarder partie");

	private static final long serialVersionUID = 1L;

	private PanelPrincipal jpp;

	/**
	 * Constructeur qui instancie une nouvelle fenetre et ajoute le panel
	 * {@link PanelPrincipal}
	 */
	public FrameBatailleNavale() {

		jpp = new PanelPrincipal();

		this.setTitle("Jeu de la bataille navale");
		this.setSize(Options.getLargeurFenetrePrincipale(),
				Options.getHauteurFenetrePrincipale());
		this.setLocationRelativeTo(null);
		this.setContentPane(jpp);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		int tmp;
		setSaveOn(false);
		this.menuBar.add(helpGame);
		this.menuBar.add(newGame);
		this.menuBar.add(saveGame);
		this.menuBar.add(loadGame);
		this.menuBar.add(exitGame);

		tmp = KeyEvent.VK_F1;
		helpGame.setMnemonic(tmp);
		helpGame.setAccelerator(KeyStroke.getKeyStroke(tmp,
				ActionEvent.CTRL_MASK));
		helpGame.setForeground(Color.RED);

		tmp = KeyEvent.VK_F2;
		newGame.setMnemonic(tmp);
		newGame.setAccelerator(KeyStroke
				.getKeyStroke(tmp, ActionEvent.CTRL_MASK));
		newGame.setForeground(Color.BLUE);

		tmp = KeyEvent.VK_F3;
		saveGame.setMnemonic(tmp);
		saveGame.setAccelerator(KeyStroke.getKeyStroke(tmp,
				ActionEvent.CTRL_MASK));
		saveGame.setForeground(Color.BLUE);

		tmp = KeyEvent.VK_F4;
		loadGame.setMnemonic(tmp);
		loadGame.setAccelerator(KeyStroke.getKeyStroke(tmp,
				ActionEvent.CTRL_MASK));
		loadGame.setForeground(Color.MAGENTA);

		tmp = KeyEvent.VK_F5;
		exitGame.setMnemonic(tmp);
		exitGame.setAccelerator(KeyStroke.getKeyStroke(tmp,
				ActionEvent.CTRL_MASK));
		exitGame.setForeground(Color.BLACK);

		this.setJMenuBar(menuBar);

		newGame.addActionListener(new ListenerLancerPartie(jpp));
		saveGame.addActionListener(new ListenerSauvegarder(jpp));
		helpGame.addActionListener(new ListenerAide());
		loadGame.addActionListener(new ListenerCharger(jpp));
		exitGame.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				boolean exitConfirm;
				exitConfirm = Options
						.questionOuiNon(
								"Voulez vous vraiment quitter ?\nAvez vous pensé à sauvegarder une éventuelle partie en cours ?",
								"Vous allez partir...");

				if (exitConfirm)
					System.exit(0);
			}
		});

		this.setResizable(false);
		this.setVisible(true);
	}

	/**
	 * Permet d'activer ou non le bouton sauvegarde
	 * 
	 * @param enabled
	 *            {@link Boolean}
	 */
	public static void setSaveOn(boolean enabled) {
		saveGame.setEnabled(enabled);
	}

}
