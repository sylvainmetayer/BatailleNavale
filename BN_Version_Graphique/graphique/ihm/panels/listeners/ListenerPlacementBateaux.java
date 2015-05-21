/**
 * 
 */
package ihm.panels.listeners;

import ihm.composants.BoutonBN;
import ihm.panels.PanelPlateau;
import ihm.panels.PanelPrincipal;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import enums.NavireCaracteristique;
import metier.Case;
import metier.Navire;

/**
 * @author Sylvain METAYER - Kevin DESSIMOULIE
 *
 */
public class ListenerPlacementBateaux implements ActionListener {

	private static int idNavire = 1;

	public static void incrementerIdNavire() {
		ListenerPlacementBateaux.idNavire++;
	}

	private BoutonBN caseDebut, temporaire;
	private NavireCaracteristique navireDetails;
	private PanelPlateau jpp_plateau;

	// 0 = horizontal vers la droite
	// 1 = vertical vers le bas
	int orientation;

	public ListenerPlacementBateaux(NavireCaracteristique navireDetails,
			PanelPlateau jpp_plateau) {
		this.navireDetails = navireDetails;
		this.jpp_plateau = jpp_plateau;
	}

	// TODO gestion erreur x et y.
	// Gestion bateau ajouté à la gauche au lieu de vers la droite.
	@Override
	public void actionPerformed(ActionEvent e) {
		caseDebut = ((BoutonBN) e.getSource());
		orientation = popupChoixOrientation();

		List<Case> caseOccupeParBateau = new ArrayList<Case>();
		int tmp = 0, y, x;
		x = caseDebut.getCase().getPosx();
		y = caseDebut.getCase().getPosy() - 1;

		if (orientation == 0) {
			// HORIZONTAL
			tmp = y;
		}

		if (orientation == 1) {
			// VERTICAL
			tmp = x;
		}
		PanelPrincipal.jta_message.append(caseDebut.toString());
		PanelPrincipal.jta_message.append(Integer.toString(tmp) + ", +1 :"
				+ Integer.toString(tmp + 1));

		if (orientation == 0) {
			// HORIZONTAL
			for (int i = 0; i < navireDetails.getTaille(); i++) {

				if (jpp_plateau.getPlateau().isCollisionPlacement(x, tmp)) {
					PanelPrincipal.jta_message
							.append("Erreur, collision.\nRecommencer svp.");
					// erreur = true;
					// TODO gestion erreur
				} else {

					// on recupère le bouton correspondant
					temporaire = jpp_plateau.getTableauBoutonsBN()[x][tmp];

					// on ajoute la case de ce bouton a la liste de case occupee
					caseOccupeParBateau.add(temporaire.getCase());

					PanelPrincipal.jta_message.append(temporaire.toString());

					// on modifie son texte et le motif de sa case
					temporaire.setText(navireDetails.getMotif());
					// on remplace le bouton modifié par ce dernier
					jpp_plateau.setTableauBoutonsBN(x, tmp, temporaire);

				}

				tmp = y + 1;
			}
			jpp_plateau.getPlateau().ajouterNavire(
					new Navire(ListenerPlacementBateaux.idNavire, navireDetails
							.getTaille(), caseOccupeParBateau, false,
							navireDetails.getValeurScore()));
			ListenerPlacementBateaux.incrementerIdNavire();

		}

		if (orientation == 1) {
			// VERTICAL
			for (int i = 0; i < navireDetails.getTaille(); i++) {

				if (jpp_plateau.getPlateau().isCollisionPlacement(tmp, y)) {
					PanelPrincipal.jta_message
							.append("Erreur, collision.\nRecommencer svp.");
				} else {

					// on recupère le bouton correspondant
					// y+1 règle le problème de décalage de colonne, mais cela
					// n'est pas normal. A fixer TODO
					temporaire = jpp_plateau.getTableauBoutonsBN()[tmp][y];

					// on ajoute la case de ce bouton a la liste de case occupee
					caseOccupeParBateau.add(temporaire.getCase());

					PanelPrincipal.jta_message.append(temporaire.toString());

					// on modifie son texte et le motif de sa case
					temporaire.setText(navireDetails.getMotif());
					// on remplace le bouton modifié par ce dernier
					jpp_plateau.setTableauBoutonsBN(tmp, y, temporaire);

				}

				tmp = x + 1;
			}
			jpp_plateau.getPlateau().ajouterNavire(
					new Navire(ListenerPlacementBateaux.idNavire, navireDetails
							.getTaille(), caseOccupeParBateau, false,
							navireDetails.getValeurScore()));
			ListenerPlacementBateaux.incrementerIdNavire();

		}

	}

	private int popupChoixOrientation() {
		String[] options = new String[] { "Horizontal", "Vertical" };
		int choixPositionnement = JOptionPane.showOptionDialog(null,
				"Choisir l'orientation :", "Précisions..",
				JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null,
				options, options[0]);
		return choixPositionnement;
	}
}