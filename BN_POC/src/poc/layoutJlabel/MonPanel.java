package poc.layoutJlabel;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MonPanel extends JPanel implements ActionListener {

	private static final long serialVersionUID = 4591486156480744390L;
	private static final int LINE = 5;
	private static final int COLS = 5;

	private JPanel jp_plateau1;
	private JPanel jp_plateau2;
	private JLabel jl_log;
	private JPanel jp_centre;

	private JLabel jb_test;

	public MonPanel() {

		jl_log = new JLabel("Bonjour votre score");
		jp_centre = new JPanel();
		jp_centre.setLayout(new BorderLayout());

		jp_plateau1 = new JPanel();
		jp_plateau2 = new JPanel();
		jp_plateau1.setLayout(new GridLayout(LINE, COLS));
		jp_plateau2.setLayout(new GridLayout(LINE, COLS));

		for (int i = 0; i < LINE; i++) {
			for (int j = 0; j < COLS; j++) {
				int k = i;
				jb_test = new JLabel(Integer.toString(k + j));
				// jb_test.addActionListener(this);
				jp_plateau1.add(jb_test, k + j);
			}
		}

		for (int i = 0; i < LINE; i++) {
			for (int j = 0; j < COLS; j++) {
				int k = i;
				jb_test = new JLabel(Integer.toString(k + j));
				// bordure plus écouteur mouse listener a gerer
//				jb_test.addMouseListener(maMouse);
				// jb_test.addActionListener(this);
				jp_plateau2.add(jb_test, k + j);
			}
		}

		jp_centre.add(jp_plateau1, BorderLayout.EAST);
		jp_centre.add(jp_plateau2, BorderLayout.WEST);
		jp_centre.add(jl_log, BorderLayout.CENTER);

		this.setLayout(new BorderLayout());
		this.add(jp_centre, BorderLayout.CENTER);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		jl_log.setText("Je suis clické ! "
				+ ((JButton) e.getSource()).getText());

	}

	class maMouse extends MouseAdapter {

	}
}
