/**
 * 
 */
package outils;

/**
 * @author Sylvain METAYER - Kevin DESSIMOULIE
 *
 */
public abstract class Options {

	private static int tailleGrilleJeu = 8;
	private static final String prefixeDossierImage = "/images/";
	public static final String DEFAULTJOUEURDEUX = "Joueur Deux";
	public static final String DEFAULTJOUEURUN = System
			.getProperty("user.name");
	private final static String NAMEFICHIER = "backup";
	private final static String EXTENSIONFICHIER = ".data";

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

}
