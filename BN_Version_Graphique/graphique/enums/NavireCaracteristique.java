/**
 * 
 */
package enums;

/**
 * @author Sylvain - Kevin
 *
 */
public enum NavireCaracteristique {
	NAVIRESIZE2(2, 2, "Navire taille 2", 0), NAVIRESIZE3(3, 4,
			"Navire taille 3", 1), NAVIRESIZE4(4, 6, "Navire taille 4", 2), NAVIRESIZE5(
			5, 10, "Navire taille 5", 3);

	private final int taille;
	private final int valeurScore;
	private final String nom;
	private final int numeroBateau;

	public int getNumeroBateau() {
		return numeroBateau;
	}

	public int getTaille() {
		return taille;
	}

	public String getNom() {
		return nom;
	}

	public int getValeurScore() {
		return valeurScore;
	}

	private NavireCaracteristique(final int taille, final int valeurScore,
			final String nom, final int numeroBateau) {
		this.taille = taille;
		this.valeurScore = valeurScore;
		this.nom = nom;
		this.numeroBateau = numeroBateau;
	}

	public String getMotif() {
		switch (this) {
		case NAVIRESIZE2:
			return "T";
		case NAVIRESIZE3:
			return "D";
		case NAVIRESIZE4:
			return "C";
		case NAVIRESIZE5:
			return "P";
		default:
			return null;
		}
	}

	@Override
	public String toString() {
		return nom + " " + "taille : " + taille + ",valeur score : "
				+ valeurScore;
	}

}
