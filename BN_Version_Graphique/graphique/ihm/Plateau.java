/**
 * 
 */
package ihm;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author Sylvain - Kevin
 *
 */

public class Plateau {

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((joueur == null) ? 0 : joueur.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Plateau other = (Plateau) obj;
		if (joueur == null) {
			if (other.joueur != null)
				return false;
		} else if (!joueur.equals(other.joueur))
			return false;
		return true;
	}

	private String joueur;
	private int longueur;
	private int largeur;

	private List<Navire> listeNav;
	private List<Case> casesOccupees;
	private boolean[][] coupsJoues;
	private boolean[][] casesTouchees;
	private Case[][] lstCases;

	public Plateau(int longueur, int largeur) throws CoupException {
		this(longueur, largeur, "Joueur");

	}

	/**
	 * Genere le plateau pour un joueur : taille, initialisation des tableaux de
	 * coups joues et cases touchees
	 * 
	 * @param longueur
	 * @param largeur
	 * @param joueur
	 * @throws CoupException
	 *             lorsque le plateau n'est pas correct
	 */
	public Plateau(int longueur, int largeur, String joueur)
			throws CoupException {

		if (!CtrlGoodPlateau(largeur, longueur, joueur))
			throw new CoupException(
					"Le plateau n'est pas initialisé correctement");

		this.longueur = longueur;
		this.largeur = largeur;

		// affectation des dimensions
		lstCases = new Case[longueur][largeur];
		coupsJoues = new boolean[longueur][largeur];
		casesTouchees = new boolean[longueur][largeur];

		// on instancie le plateau vide
		for (int y = 0; y < longueur; y++) {
			for (int x = 0; x < largeur; x++) {
				lstCases[x][y] = new Case(x, y, false, Motif.EAU.toString());
				getCoupsJoues()[x][y] = false;
				getCasesTouchees()[x][y] = false;
			}
		}

		listeNav = new ArrayList<Navire>();
		casesOccupees = new ArrayList<Case>();
		this.setJoueur(joueur);

	}

	/**
	 * Verifie qu'un plateau est correct <br>
	 * longeur = largeur (pas de plateau de 4*2, ...) <br>
	 * Nom non vide
	 * 
	 * @param largeur
	 *            {@link Integer}
	 * @param longueur
	 *            {@link Integer}
	 * @param joueur
	 *            {@link String}
	 * @return <code>true</code> || <code>false</code>
	 */
	private boolean CtrlGoodPlateau(int largeur, int longueur, String joueur) {
		if (largeur < 0 || longueur < 0)
			return false;
		if (largeur != longueur)
			return false;
		if (largeur > 12 || longueur > 12)
			return false;
		if (joueur.isEmpty())
			return false;

		return true;
	}

	/**
	 * Permet d'ajouter un navire (en principe au début du jeu) sur le plateau
	 * du joueur
	 * 
	 * @param n
	 */
	public void ajouterNavire(Navire n) {
		this.listeNav.add(n);
		for (Case c : n.getCases()) {
			this.casesOccupees.add(c);
		}

	}

	/**
	 * Permet de placer des navires de façon aléatoire à partir d'une liste et
	 * met à jour la liste des cases occupées et le motif de ces cases. <br>
	 * 
	 * @param lstn
	 *            {@link List}
	 */
	private void randomiserPlacement(List<Navire> lstn) {
		//
		// for (Navire n : lstn) {
		// List<Case> c = placerNavire(n);
		// n.setCases(c);
		// casesOccupees.addAll(c);
		// }

		for (Navire n : lstn) {
			List<Case> c = placerNavire(n);
			n.setCases(c);

			for (Case a : c) {
				if (n.getTaille() == 2) {
					getLstCases()[a.getPosx()][a.getPosy()]
							.setMotif(NavireCaracteristique.NAVIRESIZE2.getMotif());
				}
				if (n.getTaille() == 3) {
					getLstCases()[a.getPosx()][a.getPosy()]
							.setMotif(NavireCaracteristique.NAVIRESIZE3.getMotif());
				}
				if (n.getTaille() == 4) {
					getLstCases()[a.getPosx()][a.getPosy()]
							.setMotif(NavireCaracteristique.NAVIRESIZE4.getMotif());
				}
				if (n.getTaille() == 5) {
					getLstCases()[a.getPosx()][a.getPosy()]
							.setMotif(NavireCaracteristique.NAVIRESIZE5.getMotif());
				}
			}
			casesOccupees.addAll(c);
		}
	}

	public List<Case> placerNavire(Navire n) {
		List<Case> lc = null;
		Case c = null;
		boolean isH = isPlacementHorizontal();
		boolean nonContinu = false;
		while (!nonContinu) {
			for (int i = 0; i < n.getTaille(); i++) {
				c = getNextCase(c, isH, i);
				if (c == null) {
					nonContinu = true;
				}
			}
		}
		return lc;
	}

	private Case getNextCase(Case c, boolean isH, int i) {
		Case cn = null;
		if (i == 0) {
			cn = getRandomCase();
		}

		return cn;
	}

	private boolean isPlacementHorizontal() {
		Random r = new Random();
		String placement = "" + 0 + r.nextInt(1 - 0);
		return Boolean.valueOf(placement);
	}

	private Case getRandomCase() {
		Case c = null;
		c = new Case();
		Random r = new Random();

		int xRandom = 1 + r.nextInt(this.largeur - 1);
		int yRandom = 1 + r.nextInt(this.largeur - 1);
		while (!isCollisionPlacement(xRandom, yRandom)) {
			xRandom = 1 + r.nextInt(this.largeur - 1);
			yRandom = 1 + r.nextInt(this.largeur - 1);
		}

		c.setPosx(xRandom);
		c.setPosy(yRandom);
		c.setEstTouche(false);
		try {
			c.setMotif(FileUtil.getInstance().getPropriete("symboleCaseVide"));
		} catch (BatailleNavaleException e) {
			e.printStackTrace();
		}
		return c;
	}

