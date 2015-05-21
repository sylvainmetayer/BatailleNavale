/**
 * 
 */
package ihm;

/**
 * @author Sylvain - Kevin
 *
 */
public enum NavireCaracteristique {
	NAVIRESIZE2(2, 2), NAVIRESIZE3(3, 4), NAVIRESIZE4(4, 6), NAVIRESIZE5(5, 10);

	private final int taille;
	private final int valeurScore;

	public int getTaille() {
		return taille;
	}

	public int getValeurScore() {
		return valeurScore;
	}

	private NavireCaracteristique(final int taille, final int valeurScore) {
		this.taille = taille;
		this.valeurScore = valeurScore;
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

}
