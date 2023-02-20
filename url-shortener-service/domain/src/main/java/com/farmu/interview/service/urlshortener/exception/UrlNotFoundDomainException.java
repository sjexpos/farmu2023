package com.farmu.interview.service.urlshortener.exception;

public class UrlNotFoundDomainException extends NotFoundDomainException {
	private static final long serialVersionUID = 9072587250507970028L;

	public UrlNotFoundDomainException(String message) {
        super(message);
    }
}
