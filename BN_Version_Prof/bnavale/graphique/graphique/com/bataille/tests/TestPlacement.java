package graphique.com.bataille.tests;

public class TestPlacement {
	String[][] lstCases = new String[8][8];
	public TestPlacement(){
		generer();
		afficher();
		//
		lstCases[6][3]= " X ";
		System.out.println(isCaseOccupee(6, 3));
		afficher();
		//
	}
	
	private boolean isCaseOccupee(int x, int y){
		return (lstCases[x][y] != null && ! lstCases[x][y].equals(" O "));		
	}
	
	
	
	private void generer(){
		for (int i = 0; i< 8 ; i++){
			for (int j = 0; j< 8 ; j++){			
				lstCases[i][j] =" ~ ";
			}
			
		}
	}
	
	private void afficher(){
		String ligne ="";
		for (int i = 0; i< 8 ; i++){
			for (int j = 0; j< 8 ; j++){			
				ligne=ligne+ lstCases[i][j];
			}
			System.out.println(ligne);
			ligne ="";
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new TestPlacement();

	}

}
