package com.bataille;

import java.util.List;

public class Jeu {

	private Plateau plateau;
	private List<Case> coupsJoues;
	private List<Case> caseTouchee;
	private int score;
	private String nomJoueur;	
	private int nbreCoups;
	
	public void jouer(int x, int y) throws CoupException {
		//controler que le coup est permis
		isCoupAutorise(x, y);
		Navire n = this.getPlateau().jouerCoup(x,y);
		
		// actualiser les coupes joués et les cases touchées
		
	}
	
	private void isCoupAutorise( int x, int y) throws CoupException{
		int cx = this.getPlateau().getLongueur();
		int cy = this.getPlateau().getLargeur();
		if (x > cx || y > cy) {
			throw new CoupException("coup non autorisé ");
		}
	}
	
	List<Navire> sontCoules() {
		return null;
	}
	
	List<Navire> tousNavires() {
		return this.getPlateau().getListeNav();
	}
	
	
	private void determinerConditionsJeu(){
		//définir nombre navires, type navire, taille navire
	}
	
	
	
	Plateau initialiserJeu(){
		//demander la taille du jeu, le nom de joueur et initaliser le score.
		return null;
	}
	
	
	
	public Plateau getPlateau() {
		return plateau;
	}

	public void setPlateau(Plateau plateau) {
		this.plateau = plateau;
	}

	public List<Case> getCoupsJoues() {
		return coupsJoues;
	}

	public void setCoupsJoues(List<Case> coupsJoues) {
		this.coupsJoues = coupsJoues;
	}

	public List<Case> getCaseTouchee() {
		return caseTouchee;
	}

	public void setCaseTouchee(List<Case> caseTouchee) {
		this.caseTouchee = caseTouchee;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getNomJoueur() {
		return nomJoueur;
	}

	public void setNomJoueur(String nomJoueur) {
		this.nomJoueur = nomJoueur;
	}

	public int getNbreCoups() {
		return nbreCoups;
	}

	public void setNbreCoups(int nbreCoups) {
		this.nbreCoups = nbreCoups;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
