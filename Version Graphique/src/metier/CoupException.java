/**
 * 
 */
package metier;

import java.io.Serializable;

/**
 * Classe représentant une exception levée lors de la réalisation d'un coup non
 * autorisé
 * 
 * @author Sylvain METAYER - Kevin DESSIMOULIE
 *
 */
public class CoupException extends Exception implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructeur
	 * 
	 * @param message
	 *            {@link String}
	 */
	public CoupException(String message) {
		super(message);
	}

}