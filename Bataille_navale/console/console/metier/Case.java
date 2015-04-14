package console.metier;

import console.enums.Motif;

//TODO 
/*
 * Gestion des exceptions
 * Gestions des valeurs saisies (>0) dans les constructeurs.
 * compareTo
 * equals, hashCode
 */

public class Case implements Comparable<Case> {

	// Données nécessaire pour une case
	private int x;
	private int y;
	private Motif motif;
	private boolean estTouche;

	public Case(int x, int y, Motif motif, boolean estTouche) {
		this.x = x;
		this.y = y;
		this.motif = motif;
		this.estTouche = estTouche;

	}

	public Case(String xy, Motif motif, boolean estTouche) {
		String sX = xy.substring(0, xy.indexOf('-'));
		String sY = xy.substring(xy.indexOf('-') + 1, xy.length());
		// on commence par récupérer la chaine jusqu'à '-' puis on récupère tout
		// le reste.

		int x = Integer.parseInt(sX);
		int y = Integer.parseInt(sY);

		this.x = x;
		this.y = y;
		this.estTouche = estTouche;
		this.motif = motif;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Motif getMotif() {
		return motif;
	}

	public void setMotif(Motif motif) {
		this.motif = motif;
	}

	public boolean isEstTouche() {
		return estTouche;
	}

	public void setEstTouche(boolean estTouche) {
		this.estTouche = estTouche;
	}

	@Override
	public int compareTo(Case arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append("(");
		s.append(getX());
		s.append(",");
		s.append(getY());
		s.append(") ");
		s.append("motif : ");
		s.append(getMotif().toString());
		s.append(" est touche :");
		s.append(isEstTouche());
		return s.toString();

	}
}
