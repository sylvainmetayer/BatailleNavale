/**
 * 
 */
package ihm.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import enums.MotifsDivers;
import enums.NavireCaracteristique;

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

	public PanelAide() {

		jta_regle = new JTextArea();
		jta_regle.setForeground(Color.BLUE);
		jta_regle.setEditable(false);
		jp_grilleIcon = new JPanel();

		nbColonnes = 2;
		nbLignes = NavireCaracteristique.values().length
				+ MotifsDivers.values().length;
		jp_grilleIcon.setLayout(new GridLayout(nbLignes, nbColonnes));

		texteAide();
		ajoutIconTexte();

		this.setLayout(new BorderLayout());
		this.add(jta_regle, BorderLayout.WEST);
		this.add(jp_grilleIcon, BorderLayout.EAST);
	}

	/**
	 * Cette méthode permet d'ajouter l'icone et sa correspondance au panel
	 * {@link PanelAide#jp_grilleIcon}
	 */
	private void ajoutIconTexte() {
		JLabel jl_icon, jl_texte;
		ImageIcon ic;
		String motif;

		float[] f = Color.RGBtoHSB(21, 195, 214, null);
		Color c = Color.getHSBColor(f[0], f[1], f[2]);

		int i = 0;
		for (MotifsDivers m : MotifsDivers.values()) {

			ic = m.getIcon();
			motif = m.getMotif();
			jl_icon = new JLabel();
			jl_icon.setIcon(ic);
			jl_icon.setText("Motif : " + motif);
			jl_icon.setForeground(c);
			jl_texte = new JLabel();
			jl_texte.setText(m.getTextEnrichi());
			jl_texte.setForeground(c);
			jp_grilleIcon.add(jl_icon, i);
			i++;
			jp_grilleIcon.add(jl_texte, i);
			i++;
		}

		for (NavireCaracteristique n : NavireCaracteristique.values()) {
			ic = n.getIcon();
			motif = n.getMotif();
			jl_icon = new JLabel();
			jl_icon.setIcon(ic);
			jl_icon.setText("Motif : " + motif);
			jl_icon.setForeground(c);
			jl_texte = new JLabel();
			jl_texte.setText(n.getTextEnrichi());
			jl_texte.setForeground(c);
			jp_grilleIcon.add(jl_icon, i);
			i++;
			jp_grilleIcon.add(jl_texte, i);
			i++;
		}
	}

	/**
	 * Texte d'aide contenu dans le {@link PanelAide#jta_regle}
	 */
	private void texteAide() {
		StringBuilder sb = new StringBuilder();
		sb.append("Bienvenue sur l'aide de la bataille navale.\n\n");
		sb.append("Pour jouer, il vous faut commencer par placer vos navires.\n");
		sb.append("Chaque joueur dispose de "
				+ NavireCaracteristique.values().length + " navires\n");
		sb.append("Une fois que les deux joueurs auront placé leur navires, la partie commence.\n");
		sb.append("Tour à tour, chaque joueur clique sur une case du plateau adverse, dans l'espoir de toucher un navire.\n");
		sb.append("Lorsqu'un navire voit toutes ses cases touchées, il est alors coulé.\n");
		sb.append("Votre score vous indique votre progression. Le premier arrivé à "
				+ NavireCaracteristique.getScoreTotal()
				+ " gagne la partie !\n\n");
		sb.append("En cas de placement erroné, ou de coup joué de façon incorrect, vous serez invité à rejouer ou replacer votre bateau.\n");
		sb.append("Vous pouvez consulter l'historique des actions (placement ou tirs effectués)\nsur le bloc de log présent entre les deux plateaux.\n");

		sb.append("Ci-contre, vous trouverez les différents icones utilisés dans le jeu, ainsi que leur significations.\n");
		sb.append("\nQue le meilleur d'entre vous gagne !");
		jta_regle.append(sb.toString());
	}
}
