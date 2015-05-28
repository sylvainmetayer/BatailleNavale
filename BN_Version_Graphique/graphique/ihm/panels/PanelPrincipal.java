/**
 * 
 */
package ihm.panels;

import ihm.composants.JTextAreaBN;
import ihm.frames.FrameBatailleNavale;
import ihm.frames.FrameOption;
import ihm.panels.listeners.ListenerCharger;
import ihm.panels.listeners.ListenerLancerPartie;
import ihm.panels.listeners.ListenerPlacementBateaux;
import ihm.panels.listeners.ListenerTirer;

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

import metier.CoupException;
import metier.Jeu;
import metier.Navire;
import metier.Plateau;
import outils.NavireCaracteristique;
import outils.Options;

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

	private Jeu jeu;

	// Déclarations composants
	private JButton jb_commencerPartie;
	private JButton jb_chargerPartie;
	private JLabel jl_menuPrincipal;

	private JLabel jl_image;
	ImageIcon image;

	// static pour pouvoir éditer le contenu à partir de tous les panels
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

		jl_image = new JLabel("");

		try {
			image = new ImageIcon(this.getClass().getResource(
					Options.getPrefixeDossierImage() + "bataillenavale.jpg"));
			jl_image.setIcon(image);
		} catch (NullPointerException e) {
			jl_image.setText("Image actuellement indisponible..");
		}

		// instanciations composants
		jl_menuPrincipal = new JLabel("Bataille Navale - Le jeu !");
		jl_menuPrincipal.setHorizontalAlignment(JLabel.CENTER);
		jb_commencerPartie = new JButton("Commencer une nouvelle partie");
		jb_commencerPartie.setForeground(Color.BLUE);
		jb_commencerPartie.setBackground(Color.CYAN);
		jb_chargerPartie = new JButton("Charger une partie");
		jb_chargerPartie.setForeground(Color.MAGENTA);
		jb_chargerPartie.setBackground(Color.ORANGE);

		// instanciations panels
		this.setLayout(new BorderLayout());
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
		this.add(jl_menuPrincipal, BorderLayout.NORTH);
		this.add(jp_panelCentre, BorderLayout.CENTER);
		this.add(jp_image, BorderLayout.SOUTH);

		// ajouts des ecouteurs.
		jb_commencerPartie.addActionListener(new ListenerLancerPartie(this));
		jb_chargerPartie.addActionListener(new ListenerCharger(this));
	}

	/**
	 * Méthode qui lance une nouvelle partie. <br>
	 * Pour cela, on va commencer par creer un jeu, ainsi que deux panels pour
	 * les deux joueurs. <br>
	 * Ensuite, on effectue le placement des bateaux.
	 */
	public void initGame() {
		try {
			this.removeAll();
			this.revalidate();
			this.repaint();
			enableBackup(false);

			tailleGrille = Options.getTailleGrilleJeu();
			jeu = new Jeu(tailleGrille, tailleGrille, Options.getNomJoueurUn(),
					Options.getNomJoueurDeux());
			// On crée les deux panels de jeu.
			joueur1 = new PanelJoueur(Options.getNomJoueurUn(),
					jeu.getPlateauJoueurUn());
			joueur2 = new PanelJoueur(Options.getNomJoueurDeux(),
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
		PanelPrincipal.jta_message.append("Début du jeu :\n"
				+ Options.getNomJoueurUn() + " contre "
				+ Options.getNomJoueurDeux() + " sur une grille de "
				+ Options.getTailleGrilleJeu() + "*"
				+ Options.getTailleGrilleJeu());

		// scrollbar pour le jtextarea
		scroller = new JScrollPane(jta_message);

		this.setLayout(new BorderLayout());

		this.add(joueur1, BorderLayout.WEST);
		this.add(joueur2, BorderLayout.EAST);

		this.add(scroller, BorderLayout.CENTER);

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
		jta_message.append("Fin de placement des bateaux...\n "
				+ "Les choses sérieuses commencent !".toUpperCase());

		enableBackup(true);
		jouerCoup(getPanelJoueurDeux(), getPanelJoueurUn());

	}

	private void enableBackup(boolean b) {
		FrameBatailleNavale.setSaveOn(b);

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
		nouvellePartie = Options.questionOuiNon("Voulez vous rejouer ?",
				"La partie est finie..");
		System.out.println(nouvellePartie);
		if (nouvellePartie)
			new FrameOption(this);
		else
			System.exit(0);

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
	 * Permet de définir le {@link JPanel} du joueur 1
	 * 
	 * @param joueur1
	 *            {@link PanelJoueur}
	 */
	public void setPanelJoueur1(PanelJoueur joueur1) {
		this.joueur1 = joueur1;
	}

	/**
	 * Permet de définir le {@link JPanel} du joueur 2
	 * 
	 * @param joueur2
	 *            {@link PanelJoueur}
	 */
	public void setPanelJoueur2(PanelJoueur joueur2) {
		this.joueur2 = joueur2;
	}

	/**
	 * Permet de charger une partie à partir d'un fichier
	 * 
	 * @param paneljoueur1
	 *            {@link PanelJoueur}
	 * @param panelJoueur2
	 *            {@link PanelJoueur}
	 * @param jeu
	 *            {@link Jeu}
	 * @param texteJTA
	 *            {@link JTextAreaBN}
	 */
	public void chargerPartie(PanelJoueur paneljoueur1,
			PanelJoueur panelJoueur2, Jeu jeu, String texteJTA) {
		this.joueur1 = paneljoueur1;
		this.joueur2 = panelJoueur2;
		PanelPrincipal.jta_message = new JTextAreaBN();
		PanelPrincipal.jta_message.setText(texteJTA);
		this.jeu = jeu;
		initGameAfterCharger();

		int nombreCoupJoues;

		nombreCoupJoues = getNbCoupJoues(joueur1.getPanelPlateau().getPlateau());
		nombreCoupJoues = nombreCoupJoues
				+ getNbCoupJoues(joueur2.getPanelPlateau().getPlateau());

		if (nombreCoupJoues / 2 == 0) {
			// Tour joueur 1
			jouerCoup(this.joueur1, this.joueur2);
		} else {
			jouerCoup(this.joueur2, this.joueur1);
		}

	}

	/**
	 * Retourne le nombre de coup joués pour un {@link Plateau} donné.<br>
	 * Utile pour déterminer le tour du joueur après une sauvegarde
	 * 
	 * @param plateau
	 *            {@link Plateau}
	 * @return {@link Integer}
	 */
	private int getNbCoupJoues(Plateau plateau) {
		int somme = 0;
		for (int i = 0; i < plateau.getLargeur(); i++) {
			for (int j = 0; j < plateau.getLargeur(); j++) {
				if (plateau.getCoupsJoues()[i][j])
					somme++;
			}
		}
		return somme;
	}

	/**
	 * Permet d'initialiser le jeu après un chargement.
	 */
	private void initGameAfterCharger() {
		this.removeAll();
		this.revalidate();
		this.repaint();

		repaint();

		PanelPrincipal.jta_message.setEditable(false);
		PanelPrincipal.jta_message.setLineWrap(true);
		PanelPrincipal.jta_message.setWrapStyleWord(true);

		PanelPrincipal.jta_message.append("Reprise de la partie...");

		// scrollbar pour le jtextarea
		scroller = new JScrollPane(jta_message);

		this.setLayout(new BorderLayout());

		this.add(jp_aide_save, BorderLayout.NORTH);
		this.add(joueur1, BorderLayout.WEST);
		this.add(joueur2, BorderLayout.EAST);

		this.add(scroller, BorderLayout.CENTER);

		enableBackup(true);
		super.revalidate();
		super.repaint();

	}

	/**
	 * Retourne le {@link Jeu}
	 * 
	 * @return {@link Jeu}
	 */
	public Jeu getJeu() {
		return jeu;
	}
}
