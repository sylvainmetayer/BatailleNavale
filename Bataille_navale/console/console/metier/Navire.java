package console.metier;

import java.util.ArrayList;
import java.util.List;

//TODO
public class Navire implements Comparable<Navire> {

	private List<Case> navire;
	private List<Case> caseTouchees = new ArrayList<Case>();
	private int taille;
	private boolean estCoule;

	@Override
	public int compareTo(Navire arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

}
