package ihm.panels.listeners;

import ihm.composants.JtextAreaBN;
import ihm.panels.PanelJoueur;
import ihm.panels.PanelPrincipal;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class SauvegarderPartieListener implements ActionListener {

	private final static String NAME = "backup";
	private final static String EXTENSION = ".data";

	private String nomFichier;
	private PanelJoueur joueur1, joueur2;
	private JtextAreaBN jta_message;

	public SauvegarderPartieListener(JtextAreaBN jta_message,
			PanelJoueur joueur2, PanelJoueur joueur1) {
		this.nomFichier = NAME + EXTENSION;

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
					PanelPrincipal.jta_message.append("Sauvegarde réussie !");
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
