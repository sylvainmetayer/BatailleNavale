package metier;

import java.util.ArrayList;
import java.util.List;

public class Jeu {

	private Plateau plateauJoueurUn;
	private Plateau plateauJoueurDeux;
	private int x;
	private int y;
	private int score;

	private int nbreCoups;

	public Plateau goodPlateau(Plateau p) {
		if (p.equals(plateauJoueurUn))
			return plateauJoueurUn;
		if (p.equals(plateauJoueurDeux))
			return plateauJoueurDeux;
		return null;
	}

	/**
	 * Constructeur permettant de creer un plateau de jeu et de donner un nom de
	 * joueur au joueur humain.<br>
	 * on peut noter que par defaut dans ce constructeur, on cree un plateau
	 * pour l'ordinateur
	 * 
	 * @param hauteur
	 * @param largeur
	 * @param nomJoueur
	 * @throws CoupException
	 *             quand TODO
	 */
	public Jeu(int hauteur, int largeur, String nomJoueur) throws CoupException {
		this.plateauJoueurUn = new Plateau(hauteur, largeur, nomJoueur);
		this.plateauJoueurDeux = new Plateau(hauteur, largeur, "Ordinateur");
		this.score = 0;
		this.nbreCoups = 0;

	}

	/**
	 * Permet de jouer un coup sur un plateau donne. Le joueur A fait un coup
	 * x,y....
	 * 
	 * @param x
	 * @param y
	 * @param plateau
	 * @return Navire : le navire touche ou coule, ou alors null si rien ne
	 *         s'est passe
	 * @throws CoupException
	 */
	public Navire jouer(int x, int y, Plateau plateau) throws CoupException {

		isCoupAutorise(x, y, plateau);
		Navire n = this.goodPlateau(plateau).jouerCoup(x, y);
		/*
		 * if (n == null) { System.out.println("Coup dans l'eau !"); if
		 * (plateau.equals(getPlateauJoueurUn())) { score += 0; score ordi à
		 * gérer ? } } else { if (n.isEstCoule() == true) {
		 * System.out.println("Touché Coulé !"); if
		 * (plateau.equals(getPlateauJoueurUn())) { // score += 2; // score ordi
		 * à gérer ? } if (plateau.equals(getPlateauJoueurDeux())) { int score =
		 * getScore() + n.getValeurScore(); setScore(score); } } else if
		 * (n.isEstCoule() == false) { System.out.println("Touché !"); if
		 * (plateau.equals(getPlateauJoueurUn())) { // score += 1; // score ordi
		 * à gérer ? } } }
		 */

		// la gestion des cases étant déjà faite avant (cf plateau#jouerCoup, on
		// peut juste retourner
		// le bateau tel quel.. ?
		return n;

	}

	/**
	 * Permet de verifier que les coordonnees du coup joue sont bien dans la
	 * taille de l'aire de jeu
	 * 
	 * @param x
	 * @param y
	 * @param plateau
	 * @throws CoupException
	 */
	private void isCoupAutorise(int x, int y, Plateau plateau)
			throws CoupException {

		int cx = this.goodPlateau(plateau).getLongueur();
		int cy = this.goodPlateau(plateau).getLargeur();
		if (x > cx || y > cy || x < 0 || y < 0) {
			throw new CoupException("coup non autorisé ");
		} else if (goodPlateau(plateau).getCoupsJoues()[x][y] == true) {
			throw new CoupException("coup non autorisé ");
		}

	}

	/**
	 * recupere la liste des bateaux coules d'un Plateau/Joueur
	 * 
	 * @param plateau
	 *            {@link Plateau}
	 * @return {@link List}
	 */
	List<Navire> sontCoules(Plateau plateau) {

		return this.goodPlateau(plateau).sontCoules();

	}

	/**
	 * recupere la liste des navires d'un plateau/joueur
	 * 
	 * @param plateau
	 *            {@link Plateau}
	 * @return {@link List}
	 */
	List<Navire> tousNavires(Plateau plateau) {
		return this.goodPlateau(plateau).getListeNav();
	}

