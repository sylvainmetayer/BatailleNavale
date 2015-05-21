package console;

/**
 * Interface du jeu
 * 
 * @author Sylvain METAYER - Kevin DESSIMOULIE
 *
 */
public interface IJeu {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bataille.IJeu#jouer(int, int)
	 */
	public abstract void jouer(int x, int y, Plateau plateau)
			throws CoordonneeException;

	public abstract Plateau getPlateauJoueurUn();

	public abstract void setPlateauJoueurUn(Plateau plateauJoueurUn);

	public abstract Plateau getPlateauJoueurDeux();

	public abstract void setPlateauJoueurDeux(Plateau plateauJoueurDeux);

	public abstract int getScore();

	public abstract void setScore(int score);

	public abstract int getNbreCoups();

	public abstract void setNbreCoups(int nbreCoups);

}