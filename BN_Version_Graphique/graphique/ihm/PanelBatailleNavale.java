/**
 * 
 */
package ihm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * @author Sylvain METAYER - Kevin DESSIMOULIE
 *
 */
public class PanelBatailleNavale extends JPanel {

	// axe x et y inversé à éventuellement régler quand TOUT sera fini. En
	// attendant, on continue comme ça.

	/*
	 * Ci dessous, des paramètres globaux, de taille de layout, de taille de
	 * grille, ...
	 */
	private final String DEFAULTNAME = "Joueur";
	private final int TAILLEGRILLE = 8;
	private final int ECARTLARGEURFLOWLAYOUT = 150;
	private final int ECARTHAUTEURFLOWLAYOUT = 5;

	// Paramètres du jeu
	Jeu jeu;
	String nomJoueur = DEFAULTNAME;

	// = this pour plus de facilité d'accès vis a vis des classe interne membres
	private final JPanel PanelPrincipal = this;

	// Déclarations composants
	JButton jb_commencerPartie;
	JButton jb_quitterPartie;
	JButton jb_chargerPartie;
	JLabel jl_menuPrincipal;

	JPanel jp_plateau1;
	JPanel jp_plateau2;
	JLabel jl_infos;
	JLabel jl_plateauMoi;
	JLabel jl_plateauAdverse;
	JTextArea jta_message;

	JPanel jp_messageHautDivers;
	JPanel jp_plateauxEtScore;
	JPanel jp_haut;

	// Déclarations Panels
	private JPanel jp_panelCentre;

	private static final long serialVersionUID = 1L;
	private HashMap<String, BoutonBN> mapBoutonJoueur;
	private HashMap<String, BoutonBN> mapBoutonOrdi;

	/**
	 * Constructeur, qui va afficher le menu et permettre de charger,sauvegarder
	 * ou lancer une partie.
	 */
	public PanelBatailleNavale() {

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
		jb_commencerPartie.addActionListener(new LancerPartieListener());

	}

	/**
	 * Méthode qui lance une nouvelle partie.
	 */
	private void StartGame() {
		try {
			jeu = new Jeu(TAILLEGRILLE, TAILLEGRILLE, nomJoueur);
			// les deux maps qui contiendront les boutons des deux grilles
			mapBoutonJoueur = new HashMap<String, BoutonBN>();
			mapBoutonOrdi = new HashMap<String, BoutonBN>();
		} catch (CoupException e) {
			e.printStackTrace();
		}

		initPlateau();
		// super.revalidate();
		// super.repaint();

		placementBateauxJoueur();

	}

	private void placementBateauxJoueur() {
		JOptionPane.showMessageDialog(PanelPrincipal,
				"Avant que la guerre ne commence, placez vos bateaux..");

		setEtatGrilleBoutons(mapBoutonOrdi, false);
		jta_message
				.setText("Cliquer sur une case de départ\npour placer le bateu de deux cases");

		// idée de généralisation ?
		// for (NavireSize n : NavireSize.values()) {
		// setListenerGrille(mapBoutonJoueur, new PlacementBateauListener(
		// n));
		// plus un timer entre ?
		// }

		setListenerGrille(mapBoutonJoueur, new PlacementBateauListener(
				NavireCaracteristique.NAVIRESIZE2));
		repaint();

	}

	public class PlacementBateauListener implements ActionListener {

		private NavireCaracteristique navDetails;

