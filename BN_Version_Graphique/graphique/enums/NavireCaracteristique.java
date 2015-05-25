/**
 * 
 */
package enums;

import javax.swing.ImageIcon;

/**
 * Cette classe représente les différentes caractéristiques d'un navire : <br>
 * <ul>
 * <li>nombre de case occupées sur un plateau
 * <li>valeur de score
 * <li>nom
 * <li>numero
 * <li>motif
 * 
 * @author Sylvain METAYER - Kevin DESSIMOULIE
 *
 */
public enum NavireCaracteristique {
	TORPILLEUR(2, 2, "torpilleur", 0, "T", "torpilleur.jpg",
			"Représente un torpilleur (2 cases)"), SOUSMARIN(3, 6,
			"sous marin", 1, "S", "sousMarin.jpg",
			"Représente un sous-marin (3 cases)"), CROISEUR(4, 8, "croiseur",
			2, "C", "croiseur.jpg", "Représente un croiseur (4 cases)"), PORTEAVION(
			5, 10, "porte avion", 3, "P", "porteAvion.jpg",
			"Représente un porte-avion (5 cases)");

	private final int taille;
	private final int valeurScore;
	private final String nom;
	private final int numeroNavire;
	private final String motif;
	private final String cheminIcon;
	private final String textEnrichi;

	/**
	 * Constructeur de l'énumération.
	 * 
	 * @param taille
	 *            {@link Integer}
	 * @param valeurScore
	 *            {@link Integer}
	 * @param nom
	 *            {@link String}
	 * @param numeroBateau
	 *            {@link Integer}
	 * @param motif
	 *            {@link String}
	 */
	private NavireCaracteristique(final int taille, final int valeurScore,
			final String nom, final int numeroBateau, final String motif,
			final String cheminIcon, final String textEnrichi) {
		this.taille = taille;
		this.valeurScore = valeurScore;
		this.nom = nom;
		this.numeroNavire = numeroBateau;
		this.motif = motif;
		this.cheminIcon = cheminIcon;
		this.textEnrichi = textEnrichi;
	}

	/**
	 * Retourne le numero du navire
	 * 
	 * @return {@link Integer}
	 */
	public int getNumeroNavire() {
		return numeroNavire;
	}

	/**
	 * Retourne la taille du navire
	 * 
	 * @return {@link Integer}
	 */
	public int getTaille() {
		return taille;
	}

	/**
	 * Retourne le nom du navire
	 * 
	 * @return {@link String}
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * Retourne la valeur de score du navire
	 * 
	 * @return {@link Integer}
	 */
	public int getValeurScore() {
		return valeurScore;
	}

	/**
	 * Retourne le motif associé à ce navire
	 * 
	 * @return {@link String}
	 */
	public String getMotif() {
		return motif;
	}

	/**
	 * Retourne les caractéristiques d'un navire à partir de sa taille
	 * 
	 * @param taille
	 *            {@link Integer}
	 * @return {@link NavireCaracteristique}
	 */
	public static NavireCaracteristique getCaracteristiqueByTaille(int taille) {
		if (taille == 2) {
			return NavireCaracteristique.TORPILLEUR;
		}
		if (taille == 3) {
			return NavireCaracteristique.SOUSMARIN;
		}
		if (taille == 4) {
			return NavireCaracteristique.CROISEUR;
		}
		if (taille == 5) {
			return NavireCaracteristique.PORTEAVION;
		}
		return null;
	}

	/**
	 * Retourne le score maximal atteignable
	 * 
	 * @return {@link Integer}
	 */
	public static int getScoreTotal() {
		int somme = 0;

		for (NavireCaracteristique n : NavireCaracteristique.values()) {
			somme = somme + n.getValeurScore();
		}
		return somme;
	}

	/**
	 * Retourne l'icone correspondant au navire
	 * 
	 * @return {@link ImageIcon}
	 */
	public ImageIcon getIcon() throws NullPointerException {
		String prefixe = "/images/";
		ImageIcon ic = new ImageIcon(this.getClass().getResource(
				prefixe + cheminIcon));
		return ic;
	}

	/**
	 * Retourne la description detaille du navire
	 * 
	 * @return {@link String}
	 */
	public String getTextEnrichi() {
		return textEnrichi;
	}

}
