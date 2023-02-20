package com.farmu.interview.service.urlshortener.usecases.urls;

import com.farmu.interview.service.urlshortener.domain.ShortUrl;
import com.farmu.interview.service.urlshortener.exception.UrlNotFoundDomainException;

public interface GetShortUrlByIdUseCase {

	ShortUrl handle(Long id) throws UrlNotFoundDomainException;
	
}
