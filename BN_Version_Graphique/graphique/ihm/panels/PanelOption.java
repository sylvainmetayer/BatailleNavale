/**
 * 
 */
package ihm.panels;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;

import javafx.scene.layout.Border;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * @author Sylvain METAYER - Kevin DESSIMOULIE
 *
 */
public class PanelOption extends JPanel {

	private static final long serialVersionUID = 1L;

	private JPanel jp_contenu;
	private JTextField jt_joueurUn;
	private JTextField jt_joueurDeux;
	private JLabel jl_joueurUn;
	private JLabel jl_joueurDeux;
	private JComboBox<Integer> jcb_choixTaille;
	private JLabel jl_choixTaille;
	private Integer[] valeurs = { 8, 10, 12 };
	private JLabel jl_haut;
	private JButton jb_valider;

	public PanelOption() {
		jp_contenu = new JPanel();
		jp_contenu.setLayout(new GridLayout(3, 2));
		this.setLayout(new BorderLayout());

		jl_haut = new JLabel("Saisir vos options");
		jb_valider = new JButton("Valider");
		jt_joueurUn = new JTextField();
		jl_joueurUn = new JLabel("Nom du joueur un :");
		jt_joueurDeux = new JTextField();
		jl_joueurUn = new JLabel("Nom du joueur deux :");
		jcb_choixTaille = new JComboBox<Integer>();

		for (Integer i : valeurs) {
			jcb_choixTaille.addItem(i);
		}
		jcb_choixTaille.setSelectedItem(valeurs[1]);

		jl_choixTaille = new JLabel("Choisir la taille du plateau :");

		Object[] tab = { jl_joueurDeux, jt_joueurUn, jl_joueurDeux,
				jt_joueurDeux, jl_choixTaille, jcb_choixTaille };

		for (int i = 0; i < 6; i++) {
			jp_contenu.add((Component) tab[i], i);
		}

		this.add(jl_haut, BorderLayout.NORTH);
		this.add(jp_contenu, BorderLayout.CENTER);
		this.add(jb_valider, BorderLayout.SOUTH);
	}
}
