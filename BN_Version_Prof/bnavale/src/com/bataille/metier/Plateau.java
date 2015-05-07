package com.bataille.metier;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.bataille.application.BatailleNavaleException;
import com.bataille.util.FileUtil;
import com.bataille.util.Motif;

public class Plateau {
	private String joueur;
	private int longueur;
	private int largeur;

	private List<Navire> listeNav;
	private List<Case> casesOccupees;
	private boolean[][] coupsJoues;
	private boolean[][] casesTouchees;
	private Case[][] lstCases;

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

	public Plateau(int longueur, int largeur) throws CoupException {
		this(longueur, largeur, "Joueur");

	}

	/**
	 * G�n�re le plateau pour un joueur : taille, initialisation des tableaux de
	 * coups jou�s et cases touch�es
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
				lstCases[x][y] = new Case(x, y, false,
						Motif.NONTOUCHE.toString());
				getCoupsJoues()[x][y] = false;
				getCasesTouchees()[x][y] = false;
			}
		}

		listeNav = new ArrayList<Navire>();
		casesOccupees = new ArrayList<Case>();
		this.setJoueur(joueur);

	}

	/**
	 * Vérifie qu'un plateau est correct <br>
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
		// TODO Ctrl Pattern si temps

		return true;
	}

	/**
	 * Permet d'ajouter un navire (en principe au d�but du jeu) sur le plateau
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

	@Deprecated
	private void randomiserPlacement(List<Navire> lstn) {

		for (Navire n : lstn) {
			List<Case> c = placerNavire(n);
			n.setCases(c);
			casesOccupees.addAll(c);
		}

	}

	@Deprecated
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

	@Deprecated
	private Case getNextCase(Case c, boolean isH, int i) {
		Case cn = null;
		if (i == 0) {
			cn = getRandomCase();
		}

		return cn;
	}

	@Deprecated
	private boolean isPlacementHorizontal() {
		Random r = new Random();
		String placement = "" + 0 + r.nextInt(1 - 0);
		return Boolean.valueOf(placement);
	}

	@Deprecated
	private Case getRandomCase() {
		Case c = null;
		// c= new Case();
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

	@Deprecated
	private boolean isCollisionPlacement(int x, int y) {
		boolean isCollision = false;
		for (Case c : casesOccupees) {
			if (c.getPosx() == x && c.getPosy() == y) {
				isCollision = true;
			}
		}
		return isCollision;
	}

	/**
	 * Renvoie la liste des navires coul�s
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
	 * Permet de jouer effectivement le coup aux coordonn�es choisies 1. le coup
	 * a-t-il �t� jou� avant ? 2. parcours de tous les navires du joueur pour
	 * trouver si la case correspond � une case occup�e 3. modification des
	 * �tats et tableaux : touch�, coul�, case jou�e
	 * 
	 * @param x
	 * @param y
	 * @return le navire touch� ou null si coup dans l'eau
	 */
	public Navire jouerCoup(int x, int y) {
		// 1 a t on d�j� jou� le coup
		int nbreTouche = 0;
		Navire navireTouche = null;
		if (!coupsJoues[x][y]) {

			// 2 on demande � la liste des navires qui est touche ? si oui maj
			// de l'�tat + coups jou�s
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

	public List<Navire> getListeNav() {
		return listeNav;
	}

	public void setListeNav(List<Navire> listeNav) {
		this.listeNav = listeNav;
		// this.randomiserPlacement(listeNav);
	}

	public List<Case> getCasesOccupees() {
		return casesOccupees;
	}

	public void setCasesOccupees(List<Case> casesOccupees) {
		this.casesOccupees = casesOccupees;
	}

	public int getLongueur() {
		return longueur;
	}

	public int getLargeur() {
		return largeur;
	}

	public String getJoueur() {
		return joueur;
	}

	public void setJoueur(String joueur) {
		this.joueur = joueur;
	}

	public boolean[][] getCoupsJoues() {
		return coupsJoues;
	}

	public void setCoupsJoues(boolean[][] coupsJoues) {
		this.coupsJoues = coupsJoues;
	}

	public void setCasesTouchees(boolean[][] casesTouchees) {
		this.casesTouchees = casesTouchees;
	}

	public boolean[][] getCasesTouchees() {
		return casesTouchees;
	}

	/**
	 * Retourne un tableau de Cases repr�sentant l'ensemble des cases de ce
	 * plateau.
	 * 
	 * @return lstCases
	 */
	public Case[][] getLstCases() {
		return lstCases;
	}

}
