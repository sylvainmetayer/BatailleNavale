/**
 * 
 */
package ihm;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.Scope;

/**
 * @author Sylvain - Kevin
 *
 */
public class PanelPrincipal extends JPanel {

	private static final long serialVersionUID = 1L;

	private final String NAMEORDI = "Ordinateur";
	private final String DEFAULTNAME = "Joueur";
	private final int TAILLEGRILLE = 8;

	// Paramètres du jeu
	private Jeu jeu;
	private String nomJoueur = DEFAULTNAME;

	// = this pour plus de facilité d'accès vis a vis des classe interne membres
	private final JPanel panelPrincipal = this;

	// Déclarations composants
	private JButton jb_commencerPartie;
	private JButton jb_quitterPartie;
	private JButton jb_chargerPartie;
	private JLabel jl_menuPrincipal;

	// pour pouvoir éditer le contenu à partir de tous les panels
	protected static JtextAreaBN jta_message;
	private JScrollPane scroller;

	// Déclarations Panels
	private JPanel jp_panelCentre;
	private PanelJoueur joueur1;
	private PanelJoueur joueur2;

	/**
	 * Constructeur, qui va afficher le menu et permettre de charger,sauvegarder
	 * ou lancer une partie.
	 */
	public PanelPrincipal() {
		// instanciations composants
		jl_menuPrincipal = new JLabel("Bataille Navale - Le jeu !");
		jl_menuPrincipal.setHorizontalAlignment(JLabel.CENTER);
		jb_commencerPartie = new JButton("Commencer une nouvelle partie");
		jb_quitterPartie = new JButton("Quitter");
		jb_chargerPartie = new JButton("Charger une partie");

		// instanciations panels
		panelPrincipal.setLayout(new BorderLayout());
		jp_panelCentre = new JPanel();
		jp_panelCentre.setLayout(new GridLayout(2, 2));

		// ajouts des composants dans les panels secondaires.
		jp_panelCentre.add(jb_commencerPartie, 0);
		jp_panelCentre.add(jb_chargerPartie, 1);
		jp_panelCentre.add(jb_quitterPartie, 2);

		// ajouts des composants dans le panel principal
		panelPrincipal.add(jl_menuPrincipal, BorderLayout.NORTH);
		panelPrincipal.add(jp_panelCentre, BorderLayout.CENTER);

		// ajouts des ecouteurs.
		jb_quitterPartie.addActionListener(new QuitterListener());
		jb_commencerPartie.addActionListener(new LancerPartieListener());
		// TODO ajout charger/sauvegarder
	}

	/**
	 * Méthode qui lance une nouvelle partie. <br>
	 * Pour cela, on va commencer par creer un jeu, ainsi que deux panels pour
	 * les deux joueurs. <br>
	 * Ensuite, on effectue le placement des bateaux.
	 */
	private void StartGame() {
		try {
			jeu = new Jeu(TAILLEGRILLE, TAILLEGRILLE, nomJoueur);
			// On crée les deux panels de jeu.
			joueur1 = new PanelJoueur(nomJoueur, jeu, jeu.getPlateauJoueurUn());
			joueur2 = new PanelJoueur(NAMEORDI, jeu, jeu.getPlateauJoueurDeux());
			repaint();
		} catch (CoupException e) {
			e.printStackTrace();
		}

		PanelPrincipal.jta_message = new JtextAreaBN();
		PanelPrincipal.jta_message.setEditable(false);
		// retour à la ligne
		PanelPrincipal.jta_message.setLineWrap(true);
		PanelPrincipal.jta_message.setWrapStyleWord(true);
		PanelPrincipal.jta_message.setText("Historique :");
		PanelPrincipal.jta_message.setText("Début du jeu..");

		// scrollbar pour le jtextarea
		scroller = new JScrollPane(jta_message);

		placementBateaux(joueur1);

		panelPrincipal.setLayout(new BorderLayout());

		panelPrincipal.add(joueur1, BorderLayout.WEST);
		panelPrincipal.add(joueur2, BorderLayout.EAST);

		panelPrincipal.add(scroller, BorderLayout.CENTER);
		// panelPrincipal.add(PanelPrincipal.jta_message, BorderLayout.CENTER);
		super.revalidate();
		super.repaint();

	}

	/**
	 * Méthode pour placer les bateaux d'un joueur
	 * 
	 * @param jpj_joueur
	 */
	private void placementBateaux(PanelJoueur jpj_joueur) {
		PanelPrincipal.jta_message.append("Placement des bateaux de "
				+ jpj_joueur.getNomJoueur());
		PanelPrincipal.jta_message.append("Le placement des bateaux se fait"
				+ " horizontalement vers la droite"
				+ " et verticalement vers le bas.");

		// blocage grille adverse
		getMonPanelJoueur(jpj_joueur, false).setEtatGrille(false);
		getMonPanelJoueur(jpj_joueur, false).setMessage("Inactif..");

		// TODO idée de généralisation à faire pour placer plusieurs bateaux
		// test pour afficher 1 bateau de 2 cases
		jpj_joueur
				.setPlateauListener(new ListenerPlacementBateaux(
						NavireCaracteristique.NAVIRESIZE2, jpj_joueur
								.getPanelPlateau()));
	}

	/**
	 * Permet de recupérer n'importe quel panel de joueur. <br>
	 * Dans le cas ou monPanel est vrai, on retourne le panelJoueur du joueur
	 * passé en paramètre. <br>
	 * Sinon, on retourne celui du joueur adverse
	 * 
	 * @param jp
	 *            {@link PanelJoueur}
	 * @param monPanel
	 *            {@link Boolean}
	 * @return {@link PanelJoueur}
	 */
	public PanelJoueur getMonPanelJoueur(PanelJoueur jp, boolean monPanel) {
		if (jp.equals(joueur1))
			if (monPanel)
				return joueur1;
			else
				return joueur2;
		if (jp.equals(joueur2))
			if (monPanel)
				return joueur2;
			else
				return joueur1;
		return null;
	}

	/**
	 * Ecouteur pour le bouton {@link PanelPrincipal#jb_commencerPartie} <br>
	 * 
	 * @author Sylvain - Kevin
	 *
	 */
	public class LancerPartieListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			panelPrincipal.removeAll();
			panelPrincipal.revalidate();
			panelPrincipal.repaint();

			// Demande du prenom avec un JOptionPane
			Object[] message = new Object[2];
			message[0] = "Votre prénom";
			message[1] = new JTextField();
			String option[] = { "OK" };

			int result = JOptionPane.showOptionDialog(null, message,
					"Renseignements", JOptionPane.OK_CANCEL_OPTION,
					JOptionPane.QUESTION_MESSAGE, null, option, message[1]);

			if (result == JOptionPane.OK_OPTION) {
				nomJoueur = ((JTextField) message[1]).getText();
			}

			if (result == JOptionPane.CANCEL_OPTION) {
				JOptionPane.showMessageDialog(panelPrincipal,
						"Utilisation du nom par défaut...");
				// TODO prenom non saisi = crash
			}

			// on démarre la partie.
			StartGame();
		}
	}

	/**
	 * Ecouteur pour le bouton {@link PanelPrincipal#jb_quitterPartie}
	 * 
	 * @author Sylvain - Kevin
	 *
	 */
	public class QuitterListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {

			int option = JOptionPane.showConfirmDialog(null,
					"Voulez-vous quitter ?", "Arrêt de l'application",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if (option != JOptionPane.NO_OPTION
					&& option != JOptionPane.CLOSED_OPTION) {
				panelPrincipal.setVisible(false);
				System.exit(0);
			}
		}

	}
}
