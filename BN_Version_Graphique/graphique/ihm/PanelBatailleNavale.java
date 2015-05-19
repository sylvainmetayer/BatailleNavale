/**
 * 
 */
package ihm;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * @author Sylvain METAYER - Kevin DESSIMOULIE
 *
 */
public class PanelBatailleNavale extends JPanel {

	// Paramètres du jeu
	Jeu jeu;
	String nomJoueur;

	// = this pour plus de facilité d'accès
	private final JPanel PanelPrincipal = this;

	// Paramètres statiques
	private static String DEFAULTNAME = "Joueur";
	private static int TAILLEGRILLE = 8;

	// Déclarations Panels
	private JPanel jp_panelCentre;

	private static final long serialVersionUID = 1L;

	/**
	 * Constructeur, qui va afficher le menu et permettre de charger,sauvegarder
	 * ou lancer une partie.
	 */
	public PanelBatailleNavale() {
		// Déclarations
		JButton jb_commencerPartie;
		JButton jb_quitterPartie;
		JButton jb_chargerPartie;
		JLabel jl_menuPrincipal;

		// instanciations composants
		jl_menuPrincipal = new JLabel("Bataille Navale - Le jeu !");
		jl_menuPrincipal.setHorizontalAlignment(JLabel.CENTER);
		jb_commencerPartie = new JButton("Commencer une nouvelle partie");
		jb_quitterPartie = new JButton("Quitter");
		jb_chargerPartie = new JButton("Charger une partie");

		// instanciations panels
		PanelPrincipal.setLayout(new BorderLayout());
		jp_panelCentre = new JPanel();
		jp_panelCentre.setLayout(new GridLayout(2, 2));

		// ajouts des composants dans les panels secondaires.
		jp_panelCentre.add(jb_commencerPartie, 0);
		jp_panelCentre.add(jb_chargerPartie, 1);
		jp_panelCentre.add(jb_quitterPartie, 2);

		// ajouts des composants dans le panel principal
		PanelPrincipal.add(jl_menuPrincipal, BorderLayout.NORTH);
		PanelPrincipal.add(jp_panelCentre, BorderLayout.CENTER);

		// ajouts des ecouteurs.
		jb_quitterPartie.addActionListener(new QuitterListener());
		jb_commencerPartie.addActionListener(new LancerPartie());

	}

	/**
	 * Méthode qui lance une nouvelle partie.
	 */
	private void StartGame() {
		try {
			jeu = new Jeu(PanelBatailleNavale.TAILLEGRILLE,
					PanelBatailleNavale.TAILLEGRILLE, nomJoueur);
		} catch (CoupException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		initPlateau();

		// placementBateauxJoueur();

		// afficherPlateaux();

	}

	/**
	 * Cette méthode permet d'initialiser
	 */
	private void initPlateau() {
		JPanel jp_plateau1;
		JPanel jp_plateau2;
		JLabel jl_log;
		JPanel jp_centre;
		JButton jb_test;

		// TODO gérer placement
		jl_log = new JLabel("Bonjour " + jeu.getPlateauJoueurUn().getJoueur()
				+ " votre score");
		jp_centre = new JPanel();
		jp_centre.setLayout(new BorderLayout());

		jp_plateau1 = new JPanel();
		jp_plateau2 = new JPanel();
		jp_plateau1.setLayout(new GridLayout(TAILLEGRILLE, TAILLEGRILLE));
		jp_plateau2.setLayout(new GridLayout(TAILLEGRILLE, TAILLEGRILLE));

		// plateau1
		int k = 0;
		for (int i = 0; i < TAILLEGRILLE; i++) {
			for (int j = 0; j < TAILLEGRILLE; j++) {
				k = i;
				jb_test = new JButton(
						jeu.getPlateauJoueurUn().getLstCases()[k][j].getMotif()
				// Integer.toString(k + j)
				);
				jb_test.addActionListener(new BoutonClique());
				jp_plateau1.add(jb_test, k + j);
			}
			int somme = k + i;
		}

		// plateau2
		for (int i = 0; i < TAILLEGRILLE; i++) {
			for (int j = 0; j < TAILLEGRILLE; j++) {
				k = i;
				jb_test = new JButton(
						jeu.getPlateauJoueurDeux().getLstCases()[k][j]
								.getMotif()
				// Integer.toString(k + j)
				);
				jb_test.addActionListener(new BoutonClique());
				jp_plateau2.add(jb_test, k + j);
			}
		}

		jp_centre.add(jp_plateau1, BorderLayout.EAST);
		jp_centre.add(jp_plateau2, BorderLayout.WEST);
		jp_centre.add(jl_log, BorderLayout.CENTER);

		PanelPrincipal.setLayout(new BorderLayout());
		PanelPrincipal.add(jp_centre, BorderLayout.CENTER);
		repaint();

	}

	/**
	 * Cette méthode permet l'affichage des plateaux, et de placer ses bateaux
	 */
	private void placementBateauxJoueur() {
		// TODO afficherPlateaux();

	}

	/* CLASSE INTERNE */

	/**
	 * Classe interne membre permettant de quitter le jeu
	 * 
	 * @author Sylvain METAYER - Kevin DESSIMOULIE
	 *
	 */
	public class QuitterListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {

			int option = JOptionPane.showConfirmDialog(null,
					"Voulez-vous quitter ?", "Arrêt de l'application",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if (option != JOptionPane.NO_OPTION
					&& option != JOptionPane.CLOSED_OPTION) {
				PanelPrincipal.setVisible(false);
				System.exit(0);
			}
		}

	}

	public class BoutonClique implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub

		}

	}

	public class LancerPartie implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// on efface tout ce qu'il y avait avant.
			PanelPrincipal.removeAll();
			repaint();

			/* Demmande prenom */
			Object[] message = new Object[2];
			message[0] = "Votre prénom";
			message[1] = new JTextField();
			String option[] = { "OK" };

			int result = JOptionPane.showOptionDialog(null, message,
					"Renseignements", JOptionPane.DEFAULT_OPTION,
					JOptionPane.QUESTION_MESSAGE, null, option, message[1]);

			if (result == JOptionPane.OK_OPTION) {
				nomJoueur = ((JTextField) message[1]).getText();
			} else {
				// le nom du joueur n'a pas été renseigné, nom par defaut
				nomJoueur = DEFAULTNAME;
			}
			/* Fin demande prenom */
			StartGame();
			// on démarre la partie.
		}
	}
}
