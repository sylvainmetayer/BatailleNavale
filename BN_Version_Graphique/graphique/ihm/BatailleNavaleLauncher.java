package ihm;

import ihm.frames.FrameBatailleNavale;
import ihm.frames.FrameOption;

/**
 * Cette classe permet de lancer la bataille navale
 * 
 * @author Sylvain METAYER - Kevin DESSIMOULIE
 *
 */
public class BatailleNavaleLauncher {

	/**
	 * Point d'entrée de l'application
	 * 
	 * @param args
	 *            {@link String}
	 */
	public static void main(String[] args) {

		new FrameBatailleNavale();
		//new FrameOption();
	}

}
