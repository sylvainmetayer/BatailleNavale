package presentation;

import java.util.Scanner;

import com.bataille.application.Jeu;
import com.bataille.metier.CoupException;
import com.bataille.metier.Plateau;

public class BnavTextIHM {

	Jeu j;

	Scanner sc = new Scanner(System.in);
	String key = "";

	public BnavTextIHM() {

		try {
			j = new Jeu(0, 0, "Jimmy");
		} catch (CoupException c) {
			c.printStackTrace();
		}

		// j.initialiserJeu();
		while (!key.equals("Q")) {

			refreshAffichage();
			key = sc.next();
			if (!(key.length() == 0)) {

				if (key.equalsIgnoreCase("A"))
					afficherPlateau();
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

	private void afficherPlateau() {
		Plateau j1 = j.getPlateauJoueurUn();
		Plateau j2 = j.getPlateauJoueurDeux();
		String ligne;

		System.out.println("Joueur 1 :");

		for (int i = 0; i < j1.getLargeur(); i++) {
			ligne = "" + i + "  ";
			for (int j = 0; j < j1.getLongueur(); j++) {
				ligne = ligne + j1.getCasesOccupees().get(i + j).getMotif();
			}
			System.out.println(ligne);

		}

	}

	private void afficherScores() {

	}

	private void placerNavires() {

	}

	private void sauvegarder() {

	}

	private void jouer() throws CoupException {
		String[] coords = key.split(",");
		int coordX = Integer.parseInt(coords[0]);
		int coordY = Integer.parseInt(coords[1]);
		System.out.println("coup joué : " + coordX + " - " + coordY);
		// TODO gérer
		j.jouer(coordX, coordX, j.getPlateauJoueurUn());
	}

	private void entete() {
		System.out
				.println("*****************************************************************");
		System.out.println("Bataille navale pour l'armée de terre....");
		System.out.println("Votre score : " + j.getScore());
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
				.println("Saisissez les coordonnées de tir séparés par une virgule > ");
	}

	// construire affichage � partir du plateau qui est sur le jeu....

	// afficher menus

	// jouer

	// refresh scores, navires

	public void refreshAffichage() {
		entete();
		int[][] tab = new int[8][8];
		String ligne = "";
		System.out.println("   0  1  2  3  4  5  6  7");
		for (int i = 0; i < tab.length; i++) {
			ligne = "" + i + "  ";
			for (int j = 0; j < tab[i].length; j++) {
				ligne = ligne + "0  ";
			}
			System.out.println(ligne);

		}

		menus();
		System.out
				.println("*****************************************************************");
	}

	public static void main(String[] args) {
		new BnavTextIHM();
	}
}
