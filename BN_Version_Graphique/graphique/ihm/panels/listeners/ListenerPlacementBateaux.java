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

	/**
	 * Valeur possibles :<br>
	 * 1 : placement vertical (vers le bas)<br>
	 * 0 : placement horizontal (vers la droite)
	 */
	private int orientation;

	private int navireNumero;
	private boolean finAjout;

	public ListenerPlacementBateaux(NavireCaracteristique navireDetails,
			PanelPrincipal jpp_principal, PanelJoueur jpj_joueur) {
		this.navireDetails = navireDetails;
		this.jpp_principal = jpp_principal;

		navireValide = true; // on considère que l'utilisateur ne se trompe pas.
		finAjout = false; // on considère que c'est ne pas la fin de l'ajout

		this.jpj_joueur = jpj_joueur;

		// on recupère le plateau du joueur
		jpp_plateau = jpj_joueur.getPanelPlateau();

		// on récupère le numéro du bateau
		navireNumero = navireDetails.getNumeroBateau();

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		orientation = popupChoixOrientation();

		if (orientation == 0) {
			placementBateaux(true, e);
		}

		if (orientation == 1) {
			placementBateaux(false, e);
		}
	}

	private void placementBateaux(boolean isPlacementHorizontal, ActionEvent e) {

		List<Case> caseOccupeParBateau = new ArrayList<Case>();
		int tmp = 0, y, x;
		boolean collision;

		caseDebut = ((BoutonBN) e.getSource());
		x = caseDebut.getCase().getPosx();
		y = caseDebut.getCase().getPosy() - 1;

		if (isPlacementHorizontal)
			tmp = y;
		else
			tmp = x;

		System.out.println(navireDetails.getTaille()); // TODO erase
		for (int i = 0; i < navireDetails.getTaille(); i++) {
			System.out.println(navireValide); // TODO erase
			if (navireValide == true) {

				if (isPlacementHorizontal)
					collision = jpp_plateau.getPlateau().isCollisionPlacement(
							x, tmp);
				else
					collision = jpp_plateau.getPlateau().isCollisionPlacement(
							tmp, x);

				if (collision) {
					PanelPrincipal.jta_message
							.append("Erreur, collision.\nRecommencer svp.");
					navireValide = false;
				} else {

					// on recupère le bouton correspondant
					if (isPlacementHorizontal)
						temporaire = jpp_plateau.getTableauBoutonsBN()[x][tmp];
					else
						temporaire = jpp_plateau.getTableauBoutonsBN()[tmp][x];

					System.out.println(temporaire); // TODO erase

					// on modifie le motif de la case associée
					temporaire.setMotifCaseUniquement(navireDetails.getMotif());

					// Ajout de la case a la liste de case occupee
					caseOccupeParBateau.add(temporaire.getCase());

					// on remplace le bouton modifié par ce dernier
					if (isPlacementHorizontal)
						jpp_plateau.setTableauBoutonsBN(x, tmp, temporaire);
					else
						jpp_plateau.setTableauBoutonsBN(tmp, x, temporaire);

				}

				if (isPlacementHorizontal)
					tmp = y + 1;
				else
					tmp = x + 1;

			}
		}

		if (navireValide == true) {

			// le navire est valide et n'a pas de collision, on l'ajoute
			jpp_plateau.getPlateau().ajouterNavire(
					new Navire(ListenerPlacementBateaux.idNavire, navireDetails
							.getTaille(), caseOccupeParBateau, false,
							navireDetails.getValeurScore()));
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
						NavireCaracteristique.values()[navireNumero], finAjout);

			}

		} else {
			// on remet le listener pour ajouter le même bateau avec un
			// message d'erreurs
			PanelPrincipal.jta_message.append("Merci de rajouter à nouveau le "
					+ navireDetails.getNom());
			jpp_principal.placementBateaux(jpj_joueur, navireDetails, finAjout);
		}

		// on actualise le plateau.
		jpp_plateau.actualisation();
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