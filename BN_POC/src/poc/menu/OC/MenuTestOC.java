package poc.menu.OC;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;

public class MenuTestOC extends JFrame {
	private JMenuBar menuBar = new JMenuBar();
	private JMenu contenant = new JMenu("Menu");

	private JMenuItem newGame = new JMenuItem("Nouvelle partie");
	private JMenuItem exitGame = new JMenuItem("Quitter");
	private JMenuItem loadGame = new JMenuItem("Charger partie");
	private JMenuItem saveGame = new JMenuItem("Sauvegarder partie");
	private JMenuItem helpGame = new JMenuItem("Aide");

	public static void main(String[] args) {
		new MenuTestOC();
	}

	public MenuTestOC() {
		this.setSize(400, 200);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);

		// On initialise nos menus
		this.contenant.add(newGame);
		this.contenant.add(loadGame);
		this.contenant.add(saveGame);
		this.contenant.addSeparator();
		this.contenant.add(helpGame);
		this.contenant.addSeparator();
		this.contenant.add(exitGame);

		// ajout listener
		exitGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});

		// L'ordre d'ajout va déterminer l'ordre d'apparition dans le menu de
		// gauche à droite
		// Le premier ajouté sera tout à gauche de la barre de menu et
		// inversement pour le dernier
		this.menuBar.add(contenant);
		this.setJMenuBar(menuBar);
		this.setVisible(true);
	}
}
