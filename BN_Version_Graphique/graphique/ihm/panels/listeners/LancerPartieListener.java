package ihm.panels.listeners;

import ihm.panels.PanelPrincipal;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 * Ecouteur pour le bouton {@link PanelPrincipal#jb_commencerPartie} <br>
 * 
 * @author Sylvain METAYER - Kevin DESSIMOULIE
 *
 */
public class LancerPartieListener implements ActionListener {

	private PanelPrincipal jp;

	public LancerPartieListener(PanelPrincipal jp) {
		this.jp = jp;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		jp.removeAll();
		jp.revalidate();
		jp.repaint();

		demandePrenomJoueur(true);
		demandePrenomJoueur(false);
		jp.initGame();
	}

	/**
	 * Permet de demander le prenom d'un joueur et l'affecte à la variable de
	 * {@link PanelPrincipal} correspondante selon qu'il s'agit du joueur 1 ou 2
	 * 
	 * @param joueur1
	 *            {@link Boolean}
	 */
	private void demandePrenomJoueur(boolean joueur1) {
		Object[] message = new Object[2];

		String nom = "";

		if (joueur1)
			message[0] = "Prénom du joueur 1 :";
		else
			message[0] = "Prénom du joueur 2 :";

		message[1] = new JTextField();
		if (joueur1)
			((JTextField) message[1]).setText(jp.DEFAULTJOUEURUN);
		String option[] = { "OK" };

		int result = JOptionPane.showOptionDialog(null, message,
				"Renseignements", JOptionPane.OK_CANCEL_OPTION,
				JOptionPane.QUESTION_MESSAGE, null, option, message[1]);

		if (result == JOptionPane.OK_OPTION) {
			nom = ((JTextField) message[1]).getText();
		}

		if (nom.isEmpty()) {
			if (joueur1)
				jp.setNomJoueurUn(jp.DEFAULTJOUEURUN);
			else
				jp.setNomJoueurDeux(jp.DEFAULTJOUEURDEUX);
		} else {
			if (joueur1)
				jp.setNomJoueurUn(nom);
			else
				jp.setNomJoueurDeux(nom);
		}

	}
}