	/**
	 * Permet de dire si les entiers passés en paramètres appartiennent déjà à
	 * la liste des case occupées.
	 * 
	 * @param x
	 * @param y
	 * @return <code>true</code> || <code>false</code>
	 */
	public boolean isCollisionPlacement(int x, int y) {
		boolean isCollision = false;
		for (Case c : casesOccupees) {
			if (c.getPosx() == x && c.getPosy() == y) {
				isCollision = true;
			}
		}
		return isCollision;
	}

	/**
	 * Renvoie la liste des navires coulés
	 * 
	 * @return la liste
	 */
	public List<Navire> sontCoules() {
		List<Navire> coules = new ArrayList<Navire>();
		for (Navire n : this.getListeNav()) {
			if (n.isEstCoule()) {
				coules.add(n);
			}
		}
		return coules;
	}

	/**
	 * Permet de jouer effectivement le coup aux coordonnees choisies <br>
	 * 1. le coup a-t-il ete joue avant ? <br>
	 * 2. parcours de tous les navires du joueur pour trouver si la case
	 * correspond a une case occupee <br>
	 * 3. modification des etats et tableaux : touche, coule, case jouee
	 * 
	 * @param x
	 *            {@link Integer}
	 * @param y
	 *            {@link Integer}
	 * @return le navire touché ou null si coup dans l'eau
	 */
	public Navire jouerCoup(int x, int y) {
		int nbreTouche = 0;
		Navire navireTouche = null;

		// 1 - a t on déja joué le coup ?
		if (!coupsJoues[x][y]) {

			getLstCases()[x][y].setMotif(Motif.COUPJOUE.toString());
			getLstCases()[x][y].setEstTouche(true);
			casesTouchees[x][y] = true;

			/*
			 * 2 - On demande la liste des navires. Qui est touche ? Si oui maj
			 * de l'état des coups joués
			 */
			for (Navire n : this.getListeNav()) {
				nbreTouche = 0;
				List<Case> cases = n.getCases();
				for (Case c : cases) {
					if (c.isEstTouche())
						nbreTouche++;
					if (c.getPosx() == x && c.getPosy() == y) {
						c.setEstTouche(true);
						nbreTouche++;
						navireTouche = n;
						casesTouchees[x][y] = true;
						getLstCases()[x][y].setMotif(Motif.TOUCHE.toString());
					}
				}
				if (nbreTouche == cases.size()) {
					n.setEstCoule(true);
				}

			}
			coupsJoues[x][y] = true;

		}
		return navireTouche;
	}

	/**
	 * Retourne la liste des navires du plateau.
	 * 
	 * @return listeNav
	 */
	public List<Navire> getListeNav() {
		return listeNav;
	}

	/**
	 * Setter de la liste des navires du plateau.
	 * 
	 * @param listeNav
	 */
	public void setListeNav(List<Navire> listeNav) {
		this.listeNav = listeNav;
		this.randomiserPlacement(listeNav);
	}

	/**
	 * Retourne la liste des cases occupées par des navires.
	 * 
	 * @return casesOccupees
	 */
	public List<Case> getCasesOccupees() {
		return casesOccupees;
	}

	/**
	 * Setter de la liste des cases occupées par des navires.
	 * 
	 * @param casesOccupees
	 */
	public void setCasesOccupees(List<Case> casesOccupees) {
		this.casesOccupees = casesOccupees;
	}

	/**
	 * Retourne la longueur de ce plateau.
	 * 
	 * @return longueur
	 */
	public int getLongueur() {
		return longueur;
	}

	/**
	 * Retourne la largeur de ce plateau.
	 * 
	 * @return largeur
	 */
	public int getLargeur() {
		return largeur;
	}

	/**
	 * Retourne le nom du joueur de ce plateau.
	 * 
	 * @return joueur
	 */
	public String getJoueur() {
		return joueur;
	}

	/**
	 * Setter du nom du joueur de ce plateau.
	 * 
	 * @param joueur
	 */
	public void setJoueur(String joueur) {
		this.joueur = joueur;
	}

	/**
	 * Retourne un tableau de bool�ens représentant les coups joués sur ce
	 * plateau.
	 * 
	 * @return coupsJoues
	 */
	public boolean[][] getCoupsJoues() {
		return coupsJoues;
	}

	/**
	 * Setter du tableau de booléens représentant les coups joués sur ce
	 * plateau.
	 * 
	 * @param coupsJoues
	 */
	public void setCoupsJoues(boolean[][] coupsJoues) {
		this.coupsJoues = coupsJoues;
	}

	/**
	 * Retourne un tableau de booléens représentant les cases touchées sur ce
	 * plateau.
	 * 
	 * @return casesTouchees
	 */
	public void setCasesTouchees(boolean[][] casesTouchees) {
		this.casesTouchees = casesTouchees;
	}

	/**
	 * Setter du tableau de bool�ens représentant les cases touchées sur ce
	 * plateau.
	 * 
	 * @param casesTouchees
	 */
	public boolean[][] getCasesTouchees() {
		return casesTouchees;
	}

	/**
	 * Retourne un tableau de Cases représentant l'ensemble des cases de ce
	 * plateau.
	 * 
	 * @return lstCases
	 */
	public Case[][] getLstCases() {
		return lstCases;
	}

}
