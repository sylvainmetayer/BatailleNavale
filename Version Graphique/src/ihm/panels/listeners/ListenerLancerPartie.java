package ihm.panels.listeners;

import ihm.frames.FrameOption;
import ihm.panels.PanelPrincipal;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Ecouteur pour le demarrage d'une partie <br>
 * 
 * @author Sylvain METAYER - Kevin DESSIMOULIE
 *
 */
public class ListenerLancerPartie implements ActionListener {

	private PanelPrincipal jp;

	/**
	 * Constructeur
	 * 
	 * @param jp
	 *            {@link PanelPrincipal}
	 */
	public ListenerLancerPartie(PanelPrincipal jp) {
		this.jp = jp;

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		new FrameOption(jp);
	}

}