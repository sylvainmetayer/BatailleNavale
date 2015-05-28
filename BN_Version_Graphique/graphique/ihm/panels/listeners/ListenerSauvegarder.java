package ihm.panels.listeners;

import ihm.composants.JTextAreaBN;
import ihm.panels.PanelJoueur;
import ihm.panels.PanelPrincipal;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;

import outils.Options;

/**
 * Cet écouteur permet de sauvegarder le jeu.
 * 
 * @author Sylvain METAYER - Kevin DESSIMOULIE
 *
 */
public class ListenerSauvegarder implements ActionListener {

	private String nomFichier;
	private PanelJoueur joueur1, joueur2;
	private JTextAreaBN jta_message;

	private PanelPrincipal jpp;

	public ListenerSauvegarder(PanelPrincipal jpp) {
		this.nomFichier = Options.getNamefichier()
				+ Options.getExtensionfichier();

		this.jpp = jpp;

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
					//eraseFile();

					// 1 backup panel joueur1
					jpp.getPanelJoueurUn().save(oos);
					// 2 backup panel joueur2
					jpp.getPanelJoueurDeux().save(oos);
					// 3 backup du texte du jta
					oos.writeObject(PanelPrincipal.jta_message.getText());
					// 4 backup du jeu
					oos.writeObject(jpp.getJeu());

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

	private void eraseFile() {

		PrintWriter pw;
		try {
			pw = new PrintWriter(new BufferedWriter(new FileWriter(nomFichier,
					false)));
			pw.print("");
			pw.close();
		} catch (IOException e) {
			PanelPrincipal.jta_message.append("Erreur de sauvegarde : "
					+ e.getMessage());
		}

	}
}
