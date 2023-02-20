package com.farmu.interview.service.urlshortener.api.exception;

public class UrlNotFoundApiException extends NotFoundApiException {
	private static final long serialVersionUID = -5532954680887850771L;

	public UrlNotFoundApiException() {
    }

    public UrlNotFoundApiException(String message) {
        super(message);
    }
}
