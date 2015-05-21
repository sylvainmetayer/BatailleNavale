/**
 * 
 */
package ihm;

import java.awt.Color;

import javax.swing.JTextArea;

/**
 * @author Sylvain METAYER - Kevin DESSIMOULIE
 *
 */
public class JtextAreaBN extends JTextArea {

	// private final int LIMITE = 15;

	// private int compteur;

	private static final long serialVersionUID = 1L;

	public JtextAreaBN() {
		super();
		// compteur = 0;
	}

	@Override
	public void setText(String message) {
		super.setText(message + "\n\n");
		this.setCaretPosition(this.getDocument().getLength());

	}

	@Override
	public void append(String message) {
		// if (compteur > LIMITE) {
		// super.setText("");
		// compteur = 0;
		// }
		// compteur++;

		super.append(message + "\n\n");
		this.setCaretPosition(this.getDocument().getLength());

	}

}
