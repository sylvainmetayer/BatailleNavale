package poc.menu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class MonPanel extends JPanel implements ActionListener {

	private static final long serialVersionUID = 4591486156480744390L;
	private static final int LINE = 5;
	private static final int COLS = 5;

	private JPanel jp_plateau1;
	private JPanel jp_plateau2;
	private JLabel jl_log;
	private JPanel jp_centre;
	private JMenu jm_menu;
	private JMenuItem bonjour, aurevoir;

	private JButton jb_test;

	public MonPanel() {

		bonjour = new JMenuItem("Bonjour");
		aurevoir = new JMenuItem("Au revoir");

		jm_menu = new JMenu();
		jm_menu.add(aurevoir);
		jm_menu.add(bonjour);

		// ajout des composant dans le layout principal
		this.setLayout(new BorderLayout());
		this.add(jm_menu, BorderLayout.NORTH);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		jl_log.setText("Je suis clické ! "
				+ ((JButton) e.getSource()).getText());

	}
}
