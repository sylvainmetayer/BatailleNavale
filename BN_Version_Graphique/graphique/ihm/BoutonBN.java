/**
 * 
 */
package ihm;

import javax.swing.JButton;

/**
 * @author Sylvain - Kevin
 *
 */
public class BoutonBN extends JButton {

	private static final long serialVersionUID = 1L;
	private Case c;

	public Case getC() {
		return c;
	}

	public void setC(Case c) {
		this.c = c;
	}

	public BoutonBN(Case c, String s) {
		super(s);
		this.c = c;
	}

}
