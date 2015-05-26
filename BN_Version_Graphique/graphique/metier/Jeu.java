package metier;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Cette classe représente un {@link Jeu} de bataille navale
 * 
 * @author Sylvain METAYER - Kevin DESSIMOULIE
 *
 */
public class Jeu implements Serializable {

	private Plateau plateauJoueurUn;
	private Plateau plateauJoueurDeux;
	private int x;
	private int y;
	private int score;

	private int nbreCoups;

	/**
	 * Permet de récupérér le plateau correspondant
	 * 
	 * @param p
	 *            {@link Plateau}
	 * @return {@link Plateau}
	 */
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
	 *            {@link Integer}
	 * @param largeur
	 *            {@link Integer}
	 * @param nomJoueurUn
	 *            {@link String}
	 * @param nomJoueurDeux
	 *            {@link String}
	 * @throws CoupException
	 */
	public Jeu(int hauteur, int largeur, String nomJoueurUn,
			String nomJoueurDeux) throws CoupException {
		this.plateauJoueurUn = new Plateau(hauteur, largeur, nomJoueurUn);
		this.plateauJoueurDeux = new Plateau(hauteur, largeur, nomJoueurDeux);
		this.score = 0;
		this.nbreCoups = 0;

	}

	/**
	 * Permet de jouer un coup sur un plateau donne. Le joueur A fait un coup
	 * x,y....
	 * 
	 * @param x
	 *            {@link Integer}
	 * @param y
	 *            {@link Integer}
	 * @param plateau
	 *            {@link Plateau}
	 * @return Navire : le navire touche ou coule, ou alors null si rien ne
	 *         s'est passe
	 * @throws CoupException
	 * @see {@link Plateau#jouerCoup(int, int)} pour la gestion des cases
	 *      ajoutées, touchées, ...
	 */
	public Navire jouer(int x, int y, Plateau plateau) throws CoupException {

		isCoupAutorise(x, y, plateau);
		Navire n = this.goodPlateau(plateau).jouerCoup(x, y);

		return n;

	}

	/**
	 * Permet de verifier que les coordonnees du coup joue sont bien dans la
	 * taille de l'aire de jeu
	 * 
	 * @param x
	 *            {@link Integer}
	 * @param y
	 *            {@link Integer}
	 * @param plateau
	 *            {@link Plateau}
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
	 * @throws BatailleNavaleException
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

		plateauJoueurUn.setListeNav(listeNav);
		plateauJoueurDeux.setListeNav(listeNav);
	}

	/**
	 * Retourne le plateau du joueur Un (l'humain).
	 * 
	 * @return plateauJoueurUn {@link Plateau}
	 * 
	 */
	public Plateau getPlateauJoueurUn() {
		return plateauJoueurUn;
	}

	/**
	 * Setter du plateau du joueur Un (l'humain).
	 * 
	 * @param plateauJoueurUn
	 *            {@link Plateau}
	 */
	public void setPlateauJoueurUn(Plateau plateauJoueurUn) {
		this.plateauJoueurUn = plateauJoueurUn;
	}

	/**
	 * Retourne le plateau du joueur Deux (l'ordinateur).
	 * 
	 * @return plateauJoueurDeux {@link Plateau}
	 */
	public Plateau getPlateauJoueurDeux() {
		return plateauJoueurDeux;
	}

	/**
	 * Setter du plateau du joueur Deux (l'ordinateur).
	 * 
	 * @param plateauJoueurDeux
	 *            {@link Plateau}
	 */
	public void setPlateauJoueurDeux(Plateau plateauJoueurDeux) {
		this.plateauJoueurDeux = plateauJoueurDeux;
	}

	/**
	 * Retourne le score du joueur
	 * 
	 * @return score {@link Integer}
	 * 
	 */
	public int getScore() {
		return score;
	}

	/**
	 * Setter du score du joueur
	 * 
	 * @param score
	 *            {@link Integer}
	 */
	public void setScore(int score) {
		this.score = score;
	}

	/**
	 * Retourne le nombre de coups d'un joueur durant la partie.
	 * 
	 * @return nbreCoups {@link Integer}
	 * 
	 */
	public int getNbreCoups() {
		return nbreCoups;
	}

	/**
	 * Setter du nombre de coups d'un joueur durant la partie.
	 * 
	 * @param nbreCoups
	 *            {@link Integer}
	 */
	public void setNbreCoups(int nbreCoups) {
		this.nbreCoups = nbreCoups;
	}

	/**
	 * Retourne l'entier x.
	 * 
	 * @return x {@link Integer}
	 * 
	 */
	public int getX() {
		return x;
	}

	/**
	 * Setter du l'entier x.
	 * 
	 * @param x
	 *            {@link Integer}
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * Retourne l'entier y.
	 * 
	 * @return y {@link Integer}
	 * 
	 */
	public int getY() {
		return y;
	}

	/**
	 * Setter de l'entier y.
	 * 
	 * @param y
	 *            {@link Integer}
	 */
	public void setY(int y) {
		this.y = y;
	}

}
