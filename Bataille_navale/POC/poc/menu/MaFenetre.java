package poc.menu;

import javax.swing.JFrame;

public class MaFenetre extends JFrame {

	private static final long serialVersionUID = 5886351873390762539L;
	private static final int HAUTEUR = 800;
	private static final int LARGEUR = 400;

	public MaFenetre() {
		this.setTitle("POC Placement layout");
		this.setSize(HAUTEUR, LARGEUR);
		this.getContentPane().add(new MonPanel());
		this.setResizable(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		this.setVisible(true);

	}
}
