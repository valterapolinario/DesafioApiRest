package com.valter.test.service.exceptions;

public class ObjectNotFoundException extends RuntimeException {// classe criada para tratar e personalizar o retorno de exceções

	private static final long serialVersionUID = 1L;

	public ObjectNotFoundException(String msg) {// recebe uma mensagem de de exceção
		super(msg);
	}

	public ObjectNotFoundException(String msg, Throwable cause) {// recebe uma mensagem e a causa da execeção
		super(msg, cause);
	}

}
