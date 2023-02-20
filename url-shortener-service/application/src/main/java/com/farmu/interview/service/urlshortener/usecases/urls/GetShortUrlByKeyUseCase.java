package com.farmu.interview.service.urlshortener.usecases.urls;

import com.farmu.interview.service.urlshortener.domain.ShortUrl;
import com.farmu.interview.service.urlshortener.exception.UrlNotFoundDomainException;

public interface GetShortUrlByKeyUseCase {

	ShortUrl handle(String key) throws UrlNotFoundDomainException;
	
}
