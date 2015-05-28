/**
 * 
 */
package ihm.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import outils.Options;

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
	private Integer[] valeurs = { 6, 8, 10 };
	private JLabel jl_haut;
	private JButton jb_valider;

	private PanelPrincipal jpp;

	public PanelOption(PanelPrincipal jpp) {
		this.jpp = jpp;

		jp_contenu = new JPanel();
		jp_contenu.setLayout(new GridLayout(3, 2));
		this.setLayout(new BorderLayout());

		jl_haut = new JLabel("Saisir vos options", JLabel.CENTER);
		jb_valider = new JButton("Valider");
		jb_valider.setPreferredSize(new Dimension(100, 100));
		jb_valider.setBackground(Color.GREEN);
		jt_joueurUn = new JTextField();
		jt_joueurUn.setText(System.getProperty("user.name"));
		jl_joueurUn = new JLabel("Nom du joueur un : ", JLabel.CENTER);
		jt_joueurDeux = new JTextField();
		jl_joueurDeux = new JLabel("Nom du joueur deux : ", JLabel.CENTER);
		jcb_choixTaille = new JComboBox<Integer>();

		for (Integer i : valeurs) {
			jcb_choixTaille.addItem(i);
		}
		jcb_choixTaille.setSelectedItem(valeurs[0]);

		jl_choixTaille = new JLabel("Choisir la taille du plateau :"
				+ jcb_choixTaille.getSelectedItem() + "*"
				+ jcb_choixTaille.getSelectedItem(), JLabel.CENTER);
		jcb_choixTaille.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				jl_choixTaille.setText("Choisir la taille du plateau :"
						+ jcb_choixTaille.getSelectedItem() + "*"
						+ jcb_choixTaille.getSelectedItem());
			}
		});

		jp_contenu.add(jl_joueurUn, 0);
		jp_contenu.add(jt_joueurUn, 1);
		jp_contenu.add(jl_joueurDeux, 2);
		jp_contenu.add(jt_joueurDeux, 3);
		jp_contenu.add(jl_choixTaille, 4);
		jp_contenu.add(jcb_choixTaille, 5);

		this.add(jl_haut, BorderLayout.NORTH);
		this.add(jp_contenu, BorderLayout.CENTER);
		this.add(jb_valider, BorderLayout.SOUTH);
		repaint();

		jb_valider.addActionListener(new DebutPartieListener());
	}

	class DebutPartieListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String nomJoueurUn, nomJoueurDeux;
			int taille;

			taille = (int) jcb_choixTaille.getSelectedItem();
			nomJoueurDeux = jt_joueurDeux.getText();
			nomJoueurUn = jt_joueurUn.getText();

			if (nomJoueurDeux.isEmpty())
				nomJoueurDeux = Options.DEFAULTJOUEURDEUX;

			if (nomJoueurUn.isEmpty())
				nomJoueurUn = Options.DEFAULTJOUEURUN;

			Options.setNomJoueurDeux(nomJoueurDeux);
			Options.setNomJoueurUn(nomJoueurUn);
			
			//jpp.setNomJoueurUn(nomJoueurUn);
			//jpp.setNomJoueurDeux(nomJoueurDeux);
			Options.setTailleGrilleJeu(taille);

			JFrame j = (JFrame) (SwingUtilities
					.windowForComponent(PanelOption.this));
			j.dispose(); // fermer la frame

			jpp.initGame();
		}
	}
}
