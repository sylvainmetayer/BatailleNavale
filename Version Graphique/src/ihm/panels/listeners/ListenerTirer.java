package ihm.panels.listeners;

import ihm.composants.BoutonBN;
import ihm.panels.PanelJoueur;
import ihm.panels.PanelPrincipal;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import outils.MotifsDivers;
import outils.NavireCaracteristique;
import metier.Case;
import metier.Navire;

/**
 * Cette classe permet de gérer la phase de tir d'un joueur.
 * 
 * @author Sylvain METAYER - Kevin DESSIMOULIE
 *
 */
public class ListenerTirer implements ActionListener {

	private PanelPrincipal jpp_principal;
	private PanelJoueur jpj_joueur;

	/**
	 * Constructeur
	 * 
	 * @param jpp_principal
	 *            {@link PanelPrincipal}
	 * @param jpj_joueur
	 *            {@link PanelJoueur}
	 */
	public ListenerTirer(PanelPrincipal jpp_principal, PanelJoueur jpj_joueur) {
		this.jpp_principal = jpp_principal;
		this.jpj_joueur = jpj_joueur;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		List<Case> caseOccupees = jpj_joueur.getPanelPlateau().getPlateau()
				.getCasesOccupees();

		int y, x;
		Case caseVisee;

		caseVisee = ((BoutonBN) e.getSource()).getCase();
		x = caseVisee.getPosx();
		y = caseVisee.getPosy();
		Navire navire;
		NavireCaracteristique caracteristique;
		String nomJoueurAttaquant = jpp_principal.getMonPanelJoueur(jpj_joueur,
				false).getNomJoueur();
		String message;

		navire = jpj_joueur.getPanelPlateau().getPlateau().jouerCoup(x, y);

		if (navire == null) {
			// coup dans l'eau
			message = "Résultat du tour de " + nomJoueurAttaquant
					+ " : Dans l'eau !";
			PanelPrincipal.jta_message.append(message);
			jpj_joueur.getPanelPlateau().getPlateau().getLstCases()[x][y]
					.setMotif(MotifsDivers.COUPJOUE.getMotif());
			jpj_joueur.getPanelPlateau().getTableauBoutonsBN()[x][y]
					.setEnabled(false);
			jpj_joueur.getPanelPlateau().actualisation();
			jpj_joueur.getPanelPlateau().getPlateau().setUnCoupJoue(x, y, true);
			jpj_joueur.repaint();
		} else {

			caracteristique = NavireCaracteristique
					.getCaracteristiqueByTaille(navire.getTaille());

			jpj_joueur.getPanelPlateau().getTableauBoutonsBN()[x][y]
					.setMotifCaseEtPlateau(MotifsDivers.TOUCHE.getMotif());

			caseOccupees.add(caseVisee);

			if (navire.isEstCoule()) {
				// coulé
				navire.setEstCoule(true);

				for (Case c : navire.getCases()) {
					c.setEstTouche(true);
					c.setMotif(MotifsDivers.COULE.getMotif());
					jpj_joueur.getPanelPlateau().getTableauBoutonsBN()[c
							.getPosx()][c.getPosy()].setEnabled(false);
				}
				message = "Résultat du tour de " + nomJoueurAttaquant + " : "
						+ caracteristique.getNom() + " est touché coulé !";
				PanelPrincipal.jta_message.append(message);
			} else {
				// touché
				message = "Résultat du tour de " + nomJoueurAttaquant + " : "
						+ caracteristique.getNom() + " a été touché !";
				PanelPrincipal.jta_message.append(message);
				jpj_joueur.getPanelPlateau().getTableauBoutonsBN()[x][y]
						.setEnabled(false);
			}

		}

		jpj_joueur.actualisation();
		jpp_principal.repaint();

		// Eviter le passage automatique après le tir. Commenté car très
		// dérangeant durant les tests.
		// try {
		// Thread.sleep(800);
		// } catch (InterruptedException e1) {
		// }

		// On joue le coup suivant en inversant les roles
		jpp_principal.jouerCoup(
				jpp_principal.getMonPanelJoueur(jpj_joueur, false),
				jpp_principal.getMonPanelJoueur(jpj_joueur, true));

	}

}
