/**
 * 
 */
package ihm;

/**
 * @author Sylvain - Kevin
 *
 */
public enum NavireCaracteristique {
	NAVIRESIZE2(2), NAVIRESIZE3(3), NAVIRESIZE4(4), NAVIRESIZE5(5);

	private final int taille;

	public int getTaille() {
		return taille;
	}

	private NavireCaracteristique(final int taille) {
		this.taille = taille;
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
