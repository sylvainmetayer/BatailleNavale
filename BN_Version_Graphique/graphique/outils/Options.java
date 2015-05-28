/**
 * 
 */
package outils;

import java.awt.Color;
import java.io.Serializable;

import javax.swing.JOptionPane;
import metier.Jeu;

/**
 * Cette classe contient les différentes options et variables utilisées durant
 * le {@link Jeu}.
 * 
 * @author Sylvain METAYER - Kevin DESSIMOULIE
 *
 */
public abstract class Options implements Serializable {

	private static final long serialVersionUID = 1L;

	private static int largeurFenetrePrincipale = 1200;
	private static int hauteurFenetrePrincipale = 500;

	private static int largeurFenetreAide = 1200;
	private static int hauteurFenetreAide = 600;

	private static int largeurFenetreOptions = 600;
	private static int hauteurFenetreOptions = 300;

	private static int tailleGrilleJeu = 8;

	public static final String DEFAULTJOUEURUN = System
			.getProperty("user.name");
	public static final String DEFAULTJOUEURDEUX = "Joueur Deux";
	private static String nomJoueurUn;
	private static String nomJoueurDeux;

	private static final String prefixeDossierImage = "/images/";
	private final static String NAMEFICHIER = "backup";
	private final static String EXTENSIONFICHIER = ".data";

	private static final float[] couleurHSB = Color.RGBtoHSB(19, 23, 214, null);
	private static final Color couleurTexte = Color.getHSBColor(couleurHSB[0],
			couleurHSB[1], couleurHSB[2]);

	public static Color getCouleurTexte() {
		return couleurTexte;
	}

	public static String getPrefixeDossierImage() {
		return prefixeDossierImage;
	}

	public static String getDefaultjoueurdeux() {
		return DEFAULTJOUEURDEUX;
	}

	public static String getDefaultjoueurun() {
		return DEFAULTJOUEURUN;
	}

	public static String getNamefichier() {
		return NAMEFICHIER;
	}

	public static String getExtensionfichier() {
		return EXTENSIONFICHIER;
	}

	public static void setNomJoueurUn(String nomJoueurUn) {
		Options.nomJoueurUn = nomJoueurUn;
	}

	public static void setNomJoueurDeux(String nomJoueurDeux) {
		Options.nomJoueurDeux = nomJoueurDeux;
	}

	public static String getNomJoueurUn() {
		return Options.nomJoueurUn;
	}

	public static String getNomJoueurDeux() {
		return Options.nomJoueurDeux;
	}

	public static int getTailleGrilleJeu() {
		return tailleGrilleJeu;
	}

	/**
	 * Permet de définir une taille de grille pour le jeu. <br>
	 * Seul 3 valeurs sont admises : 6 - 8 - 10.
	 * 
	 * @param tailleGrilleJeu
	 *            {@link Integer}
	 */
	public static void setTailleGrilleJeu(int tailleGrilleJeu) {

		if (tailleGrilleJeu == 8 || tailleGrilleJeu == 6
				|| tailleGrilleJeu == 10)
			Options.tailleGrilleJeu = tailleGrilleJeu;
	}

	public static String getAllOptions() {
		return tailleGrilleJeu + "/" + nomJoueurUn + "/" + nomJoueurDeux;
	}

	public static void setAllOptions(int taille, String nomJ1, String nomJ2) {
		tailleGrilleJeu = taille;
		nomJoueurUn = nomJ1;
		nomJoueurDeux = nomJ2;
	}

	public static int getLargeurFenetrePrincipale() {
		return largeurFenetrePrincipale;
	}

	public static int getHauteurFenetrePrincipale() {
		return hauteurFenetrePrincipale;
	}

	public static int getLargeurFenetreAide() {
		return largeurFenetreAide;
	}

	public static int getHauteurFenetreAide() {
		return hauteurFenetreAide;
	}

	public static int getLargeurFenetreOptions() {
		return largeurFenetreOptions;
	}

	public static int getHauteurFenetreOptions() {
		return hauteurFenetreOptions;
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
	public static boolean questionOuiNon(String message, String titreFenetre) {
		int resultat = JOptionPane.showConfirmDialog(null, message,
				titreFenetre, JOptionPane.YES_NO_OPTION);

		if (resultat == JOptionPane.YES_OPTION)
			return true;
		else
			return false;
	}

}
