package com.tweetapp.usecase.exception;

public class TokenInvalidException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TokenInvalidException(String msg) {
        super(msg);
    }
}
