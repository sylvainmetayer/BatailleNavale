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

import enums.MotifsDivers;
import enums.NavireCaracteristique;
import metier.Case;
import metier.Navire;

/**
 * Cette classe permet l'ajout de navires lors d'un clic sur un {@link BoutonBN}
 * 
 * @author Sylvain METAYER - Kevin DESSIMOULIE
 *
 */
public class ListenerPlacementBateaux implements ActionListener {

	// on ajoute les navires sur chaque plateau.
	private final int AJOUTNAVIRE = NavireCaracteristique.values().length * 2;

	private static int idNavire = 1;
	private static int ajoutNavire = 1;

	/**
	 * Permet d'incrémenter l'id d'un navire, afin d'éviter qu'un navire n'ai
	 * deux fois le meme id
	 */
	public static void incrementerIdNavire() {
		ListenerPlacementBateaux.idNavire++;
	}

	/**
	 * Permet d'incrémenter le nombre de navire déjà ajoutés
	 */
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

	/**
	 * Constructeur
	 * 
	 * @param navireDetails
	 *            {@link NavireCaracteristique}
	 * @param jpp_principal
	 *            {@link PanelPrincipal}
	 * @param jpj_joueur
	 *            {@link PanelJoueur}
	 */
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
		navireNumero = navireDetails.getNumeroNavire();

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

	/**
	 * Méthode permettant le placement d'un bateau, en controlant qu'il n'y a
	 * pas de collision
	 * 
	 * @param isPlacementHorizontal
	 *            {@link Boolean}
	 * @param e
	 *            {@link ActionEvent}
	 */
	private void placementBateaux(boolean isPlacementHorizontal, ActionEvent e) {

		List<BoutonBN> caseBoutons = new ArrayList<BoutonBN>();
		List<Case> caseOccupeParBateau = new ArrayList<Case>();
		int tmp = 0, y, x;
		// boolean collision = false;

		caseDebut = ((BoutonBN) e.getSource()); // sert uniquement à récupérer
												// les coordonnées de départ
		x = caseDebut.getCase().getPosx();
		y = caseDebut.getCase().getPosy();

		if (isPlacementHorizontal)
			tmp = y;
		else
			tmp = x;

		// Cette boucle permet de récupérer la liste de boutons à ajouter
		for (int i = 0; i < navireDetails.getTaille(); i++) {
			// System.out.println("Je suis passe " + (i + 1) + "fois");
			if (navireValide == true) {

				try {
					if (isPlacementHorizontal)
						temporaire = jpp_plateau.getTableauBoutonsBN()[x][tmp];
					else
						temporaire = jpp_plateau.getTableauBoutonsBN()[tmp][y];

					// Test supplémentaire, au cas ou...
					if (temporaire.getCase().getMotif() != MotifsDivers.EAU.getMotif()) {
						// Dans ce cas, cela veut dire que la case est déjà
						// occupée. On empeche donc l'ajout du navire
						navireValide = false;
					}

				} catch (ArrayIndexOutOfBoundsException exception) {
					navireValide = false;
					// une erreur est levée, on n'ajoute pas le navire.
				}

				caseBoutons.add(temporaire);

			}
			tmp++;
		}

		// Permet d'ajouter le navire à la liste de navire
		if (navireValide == true) {
			// le navire est valide et n'a pas de collision, on l'ajoute

			for (BoutonBN b : caseBoutons) {
				caseOccupeParBateau.add(b.getCase());
				// set du motif ici pour éviter levée d'exception
				b.setMotifCaseUniquement(navireDetails.getMotif());
			}

			jpp_plateau.getPlateau().ajouterNavire(
					new Navire(ListenerPlacementBateaux.idNavire, navireDetails
							.getTaille(), caseOccupeParBateau, false,
							navireDetails.getValeurScore()));
			ListenerPlacementBateaux.incrementerIdNavire();

		}

		// Ajout du navire suivant
		if (navireValide == true) {

			ListenerPlacementBateaux.incrementerAjoutNavire();
			navireNumero++;

			if (navireNumero >= NavireCaracteristique.values().length) {
				// on vient d'ajouter le dernier navire
				finAjout = true;
				navireNumero--;
				// pour éviter collision avec navireCaractéristiques
			}

			if (ListenerPlacementBateaux.ajoutNavire > AJOUTNAVIRE) {
				// placement fini pour les deux joueurs
				jpp_principal.debutPartie();
			} else {

				jpp_principal.placementBateaux(jpj_joueur,
						NavireCaracteristique.values()[navireNumero], finAjout);

			}
		} else {
			// listener pour ajouter le même bateau et un message d'erreur

			String message = "Une collision a été détectée lors du placement.\n"
					+ "Merci de rajouter à nouveau le "
					+ navireDetails.getNom();
			PanelPrincipal.jta_message.append(message);
			jpp_principal.placementBateaux(jpj_joueur, navireDetails, finAjout);
		}

		// on actualise le plateau.
		jpp_plateau.actualisation();
	}

	/**
	 * Ceette méthode permet de choisir l'orientation du navire au travers d'une
	 * {@link JOptionPane} <br>
	 * Elle retourne 0 dans le cas ou le placement est horizontal et 1 dans le
	 * cas ou il est vertical. <br>
	 * <b>La gestion d'annulation de la popup n'est pas gérée.<b>
	 * 
	 * @return {@link Integer}
	 */
	private int popupChoixOrientation() {
		String[] options = new String[] { "Horizontal", "Vertical" };
		int choixPositionnement = JOptionPane.showOptionDialog(null,
				"Choisir l'orientation :", "Précisions..",
				JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null,
				options, options[0]);
		return choixPositionnement;
	}
}