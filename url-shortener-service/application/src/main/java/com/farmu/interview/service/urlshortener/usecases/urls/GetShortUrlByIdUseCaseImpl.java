package com.farmu.interview.service.urlshortener.usecases.urls;

import org.springframework.stereotype.Component;

import com.farmu.interview.service.urlshortener.domain.ShortUrl;
import com.farmu.interview.service.urlshortener.exception.UrlNotFoundDomainException;
import com.farmu.interview.service.urlshortener.repository.ShortUrlRepository;

@Component
public class GetShortUrlByIdUseCaseImpl implements GetShortUrlByIdUseCase {
	
    private final ShortUrlRepository shortUrlRepository;

	public GetShortUrlByIdUseCaseImpl(ShortUrlRepository shortUrlRepository) {
		super();
		this.shortUrlRepository = shortUrlRepository;
	}

	public ShortUrl handle(Long id) throws UrlNotFoundDomainException {
		return this.shortUrlRepository.findById(id)
				.orElseThrow(() -> new UrlNotFoundDomainException(Long.toString(id))); 
	}

}
