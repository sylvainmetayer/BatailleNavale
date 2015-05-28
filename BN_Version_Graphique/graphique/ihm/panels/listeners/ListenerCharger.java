package ihm.panels.listeners;

import ihm.panels.PanelJoueur;
import ihm.panels.PanelPrincipal;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InvalidClassException;
import java.io.ObjectInputStream;

import javax.swing.JOptionPane;

import metier.Jeu;
import outils.Options;

/**
 * Listener permettant de charger la partie <br>
 * 
 * Non fonctionnelle
 * 
 * @author Sylvain METAYER - Kevin DESSIMOULIE
 *
 */
public class ListenerCharger implements ActionListener {

	private String nomFichier;

	private PanelPrincipal jpp;

	/**
	 * Constructeur
	 * 
	 * @param jpp
	 *            {@link PanelPrincipal}
	 */
	public ListenerCharger(PanelPrincipal jpp) {
		this.nomFichier = Options.getNamefichier()
				+ Options.getExtensionfichier();

		this.jpp = jpp;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		boolean chargementConfirmeByUser;

		chargementConfirmeByUser = Options
				.questionOuiNon(
						"Voulez vous vraiment charger une partie ?\nCela effacera toute partie en cours !",
						"Attention !");

		if (chargementConfirmeByUser)
			chargerPartie();

	}

	/**
	 * Méthode permettant de charger la partie à partir d'un fichier dont les
	 * caractéristiques sont spécifiés dans {@link Options}
	 */
	private void chargerPartie() {

		File f = new File(nomFichier);
		if (f.exists()) {
			try {

				FileInputStream fis = new FileInputStream(nomFichier);
				ObjectInputStream ois = new ObjectInputStream(fis);

				try {
					PanelJoueur panelJoueur1 = (PanelJoueur) ois.readObject();
					PanelJoueur panelJoueur2 = (PanelJoueur) ois.readObject();
					String texteJTA = (String) ois.readObject();
					Jeu jeu = (Jeu) ois.readObject();

					jpp.chargerPartie(panelJoueur1, panelJoueur2, jeu, texteJTA);

				} catch (InvalidClassException e) {
					String message = "Vous avez tenté de charger un fichier dont la sauvegarde à été effectuée "
							+ "sur un autre ordinateur.\nSuite à une méthode de sauvegarde spécifique de notre part"
							+ ",\nil nous est impossible de restaurer les fichiers "
							+ "provenant d'une version différente de leur sauvegarde"
							+ ".\nVeuillez nous excuser pour la gêne occasionée.";
					JOptionPane.showMessageDialog(null, message,
							"Nous sommes désolés..", JOptionPane.ERROR_MESSAGE);
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
		} else {
			String message = "Une erreur s'est produite durant le chargement, car le fichier de sauvegarde n'existe pas."
					+ " Merci de d'abord créer une sauvegarde.";
			JOptionPane.showMessageDialog(null, message, "Oups !",
					JOptionPane.ERROR_MESSAGE);
		}

	}
}