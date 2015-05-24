/**
 * 
 */
package metier;

/**
 * Classe représentant une exception levée lors d'une erreur durant le jeu
 * 
 * @author Sylvain METAYER - Kevin DESSIMOULIE
 *
 */
public class BatailleNavaleException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructeur
	 * 
	 * @param message
	 *            {@link String}
	 */
	public BatailleNavaleException(String message) {
		super(message);
	}

}
