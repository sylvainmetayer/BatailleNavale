package com.bataille.util;

public enum Motif {
	TOUCHE, NONTOUCHE;

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
}
