package console;

/**
 * Classe représentant une exception levée lors de la réalisation d'un coup non
 * autorisé
 * 
 * @author Sylvain METAYER - Kevin DESSIMOULIE
 *
 */
public class CoordonneeException extends Exception {

	private static final long serialVersionUID = 1L;

	public CoordonneeException(String message) {
		super(message);
	}

}