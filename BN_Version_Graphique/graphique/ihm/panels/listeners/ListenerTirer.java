package ihm.panels.listeners;

import ihm.composants.BoutonBN;
import ihm.panels.PanelJoueur;
import ihm.panels.PanelPrincipal;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import enums.Motif;

public class ListenerTirer implements ActionListener {

	private PanelPrincipal jpp_principal;
	private PanelJoueur jpj_joueur;
	
	public ListenerTirer(PanelPrincipal jpp_principal, PanelJoueur jpj_joueur) {
		this.jpp_principal = jpp_principal;
		this.jpj_joueur = jpj_joueur;
	}
	@Override
	public void actionPerformed(ActionEvent e) {

		int y, x;
		boolean collision = false;

		x = ((BoutonBN) e.getSource()).getCase().getPosx();
		y = ((BoutonBN) e.getSource()).getCase().getPosy();
		
		collision = jpj_joueur.getPanelPlateau().getPlateau().isCollisionPlacement(x, y);

		if (collision) {
			PanelPrincipal.jta_message.append("Touché !");
			jpj_joueur.getPanelPlateau().getPlateau().getLstCases()[x][y].setMotif(Motif.TOUCHE.toString());
			jpp_principal.repaint();
		} else {
			PanelPrincipal.jta_message.append("Dans l'eau !");
			jpj_joueur.getPanelPlateau().getPlateau().getLstCases()[x][y].setMotif(Motif.COUPJOUE.toString());
			jpp_principal.repaint();
		}
		// on actualise le plateau.
		jpj_joueur.getPanelPlateau().actualisation();
	}

}
