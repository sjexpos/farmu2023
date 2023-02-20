package com.farmu.interview.service.urlshortener.usecases.urls;

import com.farmu.interview.service.urlshortener.domain.ShortUrl;
import com.farmu.interview.service.urlshortener.exception.UrlNotFoundDomainException;

public interface DeleteShortUrlUseCase {

	ShortUrl handle(long id) throws UrlNotFoundDomainException;
	
}
