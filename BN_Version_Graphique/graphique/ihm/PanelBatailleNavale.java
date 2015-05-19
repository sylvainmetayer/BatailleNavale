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

	// = this pour plus de facilité d'accès vis a vis des classe interne membres
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
		JOptionPane.showMessageDialog(this, "La partie commence !");

		super.revalidate();
		super.repaint();
	}

	/**
	 * Cette méthode permet d'initialiser le plateau en assignant a chaque
	 * bouton une case
	 */
	private void initPlateau() {
		JPanel jp_plateau1;
		JPanel jp_plateau2;
		JLabel jl_infos;
		JLabel jl_plateauMoi;
		JLabel jl_plateauAdverse;

		JPanel jp_plateauxEtScore;
		JPanel jp_haut;

		jl_infos = new JLabel(jeu.getPlateauJoueurUn().getJoueur()
				+ " votre score est : " + jeu.getScore());

		jl_plateauAdverse = new JLabel("Plateau ennemi");
		jl_plateauMoi = new JLabel("Plateau de "
				+ jeu.getPlateauJoueurUn().getJoueur(), JLabel.LEFT);

		jp_plateauxEtScore = new JPanel();
		jp_haut = new JPanel();
		jp_plateauxEtScore.setLayout(new BorderLayout());

		jp_plateau1 = new JPanel();
		jp_plateau2 = new JPanel();
		jp_plateau1.setLayout(new GridLayout(TAILLEGRILLE, TAILLEGRILLE));
		jp_plateau2.setLayout(new GridLayout(TAILLEGRILLE, TAILLEGRILLE));

		genererPlateau(jp_plateau1);

		genererPlateau(jp_plateau2);

		jp_haut.add(jl_plateauMoi);
		jp_haut.add(jl_plateauAdverse);
		jp_plateauxEtScore.add(jp_plateau1, BorderLayout.EAST);
		jp_plateauxEtScore.add(jp_plateau2, BorderLayout.WEST);
		jp_plateauxEtScore.add(jl_infos, BorderLayout.CENTER);

		PanelPrincipal.setLayout(new BorderLayout());

		PanelPrincipal.add(jp_haut, BorderLayout.NORTH);
		PanelPrincipal.add(jp_plateauxEtScore, BorderLayout.CENTER);

	}

	private void genererPlateau(JPanel jp) {
		JButton jb_test;
		for (int i = 0; i < TAILLEGRILLE; i++) {
			for (int j = 0; j < TAILLEGRILLE; j++) {
				jb_test = new BoutonBN(
						jeu.getPlateauJoueurUn().getLstCases()[i][j],
						jeu.getPlateauJoueurUn().getLstCases()[i][j].getMotif());
				jb_test.addActionListener(new BoutonClique());
				jp.add(jb_test);
			}
		}
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
				nomJoueur = "joueur";
			}

			if (result == JOptionPane.CLOSED_OPTION) {
				// le nom du joueur n'a pas été renseigné, nom par defaut
				nomJoueur = "Joueur";
			}
			/* Fin demande prenom */

			StartGame();
			// on démarre la partie.
			repaint();
		}
	}
}
