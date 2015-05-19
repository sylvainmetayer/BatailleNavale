package graphique.com.bataille.metier;

import com.bataille.util.Motif;

/**
 * Classe représentant une classe
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
		builder.append("Case [posx=");
		builder.append(posx);
		builder.append(", posy=");
		builder.append(posy);
		builder.append(", estTouche=");
		builder.append(estTouche);
		builder.append(", motif=");
		builder.append(motif);
		builder.append("]");
		return builder.toString();
	}

	/**
	 * Constructeur Case
	 * 
	 * @param posx
	 * @param posy
	 * @param estTouche
	 * @param motif
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
	 * @param estTouche
	 * @param motif
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
		this.motif = Motif.EAU.toString();
	}

	/**
	 * Retourne la position x de la case.
	 * 
	 * @return posx
	 */
	public int getPosx() {
		return posx;
	}

	/**
	 * Setter de la position x de la case.
	 * 
	 * @param posx
	 */
	public void setPosx(int posx) {
		this.posx = posx;
	}

	/**
	 * Retourne la position y de la case.
	 * 
	 * @return posy
	 */
	public int getPosy() {
		return posy;
	}

	/**
	 * Setter de la position y de la case.
	 * 
	 * @param posy
	 */
	public void setPosy(int posy) {
		this.posy = posy;
	}

	/**
	 * Retourne un booléen indiquant si la case a été touchée ou pas.
	 * 
	 * @return estTouche
	 */
	public boolean isEstTouche() {
		return estTouche;
	}

	/**
	 * Setter du booléen indiquant si la case a été touchée ou pas.
	 * 
	 * @param estTouche
	 */
	public void setEstTouche(boolean estTouche) {
		this.estTouche = estTouche;
	}

	/**
	 * Retourne le motif de la case.
	 * 
	 * @return motif
	 */
	public String getMotif() {
		return motif;
	}

	/**
	 * Setter du motif de la case.
	 * 
	 * @param motif
	 */
	public void setMotif(String motif) {
		this.motif = motif;
	}

}