		public PlacementBateauListener(NavireCaracteristique navire) {
			this.navDetails = navire;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// Test pour vérifier
			// System.out.println((((BoutonBN)
			// e.getSource())).getCase().getPosx()
			// + " y=" + (((BoutonBN) e.getSource())).getCase().getPosy());

			List<Case> lstCase = new ArrayList<Case>();
			Case depart;
			int x, y;
			String motif;
			boolean estTouche;

			x = ((BoutonBN) e.getSource()).getCase().getPosx();
			y = ((BoutonBN) e.getSource()).getCase().getPosy();
			// on récupère les coordonnées de la case, mais on affecte le motif
			// du bateau que l'on veut ajouter
			motif = navDetails.getMotif();
			// bateau pas encore placé = bateau non touché
			estTouche = false;
			depart = new Case(x, y, estTouche, motif);

			lstCase.add(depart);

			String[] options = new String[] { "Horizontal", "Vertical" };
			int choixPositionnement = JOptionPane.showOptionDialog(null,
					"Choisir l'orientation :", "Précisions..",
					JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
					null, options, options[0]);

			if (choixPositionnement == 0) {
				// PLACEMENT HORIZONTAL
				for (int i = 0; i < navDetails.getTaille(); i++) {

					int tmp = y + 1;
					if (jeu.getPlateauJoueurUn().isCollisionPlacement(x, tmp)) {
						jta_message
								.setText("Erreur, collision.\nRecommencer svp.");
						repaint();
						System.out
								.println("Collision, le bateau n'a pas pu être ajouté.");
						// erreur = true;
						// TODO gestion erreur
					}
					lstCase.add(new Case(x, tmp, false,
							NavireCaracteristique.NAVIRESIZE2.getMotif()));

				}
				jeu.getPlateauJoueurUn().ajouterNavire(
						new Navire(1, 1, lstCase, false, 1 * 10));
				// Ctrl ajout dans grille
				System.out.println(jeu.getPlateauJoueurUn().getCasesOccupees());
				// actualisation grille
				System.out.println("J'ai CLIQUE LA"
						+ ((BoutonBN) e.getSource()).getCase().getPosx()
						+ ((BoutonBN) e.getSource()).getCase().getPosy());
				// L'erreur se situe la, il y a une différence de 1 sur un des
				// axes et du coup, on obtient un nullpointerException
				// A Corriger.
				// TODO FIX IT EN PRIORITE
				actualiserGrille(mapBoutonOrdi, jeu.getPlateauJoueurUn());
			} else if (choixPositionnement == 1) {
				// TODO PLACEMENT VERTICAL A GERER
			} else {
				// TODO ERREUR A GERER
			}

		}

	}

	// TODO
	// Une méthode qui afiche mes jbuton
	// une méthode qui recupère case plateau et les affecte au jbuton

	/**
	 * Permet d'actualiser une grille de jeu en fonction du plateau de jeu.
	 * 
	 * @param map
	 * @param plateau
	 */
	private void actualiserGrille(HashMap<String, BoutonBN> map, Plateau plateau) {
		Case[][] c = jeu.goodPlateau(plateau).getLstCases();
		BoutonBN before, after;
		String key;
		String motifCaseToUpdate;
		boolean ctrl;

		for (int i = 0; i < TAILLEGRILLE; i++) {
			for (int j = 0; j < TAILLEGRILLE; j++) {
				key = c[i][j].toString();
				before = map.get(key);
				// System.out.println(map.containsKey(key));
				motifCaseToUpdate = "Z";
				// plateau.getLstCases()[i][j].getMotif();
				after = map.get(key);

				System.out.println("LA CASE " + after);
				after.getCase().setMotif(motifCaseToUpdate);

				ctrl = map.replace(key, before, after);
				System.out.println("AJOUT OK ? " + ctrl);
				if (!ctrl) {
					// TODO gestion erreur
				}
			}
		}
		repaint();
		// super.revalidate();
		// super.repaint();
	}

	private void initPlateau() {

		jta_message = new JTextArea();
		jta_message.setForeground(Color.RED);
		// jta_message.setFont(new Font("ARIAL", 10, 10));
		jta_message.setEditable(false);
		jl_infos = new JLabel(jeu.getPlateauJoueurUn().getJoueur()
				+ ", votre score est : " + jeu.getScore());
		jl_infos.setHorizontalAlignment(JLabel.CENTER);
		jl_plateauAdverse = new JLabel("Plateau ennemi");
		jl_plateauMoi = new JLabel("Plateau de "
				+ jeu.getPlateauJoueurUn().getJoueur(), JLabel.LEFT);

		jp_plateauxEtScore = new JPanel();
		jp_haut = new JPanel();
		jp_haut.setLayout(new FlowLayout(FlowLayout.CENTER,
				ECARTLARGEURFLOWLAYOUT, ECARTHAUTEURFLOWLAYOUT));
		jp_messageHautDivers = new JPanel();

		jp_plateauxEtScore.setLayout(new BorderLayout());

		jp_plateau1 = new JPanel();
		jp_plateau2 = new JPanel();
		jp_plateau1.setLayout(new GridLayout(TAILLEGRILLE, TAILLEGRILLE));
		jp_plateau2.setLayout(new GridLayout(TAILLEGRILLE, TAILLEGRILLE));

		genererPlateau(jp_plateau1, mapBoutonJoueur, jeu.getPlateauJoueurUn());

		genererPlateau(jp_plateau2, mapBoutonOrdi, jeu.getPlateauJoueurDeux());

		jp_haut.add(jl_plateauMoi);
		jp_haut.add(jta_message);
		jp_haut.add(jl_plateauAdverse);
		jp_plateauxEtScore.add(jp_plateau2, BorderLayout.EAST);
		jp_plateauxEtScore.add(jp_plateau1, BorderLayout.WEST);
		jp_plateauxEtScore.add(jl_infos, BorderLayout.CENTER);
		jp_plateauxEtScore.add(jp_messageHautDivers, BorderLayout.NORTH);
		PanelPrincipal.setLayout(new BorderLayout());

		PanelPrincipal.add(jp_haut, BorderLayout.NORTH);
		PanelPrincipal.add(jp_plateauxEtScore, BorderLayout.CENTER);
		repaint();

	}

