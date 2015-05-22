/**
 * 
 */
package enums;

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
	NAVIRESIZE2(2, 2, "Navire taille 2", 0, "T"), NAVIRESIZE3(3, 4,
			"Navire taille 3", 1, "D"), NAVIRESIZE4(4, 6, "Navire taille 4", 2,
			"C"), NAVIRESIZE5(5, 10, "Navire taille 5", 3, "P");

	private final int taille;
	private final int valeurScore;
	private final String nom;
	private final int numeroNavire;
	private final String motif;

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
			final String nom, final int numeroBateau, String motif) {
		this.taille = taille;
		this.valeurScore = valeurScore;
		this.nom = nom;
		this.numeroNavire = numeroBateau;
		this.motif = motif;
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

}
