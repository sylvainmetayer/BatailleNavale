/**
 * 
 */
package ihm.panels;

import ihm.composants.JTextAreaBN;
import ihm.panels.listeners.AideListener;
import ihm.panels.listeners.ChargerPartieListener;
import ihm.panels.listeners.LancerPartieListener;
import ihm.panels.listeners.ListenerPlacementBateaux;
import ihm.panels.listeners.ListenerTirer;
import ihm.panels.listeners.SauvegarderPartieListener;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.io.Serializable;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import outils.NavireCaracteristique;
import outils.Options;
import metier.CoupException;
import metier.Jeu;
import metier.Navire;
import metier.Plateau;

/**
 * Cette classe étend un {@link JPanel} et représente le jeu. Il s'agit du panel
 * principal qui appelle et interagit avec les autres panels
 * 
 * @author Sylvain - Kevin
 *
 */
public class PanelPrincipal extends JPanel implements Serializable {

	private static final long serialVersionUID = 1L;

	private int tailleGrille;

	// Paramètres du jeu
	private Jeu jeu;
	private String nomJoueurUn;
	private String nomJoueurDeux;

	// = this pour plus de facilité d'accès vis a vis des classe interne membres
	private final JPanel panelPrincipal = this;

	// Déclarations composants
	private JButton jb_commencerPartie;
	private JButton jb_chargerPartie;
	private JLabel jl_menuPrincipal;
	private JButton jb_sauvegarderPartie;
	private JButton jb_aide;

	private JLabel jl_image;
	ImageIcon image = new ImageIcon(this.getClass().getResource(
			Options.getPrefixeDossierImage() + "bataillenavale.jpg"));

	// pour pouvoir éditer le contenu à partir de tous les panels
	public static JTextAreaBN jta_message;
	private JScrollPane scroller;

	// Déclarations Panels
	private JPanel jp_image;
	private JPanel jp_panelCentre;
	private JPanel jp_aide_save;
	private PanelJoueur joueur1;
	private PanelJoueur joueur2;

	/**
	 * Constructeur, qui va afficher le menu et permettre de charger,sauvegarder
	 * ou lancer une partie.
	 */
	public PanelPrincipal() {

		// instanciations composants
		jl_menuPrincipal = new JLabel("Bataille Navale - Le jeu !");
		jl_menuPrincipal.setHorizontalAlignment(JLabel.CENTER);
		jb_commencerPartie = new JButton("Commencer une nouvelle partie");
		jb_sauvegarderPartie = new JButton("Sauvegarder ?");
		jb_sauvegarderPartie.setForeground(Color.RED);
		jb_aide = new JButton("Aide");
		jb_aide.addActionListener(new AideListener());
		jb_aide.setForeground(Color.BLUE);
		jb_chargerPartie = new JButton("Charger une partie");

		jl_image = new JLabel("");
		jl_image.setIcon(image);

		// instanciations panels
		panelPrincipal.setLayout(new BorderLayout());
		jp_panelCentre = new JPanel();
		jp_panelCentre.setLayout(new GridLayout(1, 2));
		jp_image = new JPanel();
		jp_image.setLayout(new FlowLayout());
		jp_image.setPreferredSize(new Dimension(200, 250));
		jp_aide_save = new JPanel();
		jp_aide_save.setLayout(new FlowLayout());

		// ajouts des composants dans les panels secondaires.
		jp_panelCentre.add(jb_commencerPartie, 0);
		jp_panelCentre.add(jb_chargerPartie, 1);
		jp_image.add(jl_image);

		// ajouts des composants dans le panel principal
		panelPrincipal.add(jl_menuPrincipal, BorderLayout.NORTH);
		panelPrincipal.add(jp_panelCentre, BorderLayout.CENTER);
		panelPrincipal.add(jp_image, BorderLayout.SOUTH);

		// ajouts des ecouteurs.
		// jb_quitterPartie.addActionListener(new QuitterListener());
		jb_commencerPartie.addActionListener(new LancerPartieListener(this));
		jb_chargerPartie.addActionListener(new ChargerPartieListener(this));
		jb_sauvegarderPartie.addActionListener(new SauvegarderPartieListener(
				jta_message, joueur2, joueur1));
	}

