/**
 * 
 */
package ihm.composants;

import java.awt.Color;

import javax.swing.JTextArea;

/**
 * Cette classe étend le comportement d'un {@link JTextArea} afin de faire des
 * retours à la ligne automatique
 * 
 * @author Sylvain METAYER - Kevin DESSIMOULIE
 *
 */
public class JtextAreaBN extends JTextArea {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructeur
	 */
	public JtextAreaBN() {
		super();
	}

	@Override
	public void setText(String message) {
		super.setText(message + "\n\n");
		this.setCaretPosition(this.getDocument().getLength());

	}

	@Override
	public void append(String message) {

		super.append(message + "\n\n");
		this.setCaretPosition(this.getDocument().getLength());

	}

	/**
	 * Efface tout le texte présent dans l'objet
	 */
	public void clearJTextArea() {
		super.setText("");
	}

}
