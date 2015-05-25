package metier;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe représentant un navire
 * 
 * @author Sylvain METAYER - Kevin DESSIMOULIE
 *
 */
public class Navire implements Comparable<Navire> {

	@Override
	public int compareTo(Navire n) {
		return this.idNavire = n.idNavire;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idNavire;
		result = prime * result + taille;
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
		Navire other = (Navire) obj;
		if (idNavire != other.idNavire)
			return false;
		if (taille != other.taille)
			return false;
		return true;
	}

	private int idNavire;
	private int taille;
	private List<Case> cases = new ArrayList<Case>();
	private boolean estCoule;
	private int valeurScore;

	/**
	 * Constructeur Navire
	 * 
	 * @param idNavire
	 *            {@link Integer}
	 * @param taille
	 *            {@link Integer}
	 * @param cases
	 *            {@link List}
	 * @param estCoule
	 *            {@link Boolean}
	 * @param valeurScore
	 *            {@link Integer}
	 * 
	 */
	public Navire(int idNavire, int taille, List<Case> cases, boolean estCoule,
			int valeurScore) {
		super();
		this.idNavire = idNavire;
		this.taille = taille;
		this.cases = cases;
		this.estCoule = estCoule;
		this.valeurScore = valeurScore;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Navire [idNavire=");
		builder.append(idNavire);
		builder.append(", taille=");
		builder.append(taille);
		builder.append(", cases=");
		builder.append(cases);
		builder.append(", estCoule=");
		builder.append(estCoule);
		builder.append(", valeurScore=");
		builder.append(valeurScore);
		builder.append("]");
		return builder.toString();
	}

	/**
	 * Retourne l'ID du navire.
	 * 
	 * @return idNavire {@link Integer}
	 */
	public int getIdNavire() {
		return idNavire;
	}

	/**
	 * Setter de l'ID du navire
	 * 
	 * @param idNavire
	 *            {@link Integer}
	 */
	public void setIdNavire(int idNavire) {
		this.idNavire = idNavire;
	}

	/**
	 * Retourne la taille du navire.
	 * 
	 * @return taille {@link Integer}
	 */
	public int getTaille() {
		return taille;
	}

	/**
	 * Setter de la taille du navire.
	 * 
	 * @param taille
	 *            {@link Integer}
	 */
	public void setTaille(int taille) {
		this.taille = taille;
	}

	/**
	 * Retourne une liste de cases sur lesquelles sont positionné le navire.
	 * 
	 * @return cases {@link List}
	 */
	public List<Case> getCases() {
		return cases;
	}

	/**
	 * Setter de la liste de cases sur lesquelles sont positionné le navire.
	 * 
	 * @param cases
	 *            {@link List}
	 */
	public void setCases(List<Case> cases) {
		this.cases = cases;
	}

	/**
	 * Retourne un booléen indiquant si le navire est coulé ou pas.
	 * 
	 * @return estCoule {@link Boolean}
	 */
	public boolean isEstCoule() {
		return estCoule;
	}

	/**
	 * Setter du booléen indiquant si le navire est coulé ou pas.
	 * 
	 * @param estCoule
	 *            {@link Boolean}
	 */
	public void setEstCoule(boolean estCoule) {
		this.estCoule = estCoule;
	}

	/**
	 * Retourne la valeur du navire utiisée pour calculer le score du joueur.
	 * 
	 * @return valeurScore {@link Integer}
	 */
	public int getValeurScore() {
		return valeurScore;
	}

	/**
	 * Setter de la valeur du navire utiisée pour calculer le score du joueur
	 * 
	 * @param valeurScore
	 *            {@link Integer}
	 */
	public void setValeurScore(int valeurScore) {
		this.valeurScore = valeurScore;
	}

	
}
