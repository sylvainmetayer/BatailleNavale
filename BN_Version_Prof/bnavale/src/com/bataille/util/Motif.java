package com.bataille.util;

import java.awt.Image;

public enum Motif {
	TOUCHE, NONTOUCHE;

	@Override
	public String toString() {
		switch (this) {
		case NONTOUCHE:
			return "~";
		case TOUCHE:
			return "X";
		default:
			return null;
		}
	}

	// Pour le graphique
	public Image toImage() {
		return null;

	}

}
