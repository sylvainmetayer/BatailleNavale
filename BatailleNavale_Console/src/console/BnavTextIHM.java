package console;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Classe principale pour lancer le jeu
 * 
 * @author Sylvain METAYER - Kevin DESSIMOULIE
 *
 */
public class BnavTextIHM {

	Jeu j;

	Scanner sc = new Scanner(System.in);
	String key = "";

	public BnavTextIHM() throws CoordonneeException {

		try {
			j = new Jeu(8, 8, "Joueur 1");
		} catch (CoordonneeException c) {
			c.printStackTrace();
		}
		entete();

		placerNaviresIA();
		placerNavires();

		while (!key.equals("Q")) {

			refreshAffichage();
			key = sc.next();
			if (!(key.length() == 0)) {

				if (key.equalsIgnoreCase("D"))
					placerNavires();
				if (key.equalsIgnoreCase("A"))
					afficherAide();
				if (!key.equalsIgnoreCase("D") && !key.equalsIgnoreCase("A")
						&& !key.equalsIgnoreCase("Q")) {
					try {
						jouer();
					} catch (CoordonneeException e) {
						System.out
								.println("Votre coup est interdit, recommencez !!");
					}
				}

			}
		}

		System.out.println("Au revoir");

	}

	private void afficherAide() {

		System.out.println("Aide : \n" + "~ = case d'eau \n"
				+ "X = navire touché \n" + "@ = tir joué dans de l'eau \n"
				+ "T = navire de taille 2 \n" + "D = navire de taille 3 \n"
				+ "C = navire de taille 4 \n" + "P = navire de taille 5 \n");
	}

	private void afficherScores() {
		System.out.println("Votre score : " + j.getScore());
	}

	private void placerNaviresIA() {
		List<Case> lsC = new ArrayList<Case>();
		Case debut = new Case(2, 4, false, Motif.NAVIRESIZE2.toString());
		Case fin = new Case(2, 5, false, Motif.NAVIRESIZE2.toString());
		lsC.add(debut);
		lsC.add(fin);

		j.getPlateauJoueurDeux().ajouterNavire(
				new Navire(1, 2, lsC, false, 1 * 10));
	}

	private void placerNavires() throws CoordonneeException {
		int x, y;
		String saisie;
		String[] coords;
		Case debut;
		boolean horizontalement, erreur, caseDepartAutorise, caseArriveeAutorise;
		erreur = false;
		System.out.println(j.getPlateauJoueurUn().getJoueur()
				+ ", vous allez placer vos navires");

		// navire 2 case
		System.out
				.println("Saisir coordonnées case départ pour navire à 2 cases");
		saisie = sc.next();
		coords = saisie.split(",");
		x = Integer.parseInt(coords[0]);
		y = Integer.parseInt(coords[1]);
		try {
			caseDepartAutorise = j.isCoordonneeAutorise(x, y,
					j.getPlateauJoueurUn());
		} catch (CoordonneeException e) {
			throw new CoordonneeException("Coordonnées Incorrects !");
		}
		if (caseDepartAutorise) {
			// horizontal ou vertical puis creer case
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
					caseArriveeAutorise = j.isCoordonneeAutorise(x, tmp,
							j.getPlateauJoueurUn());
					if (j.getPlateauJoueurUn().isCollisionPlacement(x, tmp)
							|| !caseArriveeAutorise) {
						System.out
								.println("Collision, le bateau n'a pas pu être ajouté.");
						erreur = true;
					}
					lsC.add(new Case(x, tmp, false, Motif.NAVIRESIZE2
							.toString()));
				} else {
					int tmp = x + 1;
					caseArriveeAutorise = j.isCoordonneeAutorise(tmp, y,
							j.getPlateauJoueurUn());
					if (j.getPlateauJoueurUn().isCollisionPlacement(tmp, y)
							|| !caseArriveeAutorise) {
						System.out
								.println("Collision, le bateau n'a pas pu être ajouté.");
						erreur = true;
					}
					lsC.add(new Case(tmp, y, false, Motif.NAVIRESIZE2
							.toString()));
				}
			}
			if (!erreur) {
				j.getPlateauJoueurUn().ajouterNavire(
						new Navire(1, 2, lsC, false, 1 * 10));
			}
		}
	}

	private void jouer() throws CoordonneeException {
		String[] coords = key.split(",");
		int coordX = Integer.parseInt(coords[0]);
		int coordY = Integer.parseInt(coords[1]);
		System.out.println("coup joue : " + coordX + " - " + coordY);
		j.jouer(coordX, coordY, j.getPlateauJoueurDeux());
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
		System.out.println("D = Demarrer une nouvelle partie");
		System.out.println("A = Afficher l'aide");
		System.out.println("Q = Quitter le jeu");
		System.out
				.println("Pour saisir les coordonnees de tir separes les par une virgule > ");
	}

	public void refreshAffichage() {
		entete();
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

	public static void main(String[] args) throws CoordonneeException {
		new BnavTextIHM();
	}
}
