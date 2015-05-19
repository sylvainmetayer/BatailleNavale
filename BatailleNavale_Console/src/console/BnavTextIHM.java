package console;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BnavTextIHM {

	Jeu j;

	Scanner sc = new Scanner(System.in);
	String key = "";

	public BnavTextIHM() {

		try {
			j = new Jeu(8, 8, "Joueur 1");
		} catch (CoupException c) {
			c.printStackTrace();
		}

		// TODO changer la génération automatique en placement par l'user
		placerNavires();

		// on génère le plateau
		// TODO à fixer
		// j.genererJeu();

		while (!key.equals("Q")) {

			refreshAffichage();
			key = sc.next();
			if (!(key.length() == 0)) {

				if (key.equalsIgnoreCase("A"))
					afficherLesPlateaux();
				if (key.equalsIgnoreCase("S"))
					sauvegarder();
				if (key.equalsIgnoreCase("D"))
					placerNavires();
				if (key.equalsIgnoreCase("T"))
					afficherScores();
				if (!key.equalsIgnoreCase("T") && !key.equalsIgnoreCase("D")
						&& !key.equalsIgnoreCase("S")
						&& !key.equalsIgnoreCase("A")) {
					try {
						jouer();
					} catch (CoupException e) {
						System.out
								.println("Votre coup est interdit, recommencez !!");
					}
				}

			}
		}

		System.out.println("Au revoir");

	}

	private void afficherScores() {
		System.out.println("Votre score : " + j.getScore());
	}

	private void placerNavires() {
		int x, y;
		String saisie;
		String[] coords;
		Case debut;
		boolean horizontalement, erreur;
		erreur = false;
		System.out.println(j.getPlateauJoueurUn().getJoueur()
				+ ", vous allez placer vos navires");

		// navire 2 case
		System.out
				.println("Saisir coordonnées case départ pour navire à 1 cases");
		saisie = sc.next();
		coords = saisie.split(",");
		x = Integer.parseInt(coords[0]);
		y = Integer.parseInt(coords[1]);
		// horizon vertical puis creer case
		System.out
				.println("Placer horizontalement vers la droite ? (true/false)\n"
						+ "Sinon, le placement sera placé verticalement vers le bas");
		horizontalement = sc.nextBoolean();
		List<Case> lsC = new ArrayList<Case>();
		debut = new Case(x, y, false, Motif.NAVIRESIZE2.toString());
		lsC.add(debut);
		for (int i = 0; i < 1; i++) {
			if (horizontalement) {
				int tmp = y + 1;
				if (j.getPlateauJoueurUn().isCollisionPlacement(x, tmp)) {
					System.out
							.println("Collision, le bateau n'a pas pu être ajouté.");
					erreur = true;
				}
				lsC.add(new Case(x, tmp, false, Motif.NAVIRESIZE2.toString()));
			} else {
				int tmp = x + 1;
				if (j.getPlateauJoueurUn().isCollisionPlacement(tmp, y)) {
					System.out
							.println("Collision, le bateau n'a pas pu être ajouté.");
					erreur = true;
				}
				lsC.add(new Case(tmp, y, false, Motif.NAVIRESIZE2.toString()));
			}
		}
		if (!erreur) {
			j.getPlateauJoueurUn().ajouterNavire(
					new Navire(1, 1, lsC, false, 1 * 10));
		}

	}

	//
	// private void ajouterBateau(Motif m)

	private void sauvegarder() {
		// TODO en graphique.
	}

	private void jouer() throws CoupException {
		String[] coords = key.split(",");
		int coordX = Integer.parseInt(coords[0]);
		int coordY = Integer.parseInt(coords[1]);
		System.out.println("coup joue : " + coordX + " - " + coordY);
		// TODO gerer
		j.jouer(coordX, coordX, j.getPlateauJoueurUn());
	}

	private void entete() {
		System.out
				.println("*****************************************************************");
		System.out.println("Bataille navale pour l'armee de "
				+ j.getPlateauJoueurUn().getJoueur());
		afficherScores();
		System.out
				.println("*****************************************************************\n");
	}

	private void menus() {
		System.out.println("");
		System.out.println("A = Afficher le plateau");
		System.out.println("D = Demarrer la partie");
		System.out.println("S = Sauvegarder la partie");
		System.out.println("T = Afficher score");
		System.out.println("Q = Quitter le jeu");
		System.out
				.println("Saisissez les coordonnees de tir separes par une virgule > ");
	}

	// construire affichage a partir du plateau qui est sur le jeu....

	// afficher menus

	// jouer

	// refresh scores, navires

	public void refreshAffichage() {
		entete();
		/*
		 * int[][] tab = new int[8][8]; String ligne = "";
		 * System.out.println("   0  1  2  3  4  5  6  7"); for (int i = 0; i <
		 * tab.length; i++) { ligne = "" + i + "  "; for (int j = 0; j <
		 * tab[i].length; j++) { ligne = ligne + "0  "; }
		 * System.out.println(ligne);
		 * 
		 * }
		 */
		afficherLesPlateaux();
		menus();
		System.out
				.println("*****************************************************************");
	}

	/**
	 * Affiche le plateau passé en paramètre.
	 * 
	 */
	private void afficherPlateau(Plateau p) {
		String ligne = "";
		System.out.println("   0  1  2  3  4  5  6  7");
		for (int i = 0; i < j.goodPlateau(p).getLargeur(); i++) {
			ligne = "" + i + "  ";
			for (int t = 0; t < j.goodPlateau(p).getLongueur(); t++) {
				ligne = ligne + j.goodPlateau(p).getLstCases()[i][t].getMotif()
						+ "  ";
			}
			System.out.println(ligne);
		}
	}

	/**
	 * Affiche les plateaux de la partie.
	 * 
	 */
	private void afficherLesPlateaux() {
		System.out.println("Votre plateau :");
		afficherPlateau(j.getPlateauJoueurUn());
		System.out.println("");
		System.out.println("Plateau adverse :");
		afficherPlateau(j.getPlateauJoueurDeux());
	}

	public static void main(String[] args) {
		new BnavTextIHM();
	}
}
