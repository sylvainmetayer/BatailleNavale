package ihm.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import outils.MotifsDivers;
import outils.NavireCaracteristique;
import outils.Options;

/**
 * Ce panel contient une aide du jeu, avec les différents motifs et leur
 * significations
 * 
 * @author Sylvain METAYER - Kevin DESSIMOULIE
 *
 */
public class PanelAide extends JPanel {

	private static final long serialVersionUID = 1L;

	private JPanel jp_grilleIcon;
	private JTextArea jta_regle;
	private int nbLignes;
	private int nbColonnes;
	private JButton jb_fermer;

	/**
	 * Constructeur
	 */
	public PanelAide() {

		jta_regle = new JTextArea();
		jta_regle.setForeground(Options.getCouleurTexte());
		jta_regle.setEditable(false);
		jta_regle.setText(texteAide());
		jp_grilleIcon = new JPanel();
		jb_fermer = new JButton("Fermer la fenetre");
		jb_fermer.setForeground(Color.BLACK);
		jb_fermer.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();

			}
		});

		nbColonnes = 2;
		nbLignes = NavireCaracteristique.values().length
				+ MotifsDivers.values().length + 2;
		jp_grilleIcon.setLayout(new GridLayout(nbLignes, nbColonnes));

		ajoutIconTexte();

		this.setLayout(new BorderLayout());
		this.add(jb_fermer, BorderLayout.SOUTH);
		this.add(jta_regle, BorderLayout.WEST);
		this.add(jp_grilleIcon, BorderLayout.EAST);
	}

	/**
	 * Permet de fermer la fenetre active.
	 */
	private void dispose() {
		JFrame j = (JFrame) (SwingUtilities.windowForComponent(PanelAide.this));
		j.dispose(); // fermer la frame
	}

	/**
	 * Cette méthode permet d'ajouter l'icone et sa correspondance au panel
	 * {@link PanelAide#jp_grilleIcon}
	 */
	private void ajoutIconTexte() {
		JLabel jl_icon, jl_texte;
		JTextArea jt_texte;
		ImageIcon ic;
		String motif;

		int i = 0;
		for (MotifsDivers m : MotifsDivers.values()) {

			ic = m.getIcon();
			motif = m.getMotif();
			jl_icon = new JLabel();
			jl_icon.setIcon(ic);
			jl_icon.setText("Motif : " + motif);
			jl_icon.setForeground(Options.getCouleurTexte());
			jl_texte = new JLabel();
			jl_texte.setText(m.getTextEnrichi());
			jl_texte.setForeground(Options.getCouleurTexte());
			jp_grilleIcon.add(jl_icon, i);
			i++;
			jp_grilleIcon.add(jl_texte, i);
			i++;
		}

		// on ajoute un espace entre les deux, pour aérer
		jp_grilleIcon.add(new JLabel(""), i);
		i++;
		jp_grilleIcon.add(new JLabel(""), i);
		i++;

		for (NavireCaracteristique n : NavireCaracteristique.values()) {
			ic = n.getIcon();
			motif = n.getMotif();
			jl_icon = new JLabel();
			jl_icon.setIcon(ic);
			jl_icon.setText("Motif : " + motif);
			jl_icon.setForeground(Options.getCouleurTexte());
			jt_texte = new JTextArea();
			jt_texte.setEditable(false);
			jt_texte.setText(n.getTextEnrichi());
			jt_texte.append("\nValeur de score : " + n.getValeurScore());
			jt_texte.setForeground(Options.getCouleurTexte());
			jp_grilleIcon.add(jl_icon, i);
			i++;
			jp_grilleIcon.add(jt_texte, i);
			i++;
		}
	}

	/**
	 * Texte d'aide contenu dans le {@link PanelAide#jta_regle}
	 */
	private String texteAide() {
		StringBuilder sb = new StringBuilder();
		sb.append("Bienvenue sur l'aide de la bataille navale.\n\n");
		sb.append("Lors du démarrage de la partie, vous pouvez choisir certaines options.\n");
		sb.append("Vous pouvez laisser les noms des joueurs vide, les valeurs par défaut seront dans ce cas utilisée.\n");
		sb.append("Une fois les noms des joueurs renseignés, et la taille de la grille choisie, le jeu commence.\n"
				+ "Il vous faut donc placer vos navires.\n");
		sb.append("Chaque joueur dispose de "
				+ NavireCaracteristique.values().length + " navires\n");
		sb.append("Une fois que les deux joueurs auront placé leur navires, la partie commence.\n");
		sb.append("Tour à tour, chaque joueur clique sur une case du plateau adverse, dans l'espoir de toucher un navire.\n");
		sb.append("Lorsqu'un navire voit toutes ses cases touchées, il est alors coulé.\n");
		sb.append("Votre nombre de vie définit le nombre de bateaux qu'ils vous reste\n(Chaque bateaux dispose d'une valeur de score différente).\nAu début de la partie, chaque joueur dispose de "
				+ NavireCaracteristique.getScoreTotal()
				+ " vies. Le premier à 0 vies perd la partie.\n\n");
		sb.append("En cas de placement erroné, ou de coup joué de façon incorrect, vous serez invité à rejouer ou replacer votre bateau.\n");
		sb.append("Vous pouvez consulter l'historique des actions (placement ou tirs effectués)\nsur le bloc de log présent entre les deux plateaux.\n");

		sb.append("Ci-contre, vous trouverez les différents icones utilisés dans le jeu, ainsi que leur significations.\n"
				+ "Vous verrez également les motifs associés à ces icônes, dans le cas où les images ne seraient pas disponibles. \n\n");

		sb.append("Il existe une fonction de sauvegarde/chargement vous permettant de sauvegarder votre jeu à un moment donné,\n"
				+ " et de le recommencer ultérieurement.\n"
				+ "La fonction de sauvegarde est accessible une fois les bateaux des deux joueurs placés.\n"
				+ "La fonction de restauration est accessible à tout moment mais ne dispose pas d'avertissement.\n"
				+ "Elle charge directement la sauvegarde effectuée précédemment et efface donc la partie en cours !\n");

		sb.append("\nQue le meilleur d'entre vous gagne !");
		sb.append("\n\nBugs connus : \n");
		sb.append("- Lors du chargement, le tour du joueur n'est pas forcément le joueur qui aurait du recommencer à jouer.\n");
		return sb.toString();
	}
}