	/**
	 * Méthode qui lance une nouvelle partie. <br>
	 * Pour cela, on va commencer par creer un jeu, ainsi que deux panels pour
	 * les deux joueurs. <br>
	 * Ensuite, on effectue le placement des bateaux.
	 */
	public void initGame() {
		try {
			tailleGrille = Options.getTailleGrilleJeu();
			jeu = new Jeu(tailleGrille, tailleGrille, nomJoueurUn,
					nomJoueurDeux);
			// On crée les deux panels de jeu.
			joueur1 = new PanelJoueur(nomJoueurUn, jeu,
					jeu.getPlateauJoueurUn());
			joueur2 = new PanelJoueur(nomJoueurDeux, jeu,
					jeu.getPlateauJoueurDeux());
			repaint();
		} catch (CoupException e) {
			e.printStackTrace();
		}

		PanelPrincipal.jta_message = new JTextAreaBN();
		PanelPrincipal.jta_message.setEditable(false);
		PanelPrincipal.jta_message.setLineWrap(true);
		PanelPrincipal.jta_message.setWrapStyleWord(true);

		PanelPrincipal.jta_message.append("Historique :");
		PanelPrincipal.jta_message.append("Début du jeu :\n" + getNomJoueurUn()
				+ " contre " + getNomJoueurDeux() + " sur une grille de "
				+ Options.getTailleGrilleJeu() + "*"
				+ Options.getTailleGrilleJeu());

		// scrollbar pour le jtextarea
		scroller = new JScrollPane(jta_message);

		panelPrincipal.setLayout(new BorderLayout());

		jp_aide_save.add(jb_sauvegarderPartie);
		jp_aide_save.add(jb_aide);
		panelPrincipal.add(jp_aide_save, BorderLayout.NORTH);
		panelPrincipal.add(joueur1, BorderLayout.WEST);
		panelPrincipal.add(joueur2, BorderLayout.EAST);

		panelPrincipal.add(scroller, BorderLayout.CENTER);

		super.revalidate();
		super.repaint();
		placementBateaux(joueur1, NavireCaracteristique.TORPILLEUR, false);
	}

	/**
	 * Méthode pour placer les bateaux des deux joueurs. Une fois les bateaux
	 * placés, cette méthode appelle {@link PanelPrincipal#debutPartie()} pour
	 * commencer le jeu
	 * 
	 * @param jpj_joueur
	 *            {@link PanelJoueur}
	 * @param navireDetails
	 *            {@link NavireCaracteristique}
	 * @param finPlacement
	 *            {@link Boolean}
	 */
	public void placementBateaux(PanelJoueur jpj_joueur,
			NavireCaracteristique navireDetails, boolean finPlacement) {
		String message;

		repaint();
		if (!finPlacement) {

			// blocage grille adverse
			getMonPanelJoueur(jpj_joueur, false).setEtatGrille(false);
			getMonPanelJoueur(jpj_joueur, false).setMessage(
					"Inactif..".toUpperCase());

			// déblocage ma grille
			getMonPanelJoueur(jpj_joueur, true).setEtatGrille(true);
			getMonPanelJoueur(jpj_joueur, true).setMessage(
					"Placement des bateaux en cours..".toUpperCase());

			PanelPrincipal.jta_message.append("Placement du "
					+ navireDetails.getNom());

			jpj_joueur.setPlateauListener(new ListenerPlacementBateaux(
					navireDetails, this, jpj_joueur));

			repaint();

		} else {
			// Fin de l'ajout des boutons pour un joueur, on place donc les
			// bateaux de l'autre
			message = jpj_joueur.getNomJoueur()
					+ " a fini de placer ses bateaux.";
			jta_message.append(message.toUpperCase());
			jpj_joueur.getPanelPlateau().masquerPlateau();

			message = getMonPanelJoueur(jpj_joueur, false).getNomJoueur()
					+ ", c'est à toi !";
			jta_message.append(message.toUpperCase());
			placementBateaux(getMonPanelJoueur(jpj_joueur, false),
					NavireCaracteristique.TORPILLEUR, false);
		}
		repaint();
	}

