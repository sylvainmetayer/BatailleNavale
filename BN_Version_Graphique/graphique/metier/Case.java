/**
 * 
 */
package metier;

import outils.MotifsDivers;

/**
 * 
 * @author Sylvain - Kevin
 *
 */

/**
 * Cette classe représente une case du {@link Plateau} de {@link Jeu}s
 * 
 * @author Sylvain METAYER - Kevin DESSIMOULIE
 *
 */
public class Case implements Comparable<Case> {

	@Override
	public int compareTo(Case uneCase) {
		return (this.getPosx() - uneCase.getPosx());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (estTouche ? 1231 : 1237);
		result = prime * result + posx;
		result = prime * result + posy;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Case other = (Case) obj;
		if (estTouche != other.estTouche)
			return false;
		if (posx != other.posx)
			return false;
		if (posy != other.posy)
			return false;
		return true;
	}

	private int posx;
	private int posy;
	private boolean estTouche;
	private String motif;

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("(");
		builder.append(posx);
		builder.append(", ");
		builder.append(posy);
		builder.append(", estTouche=");
		builder.append(estTouche);
		builder.append(", motif=");
		builder.append(motif);
		builder.append(")");
		return builder.toString();
	}

	/**
	 * Constructeur Case
	 * 
	 * @param posx
	 *            {@link Integer}
	 * @param posy
	 *            {@link Integer}
	 * @param estTouche
	 *            {@link Boolean}
	 * @param motif
	 *            {@link String}
	 * 
	 */
	public Case(int posx, int posy, boolean estTouche, String motif) {
		super();
		this.posx = posx;
		this.posy = posy;
		this.estTouche = estTouche;
		this.motif = motif;
	}

	/**
	 * Constructeur Case
	 * 
	 * @param position
	 *            {@link String}
	 * @param estTouche
	 *            {@link Boolean}
	 * @param motif
	 *            {@link String}
	 * 
	 */
	public Case(String position, boolean estTouche, String motif) {
		String monX = position.substring(0, position.indexOf("-"));
		String monY = position.substring(position.indexOf("-") + 1,
				position.length());
		this.posx = Integer.valueOf(monX);
		this.posy = Integer.valueOf(monY);
		this.estTouche = estTouche;
		this.motif = motif;
	}

	/**
	 * Constructeur Case par défaut
	 */
	public Case() {
		this.posx = 0;
		this.posy = 0;
		this.estTouche = false;
		this.motif = MotifsDivers.EAU.getMotif();
	}

	/**
	 * Retourne la position x de la case.
	 * 
	 * @return {@link Integer}
	 */
	public int getPosx() {
		return posx;
	}

	/**
	 * Setter de la position x de la case.
	 * 
	 * @param posx
	 *            {@link Integer}
	 */
	public void setPosx(int posx) {
		this.posx = posx;
	}

	/**
	 * Retourne la position y de la case.
	 * 
	 * @return posy {@link Integer}
	 */
	public int getPosy() {
		return posy;
	}

	/**
	 * Setter de la position y de la case.
	 * 
	 * @param posy
	 *            {@link Integer}
	 */
	public void setPosy(int posy) {
		this.posy = posy;
	}

	/**
	 * Retourne un booléen indiquant si la case a été touchée ou pas.
	 * 
	 * @return estTouche {@link Boolean}
	 */
	public boolean isEstTouche() {
		return estTouche;
	}

	/**
	 * Setter du booléen indiquant si la case a été touchée ou pas.
	 * 
	 * @param estTouche
	 *            {@link Boolean}
	 */
	public void setEstTouche(boolean estTouche) {
		this.estTouche = estTouche;
	}

	/**
	 * Retourne le motif de la case.
	 * 
	 * @return motif {@link String}
	 */
	public String getMotif() {
		return motif;
	}

	/**
	 * Setter du motif de la case.
	 * 
	 * @param motif
	 *            {@link String}
	 */
	public void setMotif(String motif) {
		this.motif = motif;
	}

}
