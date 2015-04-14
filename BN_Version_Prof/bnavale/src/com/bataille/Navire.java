package com.bataille;

import java.util.ArrayList;
import java.util.List;

public class Navire {
	
	private int taille;
	private List<Case> cases = new ArrayList<Case>();
	private boolean estCoule;
	private int valeurScore;
	
	
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
	
	
}
