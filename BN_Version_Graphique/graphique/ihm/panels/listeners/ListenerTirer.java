package ihm.panels.listeners;

import ihm.composants.BoutonBN;
import ihm.panels.PanelJoueur;
import ihm.panels.PanelPrincipal;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import metier.Case;
import metier.Navire;
import enums.Motif;
import enums.NavireCaracteristique;

public class ListenerTirer implements ActionListener {

	private PanelPrincipal jpp_principal;
	private PanelJoueur jpj_joueur;

	public ListenerTirer(PanelPrincipal jpp_principal, PanelJoueur jpj_joueur) {
		this.jpp_principal = jpp_principal;
		this.jpj_joueur = jpj_joueur;
	}

	@SuppressWarnings("deprecation")
	@Override
	public void actionPerformed(ActionEvent e) {

		List<Case> caseOccupees = jpj_joueur.getPanelPlateau().getPlateau()
				.getCasesOccupees();

		int y, x;
		// boolean collision = false;
		Case caseVisee;

		caseVisee = ((BoutonBN) e.getSource()).getCase();
		x = caseVisee.getPosx();
		y = caseVisee.getPosy();
		Navire navire;
		NavireCaracteristique caracteristique;

		navire = jpj_joueur.getPanelPlateau().getPlateau().jouerCoup(x, y);

		System.out.println(navire);
		if (navire == null) {
			System.out.println("eau");
			// coup dans l'eau
			PanelPrincipal.jta_message.append("Dans l'eau !");
			jpj_joueur.getPanelPlateau().getPlateau().getLstCases()[x][y]
					.setMotif(Motif.COUPJOUE.toString());
			jpj_joueur.getPanelPlateau().getTableauBoutonsBN()[x][y]
					.setEnabled(false);
			jpj_joueur.getPanelPlateau().actualisation();
			jpj_joueur.repaint();
		} else {
			caracteristique = navire.getCaracteristiqueByTaille(navire
					.getTaille());

			jpj_joueur.getPanelPlateau().getTableauBoutonsBN()[x][y]
					.setMotifCaseEtPlateau(Motif.TOUCHE.toString());

			caseOccupees.add(caseVisee);

			if (navire.isEstCoule()) {
				// coulé
				navire.setEstCoule(true);

				for (Case c : navire.getCases()) {
					c.setEstTouche(true);
					c.setMotif(Motif.COULE.toString());
					jpj_joueur.getPanelPlateau().getTableauBoutonsBN()[c
							.getPosx()][c.getPosy()].setEnabled(false);
				}
				PanelPrincipal.jta_message.append(caracteristique.getNom()
						+ "est touché coulé !");
			} else {
				// touché
				PanelPrincipal.jta_message.append(caracteristique.getNom()
						+ " a été touché !");
				jpj_joueur.getPanelPlateau().getTableauBoutonsBN()[x][y]
						.setEnabled(false);
			}

		}
		jpj_joueur.getPanelPlateau().actualisation();
		jpj_joueur.repaint();
		jpp_principal.repaint();
		jpp_principal.jouerCoup(
				jpp_principal.getMonPanelJoueur(jpj_joueur, false),
				jpp_principal.getMonPanelJoueur(jpj_joueur, true));

	}

}
