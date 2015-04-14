package com.bataille.metier;

import java.util.ArrayList;
import java.util.List;


public class Navire implements Comparable<Navire>{
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
	public int getIdNavire() {
		return idNavire;
	}
	public void setIdNavire(int idNavire) {
		this.idNavire = idNavire;
	}
	public int getTaille() {
		return taille;
	}
	public void setTaille(int taille) {
		this.taille = taille;
	}
	public List<Case> getCases() {
		return cases;
	}
	public void setCases(List<Case> cases) {
		this.cases = cases;
	}
	public boolean isEstCoule() {
		return estCoule;
	}
	public void setEstCoule(boolean estCoule) {
		this.estCoule = estCoule;
	}
	public int getValeurScore() {
		return valeurScore;
	}
	public void setValeurScore(int valeurScore) {
		this.valeurScore = valeurScore;
	}

	@Override
	public int compareTo(Navire arg0) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
}