	/**
	 * Début de la partie une fois les bateaux des deux joueurs placés. Va
	 * permettre le tir et le changement de joueur jusqu'à ce que l'un deux
	 * gagne.
	 */
	public void debutPartie() {
		repaint();
		jta_message.clearJTextArea();
		jta_message.append("Fin de placement des bateaux...\n "
				+ "Début de la guerre !");

		jouerCoup(getPanelJoueurDeux(), getPanelJoueurUn());

	}

	/**
	 * Cette méthode permet de jouer un coup sur le plateau adverse. <br>
	 * Le plateau adverse ne contient que les coup déjà joués, les autres étant
	 * masqués, et la plateau du joueur est viisble
	 * 
	 * @param joueurSousAttaque
	 *            {@link PanelJoueur}
	 * @param joueurAttaquant
	 *            {@link PanelJoueur}
	 */
	public void jouerCoup(PanelJoueur joueurSousAttaque,
			PanelJoueur joueurAttaquant) {

		PanelJoueur gagnant;

		joueurAttaquant.setMessage("A toi de jouer !");
		joueurAttaquant.setEtatGrille(false);

		joueurSousAttaque.setMessage("Prions pour un coup dans l'eau...");
		joueurSousAttaque.setEtatGrille(true);

		// TODO gestion plateau masqué unmask
		joueurAttaquant.getPanelPlateau().unmaskPlateau();
		joueurSousAttaque.getPanelPlateau().masquerPlateau();

		joueurSousAttaque.setPlateauListener(new ListenerTirer(this,
				joueurSousAttaque));

		gagnant = tellMeWhoWin(joueurAttaquant, joueurSousAttaque);

		if (gagnant == joueurAttaquant)
			finDePartie(joueurSousAttaque, joueurAttaquant);

		if (gagnant == joueurSousAttaque)
			finDePartie(joueurAttaquant, joueurSousAttaque);
		repaint();
	}

	/**
	 * Cette méthode retourne le gagnant du jeu, ou <code>null</code> si aucun
	 * n'a pour l'instant gagné.
	 * 
	 * @param jpj1
	 *            {@link PanelJoueur}
	 * @param jpj2
	 *            {@link PanelJoueur}
	 * @return {@link PanelJoueur} || <code>null</code>
	 */
	private PanelJoueur tellMeWhoWin(PanelJoueur jpj1, PanelJoueur jpj2) {

		boolean jpp1Lose, jpp2Lose;

		jpp1Lose = isAllNavireCoule(jpj1.getPanelPlateau().getPlateau());

		if (jpp1Lose)
			return jpj1;

		jpp2Lose = isAllNavireCoule(jpj2.getPanelPlateau().getPlateau());

		if (jpp2Lose)
			return jpj2;

		// la partie n'est pas encore finie
		return null;
	}

	/**
	 * Indique si tous les bateaux d'un plateau sont coulés
	 * 
	 * @param p
	 *            {@link Plateau}
	 * @return <code>true</code> || <code>false</code>
	 */
	private boolean isAllNavireCoule(Plateau p) {
		int nombreCoules = 0, nombreNavireMax;
		nombreNavireMax = NavireCaracteristique.values().length;

		for (Navire n : p.getListeNav()) {
			for (Navire nTouches : p.sontCoules()) {
				if (nTouches.equals(n))
					nombreCoules++;
			}
		}

		if (nombreCoules >= nombreNavireMax)
			return true;
		else
			return false;
	}