	/**
	 * Permet de générer une partie en récupérant la liste des navires à partir
	 * d'un fichier.
	 * 
	 * @throws metier.bataille.application.BatailleNavaleException
	 * @throws NumberFormatException
	 * 
	 */
	public void genererJeu() throws NumberFormatException,
			BatailleNavaleException {
		int nbreNavires = Integer.parseInt(FileUtil.getInstance().getPropriete(
				"nbreNavires"));
		int nbreNavires1CASE = Integer.parseInt(FileUtil.getInstance()
				.getPropriete("nbreNavireSize1"));
		int nbreNavires2CASE = Integer.parseInt(FileUtil.getInstance()
				.getPropriete("nbreNavireSize2"));
		int nbreNavires3CASE = Integer.parseInt(FileUtil.getInstance()
				.getPropriete("nbreNavireSize3"));
		int nbreNavires4CASE = Integer.parseInt(FileUtil.getInstance()
				.getPropriete("nbreNavireSize4"));
		int nbreNavires5CASE = Integer.parseInt(FileUtil.getInstance()
				.getPropriete("nbreNavireSize5"));
		int cpt;
		int idNavire = 0;
		Navire n = null;
		List<Case> lst = null;
		List<Navire> listeNav = new ArrayList<Navire>();
		for (int i = 0; i < nbreNavires; i++) {
			for (cpt = 0; cpt < nbreNavires1CASE; cpt++) {
				idNavire++;
				n = new Navire(idNavire, 1, lst, false, 100);
				listeNav.add(n);
			}
			for (cpt = 0; cpt < nbreNavires2CASE; cpt++) {
				idNavire++;
				n = new Navire(idNavire, 2, lst, false, 200);
				listeNav.add(n);
			}
			for (cpt = 0; cpt < nbreNavires3CASE; cpt++) {
				idNavire++;
				n = new Navire(idNavire, 3, lst, false, 300);
				listeNav.add(n);
			}
			for (cpt = 0; cpt < nbreNavires4CASE; cpt++) {
				idNavire++;
				n = new Navire(idNavire, 4, lst, false, 400);
				listeNav.add(n);
			}
			for (cpt = 0; cpt < nbreNavires5CASE; cpt++) {
				idNavire++;
				n = new Navire(idNavire, 5, lst, false, 500);
				listeNav.add(n);
			}

		}

		// TODO quand on place en manuel, ne set que le plateau 2 !
		plateauJoueurUn.setListeNav(listeNav);
		plateauJoueurDeux.setListeNav(listeNav);

		// TODO definir nombre navires, type navire, taille navire
	}

	/**
	 * Retourne le plateau du joueur Un (l'humain).
	 * 
	 * @return plateauJoueurUn
	 * 
	 */
	public Plateau getPlateauJoueurUn() {
		return plateauJoueurUn;
	}

	/**
	 * Setter du plateau du joueur Un (l'humain).
	 * 
	 * @param plateauJoueurUn
	 */
	public void setPlateauJoueurUn(Plateau plateauJoueurUn) {
		this.plateauJoueurUn = plateauJoueurUn;
	}

	/**
	 * Retourne le plateau du joueur Deux (l'ordinateur).
	 * 
	 * @return plateauJoueurDeux
	 * 
	 */
	public Plateau getPlateauJoueurDeux() {
		return plateauJoueurDeux;
	}

	/**
	 * Setter du plateau du joueur Deux (l'ordinateur).
	 * 
	 * @param plateauJoueurDeux
	 */
	public void setPlateauJoueurDeux(Plateau plateauJoueurDeux) {
		this.plateauJoueurDeux = plateauJoueurDeux;
	}

	/**
	 * Retourne le score du joueur
	 * 
	 * @return score
	 * 
	 */
	public int getScore() {
		return score;
	}

	/**
	 * Setter du score du joueur
	 * 
	 * @param score
	 */
	public void setScore(int score) {
		this.score = score;
	}

	/**
	 * Retourne le nombre de coups d'un joueur durant la partie.
	 * 
	 * @return nbreCoups
	 * 
	 */
	public int getNbreCoups() {
		return nbreCoups;
	}

	/**
	 * Setter du nombre de coups d'un joueur durant la partie.
	 * 
	 * @param nbreCoups
	 */
	public void setNbreCoups(int nbreCoups) {
		this.nbreCoups = nbreCoups;
	}

	/**
	 * Retourne l'entier x.
	 * 
	 * @return x
	 * 
	 */
	public int getX() {
		return x;
	}

	/**
	 * Setter du l'entier x.
	 * 
	 * @param x
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * Retourne l'entier y.
	 * 
	 * @return y
	 * 
	 */
	public int getY() {
		return y;
	}

	/**
	 * Setter de l'entier y.
	 * 
	 * @param y
	 */
	public void setY(int y) {
		this.y = y;
	}

	@Deprecated
	Plateau initialiserLeJeu() {
		// demander la taille du jeu, le nom de joueur et initaliser le score.
		return null;
	}

}
