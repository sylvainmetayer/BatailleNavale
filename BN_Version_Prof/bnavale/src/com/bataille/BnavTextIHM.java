package com.bataille;

import java.util.Scanner;

public class BnavTextIHM {
	Jeu j = new Jeu();
	
	Scanner sc = new Scanner(System.in);
	String key ="";
	public BnavTextIHM(){
		j.initialiserJeu();
		while (!key.equals("Q")){
			
			refreshAffichage();
			key = sc.next();
			if (!(key.length()==0)) {
				
				if (key.equalsIgnoreCase("A")) afficherPlateau();
				if (key.equalsIgnoreCase("S")) sauvegarder();
				if (key.equalsIgnoreCase("D")) placerNavires();
				if (key.equalsIgnoreCase("T")) afficherScores();
				try {
					jouer();
				} catch (CoupException e) {
					System.out.println("Votre coup est interdit, recommencez !!");
				}
			}
		}
		
		
	}
	
	private void afficherPlateau(){
		
	}
	private void afficherScores(){
		
	}
	private void placerNavires(){
	
	}
	
	private void sauvegarder(){
		
	}
	private void jouer() throws CoupException{
		String[]coords = key.split(",");
		int coordX = Integer.parseInt(coords[0]);
		int coordY = Integer.parseInt(coords[1]);
		System.out.println("coup jou� : "+coordX+" - "+coordY);
		j.jouer(coordX, coordX);
	}
	
	private void entete(){
		System.out.println("*****************************************************************");
		System.out.println("Bataille navale pour l'arm�e de terre....");
		System.out.println("Votre score : "+j.getScore());
		System.out.println("*****************************************************************\n");
	}
	
	private void menus(){
		System.out.println("");
		System.out.println("A = afficher le plateau");
		System.out.println("D = d�marrer la partie");
		System.out.println("S = sauvegarder la partie");
		System.out.println("T = afficher score");
		System.out.println("Q = quitter le jeu");
		System.out.println("Saisissez les coordonn�es de tir s�par�s par une virgule > ");
	}
	//construire affichage � partir du plateau qui est sur le jeu....
	
	//afficher menus

	//jouer
	
	//refresh scores, navires
	
	public void refreshAffichage(){
		entete();
		int[][]tab = new int[8][8];
		String ligne = "";
		System.out.println("   0  1  2  3  4  5  6  7");
		for (int i =0; i < tab.length; i++) {
			ligne =""+i+"  ";
			for (int j = 0; j < tab[i].length; j++){
				ligne = ligne +"0  ";
			}
			System.out.println(ligne);
			
		}
		
		menus();
		System.out.println("*****************************************************************");
	}
	public static void main(String[] args){
		new BnavTextIHM().refreshAffichage();
	}
}
