/**
 * 
 */
package ihm.panels.listeners;

import ihm.composants.BoutonBN;
import ihm.panels.PanelJoueur;
import ihm.panels.PanelPlateau;
import ihm.panels.PanelPrincipal;

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

	// on ajoute deux fois chaque navire sur chacun des plateaux.
	private final int AJOUTNAVIRE = NavireCaracteristique.values().length * 2;

	// Cela correspond à l'id d'un navire
	private static int idNavire = 1;
	private static int ajoutNavire = 1;

	public static void incrementerIdNavire() {
		ListenerPlacementBateaux.idNavire++;
	}

	public static void incrementerAjoutNavire() {
		ListenerPlacementBateaux.ajoutNavire++;
	}

	private boolean navireValide;
	private BoutonBN caseDebut, temporaire;
	private NavireCaracteristique navireDetails;

	private PanelPlateau jpp_plateau;
	private PanelPrincipal jpp_principal;
	private PanelJoueur jpj_joueur;

	// 0 = horizontal vers la droite
	// 1 = vertical vers le bas
	private int orientation;

	private int navireNumero;
	private boolean finAjout;

	public ListenerPlacementBateaux(NavireCaracteristique navireDetails,
			PanelPrincipal jpp_principal, PanelJoueur jpj_joueur) {
		this.navireDetails = navireDetails;
		this.jpp_principal = jpp_principal;

		// par défaut, on considère que l'utilisateur ne se trompe pas.
		navireValide = true;

		this.jpj_joueur = jpj_joueur;

		// on recupère le plateau du joueur
		jpp_plateau = jpj_joueur.getPanelPlateau();

		// on récupère le numéro du bateau
		navireNumero = navireDetails.getNumeroBateau();

		finAjout = false;
	}

	// TODO gestion erreur x et y.
	// Gestion bateau ajouté à la gauche au lieu de vers la droite.
	@Override
	public void actionPerformed(ActionEvent e) {

		caseDebut = ((BoutonBN) e.getSource());
		orientation = popupChoixOrientation();

		// initialisation des variables.
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

		if (orientation == 0) {
			// HORIZONTAL
			for (int i = 0; i < navireDetails.getTaille(); i++) {

				if (navireValide == true) {

					if (jpp_plateau.getPlateau().isCollisionPlacement(x, tmp)) {
						PanelPrincipal.jta_message
								.append("Erreur, collision.\nRecommencer svp.");
						navireValide = false;
					} else {

						// on recupère le bouton correspondant
						temporaire = jpp_plateau.getTableauBoutonsBN()[x][tmp];

						// on modifie le motif de la case associée
						temporaire.setMotifCaseUniquement(navireDetails
								.getMotif());

						// Ajout de la case a la liste de case occupee
						caseOccupeParBateau.add(temporaire.getCase());

						// on remplace le bouton modifié par ce dernier
						jpp_plateau.setTableauBoutonsBN(x, tmp, temporaire);

					}

					tmp = y + 1;

				}
			}

			if (navireValide == true) {

				// le navire est valide et n'a pas de collision, on l'ajoute
				jpp_plateau.getPlateau().ajouterNavire(
						new Navire(ListenerPlacementBateaux.idNavire,
								navireDetails.getTaille(), caseOccupeParBateau,
								false, navireDetails.getValeurScore()));
				// on incrémente l'id pour le navire suivant
				ListenerPlacementBateaux.incrementerIdNavire();
			}

			if (navireValide == true) {

				ListenerPlacementBateaux.incrementerAjoutNavire();
				navireNumero++;

				if (ListenerPlacementBateaux.ajoutNavire > AJOUTNAVIRE) {
					// on a fini de placer les bateaux pour les deux joueurs
					jpp_principal.debutPartie();
				} else {
					// on ajoute le navire suivant.
					if (navireNumero >= NavireCaracteristique.values().length) {
						// on vient d'ajouter le dernier navire
						finAjout = true;
						navireNumero--;
						// pour éviter collision avec navireCaractéristiques
					}

					jpp_principal.placementBateaux(jpj_joueur,
							NavireCaracteristique.values()[navireNumero],
							finAjout);

				}

			} else {
				// on remet le listener pour ajouter le même bateau avec un
				// message d'erreurs
				PanelPrincipal.jta_message
						.append("Merci de rajouter à nouveau le "
								+ navireDetails.getNom());
				jpp_principal.placementBateaux(jpj_joueur, navireDetails,
						finAjout);
			}

			// on actualise le plateau.
			jpp_plateau.actualisation();

		}

		if (orientation == 1) {
			// VERTICAL
			for (int i = 0; i < navireDetails.getTaille(); i++) {

				if (navireValide == true) {

					if (jpp_plateau.getPlateau().isCollisionPlacement(tmp, x)) {
						PanelPrincipal.jta_message
								.append("Erreur, collision.\nRecommencer svp.");
						navireValide = false;
					} else {

						// on recupère le bouton correspondant
						temporaire = jpp_plateau.getTableauBoutonsBN()[tmp][x];

						// on modifie le motif de la case associée
						temporaire.setMotifCaseUniquement(navireDetails
								.getMotif());

						// Ajout de la case a la liste de case occupee
						caseOccupeParBateau.add(temporaire.getCase());

						// on remplace le bouton modifié par ce dernier
						jpp_plateau.setTableauBoutonsBN(x, tmp, temporaire);

					}

					tmp = x + 1;

				}
			}

			if (navireValide == true) {

				// le navire est valide et n'a pas de collision, on l'ajoute
				jpp_plateau.getPlateau().ajouterNavire(
						new Navire(ListenerPlacementBateaux.idNavire,
								navireDetails.getTaille(), caseOccupeParBateau,
								false, navireDetails.getValeurScore()));
				// on incrémente l'id pour le navire suivant
				ListenerPlacementBateaux.incrementerIdNavire();
			}

			if (navireValide == true) {

				ListenerPlacementBateaux.incrementerAjoutNavire();
				navireNumero++;

				if (ListenerPlacementBateaux.ajoutNavire > AJOUTNAVIRE) {
					// on a fini de placer les bateaux pour les deux joueurs
					jpp_principal.debutPartie();
				} else {
					// on ajoute le navire suivant.
					if (navireNumero >= NavireCaracteristique.values().length) {
						// on vient d'ajouter le dernier navire
						finAjout = true;
						navireNumero--;
						// pour éviter collision avec navireCaractéristiques
					}

					jpp_principal.placementBateaux(jpj_joueur,
							NavireCaracteristique.values()[navireNumero],
							finAjout);

				}

			} else {
				// on remet le listener pour ajouter le même bateau avec un
				// message d'erreurs
				PanelPrincipal.jta_message
						.append("Merci de rajouter à nouveau le "
								+ navireDetails.getNom());
				jpp_principal.placementBateaux(jpj_joueur, navireDetails,
						finAjout);
			}

			// on actualise le plateau.
			jpp_plateau.actualisation();

		}

		/*
		 * if (orientation == 1) { // VERTICAL for (int i = 0; i <
		 * navireDetails.getTaille(); i++) {
		 * 
		 * if (jpp_plateau.getPlateau().isCollisionPlacement(tmp, y)) {
		 * PanelPrincipal.jta_message
		 * .append("Erreur, collision.\nRecommencer svp."); } else {
		 * 
		 * // on recupère le bouton correspondant // y+1 règle le problème de
		 * décalage de colonne, mais cela // n'est pas normal. A fixer TODO
		 * temporaire = jpp_plateau.getTableauBoutonsBN()[tmp][y];
		 * 
		 * // on ajoute la case de ce bouton a la liste de case occupee
		 * caseOccupeParBateau.add(temporaire.getCase());
		 * 
		 * // on modifie son texte et le motif de sa case
		 * temporaire.setText(navireDetails.getMotif()); // on remplace le
		 * bouton modifié par ce dernier jpp_plateau.setTableauBoutonsBN(tmp, y,
		 * temporaire);
		 * 
		 * }
		 * 
		 * tmp = x + 1; } jpp_plateau.getPlateau().ajouterNavire( new
		 * Navire(ListenerPlacementBateaux.idNavire, navireDetails .getTaille(),
		 * caseOccupeParBateau, false, navireDetails.getValeurScore()));
		 * ListenerPlacementBateaux.incrementerIdNavire();
		 * 
		 * }
		 */

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