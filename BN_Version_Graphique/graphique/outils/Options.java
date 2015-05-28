/**
 * 
 */
package outils;

import java.awt.Color;
import javax.swing.JOptionPane;

/**
 * Cette classe contient les différentes options et variables utilisées dans
 * l'application.
 * 
 * @author Sylvain METAYER - Kevin DESSIMOULIE
 *
 */
public abstract class Options {

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

	/**
	 * Retourne la couleur à appliquer à un texte
	 * 
	 * @return {@link Color}
	 */
	public static Color getCouleurTexte() {
		return couleurTexte;
	}

	/**
	 * Retourne le préfixe nécessaire pour accéder au dossier images contenant
	 * les différentes images utilisées dans l'application
	 * 
	 * @return {@link String}
	 */
	public static String getPrefixeDossierImage() {
		return prefixeDossierImage;
	}

	/**
	 * Retourne le nom par défaut du joueur deux. <br>
	 * Celui ci est utilisé lorsqu'aucun nom n'est spécifié pour le joueur deux
	 * 
	 * @return {@link String}
	 */
	public static String getDefaultjoueurdeux() {
		return DEFAULTJOUEURDEUX;
	}

	/**
	 * Retourne le nom par défaut du joueur un. <br>
	 * Celui ci est utilisé lorsqu'aucun nom n'est spécifié pour le joueur un<br>
	 * Il s'agit du nom d'utilisateur.
	 * 
	 * @return {@link String}
	 */
	public static String getDefaultjoueurun() {
		return DEFAULTJOUEURUN;
	}

	/**
	 * Retourne le nom du fichier de sauvegarde.
	 * 
	 * @return {@link String}
	 */
	public static String getNamefichier() {
		return NAMEFICHIER;
	}

	/**
	 * Retourne l'extension du nom de fichier de sauvegarde.
	 * 
	 * @return {@link String}
	 */
	public static String getExtensionfichier() {
		return EXTENSIONFICHIER;
	}

	/**
	 * Permet de définir le nom du joueur un
	 * 
	 * @param nomJoueurUn
	 *            {@link String}
	 */
	public static void setNomJoueurUn(String nomJoueurUn) {
		Options.nomJoueurUn = nomJoueurUn;
	}

	/**
	 * Permet de définir le nom du joueur deux.
	 * 
	 * @param nomJoueurDeux
	 *            {@link String}
	 */
	public static void setNomJoueurDeux(String nomJoueurDeux) {
		Options.nomJoueurDeux = nomJoueurDeux;
	}

	/**
	 * Retourne le nom du joueur un.
	 * 
	 * @return {@link String}
	 */
	public static String getNomJoueurUn() {
		return Options.nomJoueurUn;
	}

	/**
	 * Retourne le nom du joueur deux.
	 * 
	 * @return {@link String}
	 */
	public static String getNomJoueurDeux() {
		return Options.nomJoueurDeux;
	}

	/**
	 * Retourne la taille de la grille.
	 * 
	 * @return {@link Integer}
	 */
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

	/**
	 * Retourne la largeur de la fenetre
	 * 
	 * @return {@link Integer}
	 */
	public static int getLargeurFenetrePrincipale() {
		return largeurFenetrePrincipale;
	}

	/**
	 * Retourne la hauteur de la fenetre
	 * 
	 * @return {@link Integer}
	 */
	public static int getHauteurFenetrePrincipale() {
		return hauteurFenetrePrincipale;
	}

	/**
	 * Retourne la largeur de la fenetre
	 * 
	 * @return {@link Integer}
	 */
	public static int getLargeurFenetreAide() {
		return largeurFenetreAide;
	}

	/**
	 * Retourne la hauteur de la fenetre
	 * 
	 * @return {@link Integer}
	 */
	public static int getHauteurFenetreAide() {
		return hauteurFenetreAide;
	}

	/**
	 * Retourne la largeur de la fenetre
	 * 
	 * @return {@link Integer}
	 */
	public static int getLargeurFenetreOptions() {
		return largeurFenetreOptions;
	}

	/**
	 * Retourne la hauteur de la fenetre
	 * 
	 * @return {@link Integer}
	 */
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
