package com.bataille.metier;

/**
 * Classe représentant une exception levée lors de la réalisation d'un coup non
 * autorisé
 * 
 * @author Sylvain METAYER - Kevin DESSIMOULIE
 *
 */
public class CoupException extends Exception {

	private static final long serialVersionUID = 1L;

	public CoupException(String message) {
		super(message);
	}

}