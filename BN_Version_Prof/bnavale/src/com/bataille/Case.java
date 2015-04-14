package com.bataille;

public class Case {

	private int posx;
	private int posy;
	private boolean estTouche;
	private String motif;
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Case [posx=");
		builder.append(posx);
		builder.append(", posy=");
		builder.append(posy);
		builder.append(", estTouche=");
		builder.append(estTouche);
		builder.append(", motif=");
		builder.append(motif);
		builder.append("]");
		return builder.toString();
	}
	public Case(int posx, int posy, boolean estTouche, String motif) {
		super();
		this.posx = posx;
		this.posy = posy;
		this.estTouche = estTouche;
		this.motif = motif;
	}
	public int getPosx() {
		return posx;
	}
	public void setPosx(int posx) {
		this.posx = posx;
	}
	public int getPosy() {
		return posy;
	}
	public void setPosy(int posy) {
		this.posy = posy;
	}
	public boolean isEstTouche() {
		return estTouche;
	}
	public void setEstTouche(boolean estTouche) {
		this.estTouche = estTouche;
	}
	public String getMotif() {
		return motif;
	}
	public void setMotif(String motif) {
		this.motif = motif;
	}


}
