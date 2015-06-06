package console;

/**
 * Classe de levée d'une exception de type BatailleNavaleException
 * 
 * @author Sylvain METAYER - Kevin DESSIMOULIE
 *
 */
public class BatailleNavaleException extends Exception {

	private static final long serialVersionUID = 1L;

	public BatailleNavaleException(String message) {
		super(message);
	}

}
