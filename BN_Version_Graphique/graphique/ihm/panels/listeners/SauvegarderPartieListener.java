package ihm.panels.listeners;

import ihm.composants.JTextAreaBN;
import ihm.panels.PanelJoueur;
import ihm.panels.PanelPrincipal;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import outils.Options;

public class SauvegarderPartieListener implements ActionListener {

	private String nomFichier;
	private PanelJoueur joueur1, joueur2;
	private JTextAreaBN jta_message;

	public SauvegarderPartieListener(JTextAreaBN jta_message,
			PanelJoueur joueur2, PanelJoueur joueur1) {
		this.nomFichier = Options.getNamefichier()
				+ Options.getExtensionfichier();

		this.joueur1 = joueur1;
		this.joueur2 = joueur2;
		this.jta_message = jta_message;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		sauvegarder();
	}

	private void sauvegarder() {
		ObjectOutputStream oos = null;

		// Récupérer un tableau d'objet contenant le jeu, le plateaux, le nom du
		// joueur.
		// fois deux

		try {
			final FileOutputStream fichier = new FileOutputStream(nomFichier);
			oos = new ObjectOutputStream(fichier);
		} catch (final java.io.IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (oos != null) {
					oos.writeObject(joueur1);
					oos.writeObject(joueur2);
					oos.writeObject(jta_message);
					oos.flush();
					oos.close();
					try {
						PanelPrincipal.jta_message
								.append("Sauvegarde réussie !");
					} catch (NullPointerException e) {

					}

				}
			} catch (final IOException ex) {
				PanelPrincipal.jta_message
						.append("Une erreur est survenue lors de la sauvegarde. "
								+ "Vous trouverez ci dessous les causes de cette erreur");
				PanelPrincipal.jta_message.append(ex.getMessage());
				ex.printStackTrace();
			}
		}
	}
}