	/**
	 * Permet d'annoncer la fin de partie, ainisi que le nom du vainqueur.
	 * 
	 * @param gagnant
	 *            {@link PanelJoueur}
	 * @param perdant
	 *            {@link PanelJoueur}
	 */
	private void finDePartie(PanelJoueur gagnant, PanelJoueur perdant) {
		String message;
		boolean nouvellePartie;

		gagnant.setPlateauListener(null);
		perdant.setPlateauListener(null);
		gagnant.getPanelPlateau().setEnabled(false);
		perdant.getPanelPlateau().setEnabled(false);

		message = "Bravo " + gagnant.getNomJoueur() + ", tu as gagné !\n "
				+ perdant.getNomJoueur() + ", désolé, tu as perdu..";
		PanelPrincipal.jta_message.append(message);
		PanelPrincipal.jta_message.append("Fin de la partie..".toUpperCase());

		JOptionPane.showMessageDialog(null, message);
		nouvellePartie = questionOuiNon("Voulez vous rejouer ?",
				"La partie est finie..");
		if (nouvellePartie)
			initGame();
		else
			System.exit(0);

		// TODO gestion pour rejouer ne marche pas pour rejouer, freeze

	}

	/**
	 * Cette méthode permet de poser une question à l'utilisateur au travers
	 * d'une {@link JOptionPane}<br>
	 * Les possibilités de réponses sont oui ou non
	 * 
	 * @param message
	 *            {@link String}
	 * @param titreFenetre
	 *            {@link String}
	 * @return <code>true</code> réponse positive || <code>false</code> réponse
	 *         négative ou pas de réponse
	 */
	private boolean questionOuiNon(String message, String titreFenetre) {
		int resultat = JOptionPane.showConfirmDialog(null, message,
				titreFenetre, JOptionPane.YES_NO_OPTION);

		if (resultat == JOptionPane.YES_OPTION)
			return true;
		else
			return false;
	}

	/**
	 * Permet de recupérer n'importe quel panel de joueur. <br>
	 * Dans le cas ou monPanel est vrai, on retourne le panelJoueur du joueur
	 * passé en paramètre. <br>
	 * Sinon, on retourne celui du joueur adverse
	 * 
	 * @param jp
	 *            {@link PanelJoueur}
	 * @param monPanel
	 *            {@link Boolean}
	 * @return {@link PanelJoueur}
	 */
	public PanelJoueur getMonPanelJoueur(PanelJoueur jp, boolean monPanel) {
		if (jp.equals(joueur1))
			if (monPanel)
				return joueur1;
			else
				return joueur2;
		if (jp.equals(joueur2))
			if (monPanel)
				return joueur2;
			else
				return joueur1;
		return null;
	}

	/**
	 * Retourne le panel du joueur 1
	 * 
	 * @return {@link PanelJoueur}
	 */
	public PanelJoueur getPanelJoueurUn() {
		return joueur1;
	}

	/**
	 * Retourne le panel du joueur 2
	 * 
	 * @return {@link PanelJoueur}
	 */
	public PanelJoueur getPanelJoueurDeux() {
		return joueur2;
	}

	/**
	 * Méthode pour charger une partie à partir de ce qu'aura récupéré
	 * l'écouteur
	 * 
	 * @param jta_message
	 * @param joueur2
	 * @param joueur1
	 */
	public void chargerPartie(JTextAreaBN jta_message, PanelJoueur joueur2,
			PanelJoueur joueur1) {
		// TODO
		this.joueur1 = joueur1;
		this.joueur2 = joueur2;
		PanelPrincipal.jta_message = jta_message;

	}

	public String getNomJoueurUn() {
		return nomJoueurUn;
	}

	public void setNomJoueurUn(String nomJoueurUn) {
		this.nomJoueurUn = nomJoueurUn;
	}

	public String getNomJoueurDeux() {
		return nomJoueurDeux;
	}

	public void setNomJoueurDeux(String nomJoueurDeux) {
		this.nomJoueurDeux = nomJoueurDeux;
	}
}
