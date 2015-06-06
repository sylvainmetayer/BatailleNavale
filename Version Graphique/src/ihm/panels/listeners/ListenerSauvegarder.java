package ihm.panels.listeners;

import ihm.panels.PanelPrincipal;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import outils.Options;

/**
 * Cet écouteur permet de sauvegarder le jeu.
 * 
 * @author Sylvain METAYER - Kevin DESSIMOULIE
 *
 */
public class ListenerSauvegarder implements ActionListener {

	private String nomFichier;

	private PanelPrincipal jpp;

	/**
	 * Constructeur
	 * 
	 * @param jpp
	 *            {@link PanelPrincipal}
	 */
	public ListenerSauvegarder(PanelPrincipal jpp) {
		this.nomFichier = Options.getNamefichier()
				+ Options.getExtensionfichier();

		this.jpp = jpp;

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		sauvegarder();
	}

	/**
	 * Méthode permettant de sauvegarder la partie dans un fichier dont le nom
	 * et l'extension se trouve dans le classe {@link Options}
	 */
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

				try {
					PanelPrincipal.jta_message
							.append("Une erreur est survenue lors de la sauvegarde. "
									+ "Vous trouverez ci dessous les causes de cette erreur");
					PanelPrincipal.jta_message.append(ex.getMessage());

				} catch (NullPointerException exception) {
					// Ne rien faire.
				}
			}
		}
	}

}
