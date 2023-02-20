package com.farmu.interview.service.urlshortener.api.exception;

public class NotFoundApiException extends Exception {
	private static final long serialVersionUID = -1608923430633247488L;

	public NotFoundApiException() {}

    public NotFoundApiException(String message) {super(message);}
}
