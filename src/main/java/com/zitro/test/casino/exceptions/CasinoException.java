package com.zitro.test.casino.exceptions;

public class CasinoException extends Exception  {
	
	private static final long serialVersionUID = 1L;
	
	public CasinoException(String errorMessage) {
        super(errorMessage);
    }

}