	private void genererPlateau(JPanel jp, HashMap<String, BoutonBN> bn,
			Plateau joueurActif) {
		BoutonBN jb_test;
		for (int i = 0; i < TAILLEGRILLE; i++) {
			for (int j = 0; j < TAILLEGRILLE; j++) {
				jb_test = new BoutonBN(jeu.goodPlateau(joueurActif)
						.getLstCases()[i][j], jeu.goodPlateau(joueurActif)
						.getLstCases()[i][j].getMotif());
				// jb_test.addActionListener(new BoutonClique());
				jp.add(jb_test);
				// on ajoute le bouton dans une map
				bn.put(jb_test.getCase().toString(), jb_test);
			}
		}
		actualiserGrille(bn, joueurActif);
		// TEST : this.setListenerGrille(bn, new PlacementBateauListener());
	}

	/**
	 * Permet de désactiver ou d'activer tous les boutons d'une grille.
	 * 
	 * @param mapBouton
	 *            {@link HashMap}
	 * @param cliquable
	 *            {@link Boolean}
	 */
	private void setEtatGrilleBoutons(HashMap<String, BoutonBN> mapBouton,
			boolean cliquable) {
		for (BoutonBN b : mapBouton.values()) {
			if (cliquable)
				b.setEnabled(true);
			else
				b.setEnabled(false);
		}
	}

	/**
	 * Permet de mettre un nouveau listener sur une grille de boutons, en
	 * retirant les anciens listener.
	 * 
	 * @param boutons
	 *            {@link HashMap}
	 * @param l
	 *            {@link ActionListener}
	 */
	private void setListenerGrille(HashMap<String, BoutonBN> boutons,
			ActionListener l) {

		for (BoutonBN b : boutons.values()) {
			// on lui retire tous ses listeners
			for (ActionListener action : b.getActionListeners()) {
				b.removeActionListener(action);
			}

			// on lui ajoute le nouveau
			b.addActionListener(l);

		}

	}

	/**
	 * Classe interne membre pour le lancement du jeu. <br>
	 * Ecouteur disponible sur le bouton
	 * {@link PanelBatailleNavale#jb_commencerPartie} <br>
	 * 
	 * @author Sylvain - Kevin
	 *
	 */
	public class LancerPartieListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// on efface tout ce qu'il y avait avant.
			PanelPrincipal.removeAll();
			// TOUJOURS faire un revalidate et un repaint apres un removeAll
			// pour eviter des surprises..
			PanelPrincipal.revalidate();
			PanelPrincipal.repaint();

			/* Demmande prenom */
			Object[] message = new Object[2];
			message[0] = "Votre prénom";
			message[1] = new JTextField();
			String option[] = { "OK" };

			int result = JOptionPane.showOptionDialog(null, message,
					"Renseignements", JOptionPane.OK_CANCEL_OPTION,
					JOptionPane.QUESTION_MESSAGE, null, option, message[1]);

			if (result == JOptionPane.OK_OPTION) {
				nomJoueur = ((JTextField) message[1]).getText();
			}

			if (result == JOptionPane.CANCEL_OPTION) {
				JOptionPane.showMessageDialog(PanelPrincipal,
						"Utilisation du nom par défaut...");
				nomJoueur = "AZEZEZRTE";
				// TODO prenom non saisi = crash
			}
			/* Fin demande prenom */

			// on démarre la partie.
			StartGame();
		}
	}

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
}
