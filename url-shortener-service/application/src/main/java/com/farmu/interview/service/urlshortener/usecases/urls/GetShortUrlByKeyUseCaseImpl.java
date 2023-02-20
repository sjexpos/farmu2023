package com.farmu.interview.service.urlshortener.usecases.urls;

import org.springframework.stereotype.Component;

import com.farmu.interview.service.urlshortener.domain.ShortUrl;
import com.farmu.interview.service.urlshortener.exception.UrlNotFoundDomainException;
import com.farmu.interview.service.urlshortener.repository.ShortUrlRepository;

@Component
public class GetShortUrlByKeyUseCaseImpl implements GetShortUrlByKeyUseCase {

    private final ShortUrlRepository shortUrlRepository;

	public GetShortUrlByKeyUseCaseImpl(ShortUrlRepository shortUrlRepository) {
		super();
		this.shortUrlRepository = shortUrlRepository;
	}

	@Override
	public ShortUrl handle(String key) throws UrlNotFoundDomainException {
		return this.shortUrlRepository.findByKey(key)
					.orElseThrow(() -> new UrlNotFoundDomainException(key));
	}

}
