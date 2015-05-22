/**
 * 
 */
package ihm.composants;

import java.awt.Color;

import javax.swing.JTextArea;

/**
 * @author Sylvain METAYER - Kevin DESSIMOULIE
 *
 */
public class JtextAreaBN extends JTextArea {

	private static final long serialVersionUID = 1L;

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

	public void clearJTextArea() {
		super.setText("");
	}

}
