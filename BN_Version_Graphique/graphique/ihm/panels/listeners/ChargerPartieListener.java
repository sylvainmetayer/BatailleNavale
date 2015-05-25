package ihm.panels.listeners;

import ihm.composants.JTextAreaBN;
import ihm.panels.PanelJoueur;
import ihm.panels.PanelPrincipal;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * Listener permettant de charger la partie lors du clic sur le bouton
 * {@link PanelPrincipal#jb_chargerPartie}
 * 
 * @author Sylvain METAYER - Kevin DESSIMOULIE
 *
 */
@SuppressWarnings("unused")
public class ChargerPartieListener implements ActionListener {
	private final static String NAME = "backup";
	private final static String EXTENSION = ".data";

	private String nomFichier;
	private PanelJoueur joueur1, joueur2;
	private JTextAreaBN jta_message;

	private PanelPrincipal jpp;

	public ChargerPartieListener(PanelPrincipal jpp) {
		this.nomFichier = NAME + EXTENSION;

		this.joueur1 = null;
		this.joueur2 = null;
		this.jta_message = null;
		this.jpp = jpp;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		chargerPartie();

	}

	private void chargerPartie() {

		File f = new File(nomFichier);
		if (f.exists()) {
			try {

				FileInputStream fis = new FileInputStream(nomFichier);
				ObjectInputStream ois = new ObjectInputStream(fis);

				try {
					joueur1 = (PanelJoueur) ois.readObject();
					jta_message = (JTextAreaBN) ois.readObject();
					joueur2 = (PanelJoueur) ois.readObject();
					// TODO appel méthode chargerPartie de PanelPrincipal
				} finally {

					try {
						ois.close();
					} finally {
						fis.close();
					}
				}

			} catch (IOException ioe) {
				ioe.printStackTrace();
			} catch (ClassNotFoundException cnfe) {
				cnfe.printStackTrace();
			}
			// PanelPrincipal.jta_message.append("Chargement réussi avec succès.");
		} else {
			System.out
					.print("Une erreur s'est produite durant le chargement, car le fichier de sauvegarde n'existe pas."
							+ " Merci de d'abord créer une sauvegarde.\n\n");
		}

	}
}