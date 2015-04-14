package com.bataille.tests;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.StringTokenizer;

import com.bataille.application.BatailleNavaleException;
import com.bataille.application.Jeu;
import com.bataille.metier.Case;
import com.bataille.metier.Navire;
import com.bataille.metier.Plateau;
import com.bataille.util.FileUtil;

/**
 * @author V902458
 *
 */
public class TestJeu {
	Jeu j = null;
	Plateau pJoueur ;
	Plateau pOrdinateur;
	
	/**
	 * Le constructeur cr�e un jeu et r�cup�re les 2 plateaux pour jouer dessus
	 * @param x
	 * @param y
	 */
	public TestJeu(int x, int y) {		
		j = new Jeu(x,y,"Jimmy");
		pJoueur = j.getPlateauJoueurUn();
		pOrdinateur = j.getPlateauJoueurDeux();
		//		
		
	}

	/**
	 * On passe une chaine formatt�e ex : String  nav1 = "1 2 2-0;2-1 false 200";
	 * cette chaine est d�cortiqu�e pour cr�er une instance de navire
	 * @param infos
	 * @return
	 */
	private static Navire convertStringToNavire(String infos) {
		//String  nav1 = "1 2 2-0;2-1 false 200";
		Case[] lstCase = null;
		Navire n = null;
		int x = 0;
		StringTokenizer st = new StringTokenizer(infos);
		int i=0;
		String ch = null;
		String idNav="", tailleNav="", caseNav="", couleNav="", scoreNav="";
		
		//s�paration des �l�ments pour le navire
		while (st.hasMoreElements()){
			ch = (String) st.nextElement();
			i++;			
			System.out.println(ch);
			switch(i){
			//identifiant de navire (utile dans le equals)
			case 1:
				idNav =ch;
				break;
			//taille de navire = un int. attention � ce que la taille corresponde bien au nombre de cases dans ce navire
			case 2:
				tailleNav =ch;
				lstCase = new Case[Integer.valueOf(tailleNav)];
				break;	
			//liste des cases du navire 1 ou plus selon le type de navire. dans cet l'exemple on ne va que jusqu'� 5
			case 3:
				caseNav =ch;
				int j = 0;
				StringTokenizer stCase = new StringTokenizer(caseNav, ";");
				
				while (stCase.hasMoreElements()){
					String elem =(String) stCase.nextElement();
					Case c = new Case(elem, false, caseTypeNavire(Integer.valueOf(tailleNav)) );
					lstCase[j]=c;	
					j++;
				}				
				break;	
			//boolean indiquant si le navire est coul� = false par d�faut
			case 4:
				couleNav =ch;
				break;
			//nombre de points que vaut le navire si coul� par exemple
			case 5:
				scoreNav =ch;
				break;
			}
		}
		
		n = new Navire(Integer.valueOf(idNav),Integer.valueOf(tailleNav),new ArrayList<Case>(Arrays.asList(lstCase)),Boolean.valueOf(couleNav),Integer.valueOf(scoreNav));
		return n;
	}
	
	/**
	 * Permet de d�terminer la lettre associ�e � la case selon la taille de navire
	 * On peut imaginer par exemple un nom d'image dans la version swing
	 * @param longueur
	 * @return
	 */
	private static String caseTypeNavire(int longueur){
		String ctype="";
		switch(longueur){
		case 1 :
			ctype="V";
			break;
		case 2 :
			ctype="D";
			break;
		case 3 :
			ctype="C";
			break;
		case 4 :
			ctype="K";
			break;
		case 5 :
			ctype="P";
			break;	
		}
		return ctype;
	}
	
	
	
	/**
	 * M�thode main
	 * D�roulement :
	 * 1. on cr�e dans le fichier batailleUn.txt une s�rie de navires pour l'ordinateur par ex
	 * 2. on lit le fichier
	 * 3. on charge tout le fichier dans une Collection
	 * 4. pour chaque chaine de cette collection, on a un navire que l'on cr�e avec la m�thode convertStringToNavire
	 * 5. on ajoute le navire dans le plateau d�sir�
	 * 6. on joue des coups sur le plateau
	 * 7. on affiche le r�sultat des coups
	 * 
	 * 
	 * @param args
	 * @throws BatailleNavaleException 
	 * @throws NumberFormatException 
	 */
	public static void main(String[] args) throws NumberFormatException, BatailleNavaleException {
		
		TestJeu tj = new TestJeu(8,8);
		FileUtil f = FileUtil.getInstance();
		f.setFileName("batailleUn.txt");
		Collection<Object> nav = f.getTableProprietes().values();
		List<Navire> lstNav = new ArrayList<Navire>();
		for (Object o: nav){
			String a = (String)o;
			//System.out.println(a);
			tj.pOrdinateur.ajouterNavire(convertStringToNavire(a));
			
		}
		
		System.out.println(tj.pOrdinateur.getListeNav());
		Navire n = tj.pOrdinateur.jouerCoup(2, 0);
		System.out.println("Le navire est coul� : "+(n!= null? n.isEstCoule() +"  " : " pas touch�"));
		n = tj.pOrdinateur.jouerCoup(2, 4);
		 System.out.println("Le navire est coul� : "+(n!= null? n.isEstCoule() +"  " : " pas touch�"));
		n = tj.pOrdinateur.jouerCoup(2, 1);
		System.out.println("Le navire est coul� : "+(n!= null? n.isEstCoule() +"  " : " pas touch�"));
		
		for (int i=0; i < 8;i++){
			for (int j=0; j < 8; j++){
				boolean b = Boolean.valueOf(tj.pOrdinateur.getCasesTouchees()[i][j]);
				if (b) {
					System.out.println("Navire est touch� en "+i+"_"+j);
				}
			}
			
		}
		for (int i=0; i < 8;i++){
			for (int j=0; j < 8; j++){
				boolean b = Boolean.valueOf(tj.pOrdinateur.getCoupsJoues()[i][j]);
				if (b) {
					System.out.println("Coup est jou� en "+i+"_"+j);
				}
			}
			
		}
		
		
	}

}
