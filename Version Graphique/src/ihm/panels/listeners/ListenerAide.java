package ihm.panels.listeners;

import ihm.frames.FrameAideBatailleNavale;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

/**
 * Classe permettant d'afficher une aide dans une nouvelle {@link JFrame}.
 * 
 * @author Sylvain METAYER - Kevin DESSIMOULIE
 *
 * @see FrameAideBatailleNavale
 */
public class ListenerAide implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		new FrameAideBatailleNavale();
	}
}
