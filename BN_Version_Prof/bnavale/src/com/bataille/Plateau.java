package com.bataille;

import java.util.ArrayList;
import java.util.List;

public class Plateau {
	
	private final int longueur;
	private final int largeur;
	//private final Case[][] lstCases;
	private List<Navire> listeNav;
	
	private List<Case> casesOccupees;
	
	private boolean[][] coupsJoues;
	
	public Plateau(int longueur, int largeur) {
		super();
		this.longueur = longueur;
		this.largeur = largeur;
		
		//vérifier long max, larg max; pas zéro/ pas négatif et minimum
		//this.lstCases = new Case[longueur][largeur];
		//affectation des dimensions
		coupsJoues = new boolean[longueur][largeur];
		
		//
		listeNav = new ArrayList<Navire>();
		casesOccupees = new ArrayList<Case>();
	}
	
	private void randomiserPlacement(List<Navire> lstn){
		
		
	}
	
	public List<Navire> sontCoules(){
		List<Navire> coules = new ArrayList<Navire>();
		for (Navire n : this.getListeNav()){
			if (n.isEstCoule()){
				coules.add(n);
			}
		}
		return coules;
	}
	
	
	public void placerNavire(int taille){
		//randomiserPlacement
		
	}
	
	public Navire jouerCoup(int x, int y){
		//1 a t on déjà joué le coup
		int nbreTouche = 0;
		Navire navireTouche = null;
		if (!coupsJoues[x][y]) {
		
		//2 on demande à la liste des navires qui est touche ? si oui maj de l'état + coups joués
			for (Navire n : this.getListeNav()){
				nbreTouche = 0;
				List<Case> cases = n.getCases();
				for(Case c : cases){
					if (c.isEstTouche()) nbreTouche++;
					if (c.getPosx()== x && c.getPosy() == y) {
						c.setEstTouche(true);
						nbreTouche++;
						navireTouche = n;
					}
				}
				if (nbreTouche == cases.size()) {
					n.setEstCoule(true);
				}
				
			} 
			coupsJoues[x][y]= true;
		}
		return navireTouche;
	}

	public List<Navire> getListeNav() {
		return listeNav;
	}

	public void setListeNav(List<Navire> listeNav) {
		this.listeNav = listeNav;
	}

	public List<Case> getCasesOccupees() {
		return casesOccupees;
	}

	public void setCasesOccupees(List<Case> casesOccupees) {
		this.casesOccupees = casesOccupees;
	}

	public int getLongueur() {
		return longueur;
	}

	public int getLargeur() {
		return largeur;
	}
	
	
}
