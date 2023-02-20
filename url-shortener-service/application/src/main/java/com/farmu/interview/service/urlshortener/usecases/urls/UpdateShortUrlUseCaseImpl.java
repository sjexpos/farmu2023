package com.farmu.interview.service.urlshortener.usecases.urls;

import org.springframework.stereotype.Component;

import com.farmu.interview.service.urlshortener.domain.ShortUrl;
import com.farmu.interview.service.urlshortener.exception.UrlNotFoundDomainException;
import com.farmu.interview.service.urlshortener.repository.ShortUrlRepository;

@Component
public class UpdateShortUrlUseCaseImpl implements UpdateShortUrlUseCase {

    private final ShortUrlRepository shortUrlRepository;

	public UpdateShortUrlUseCaseImpl(ShortUrlRepository shortUrlRepository) {
		super();
		this.shortUrlRepository = shortUrlRepository;
	}

	@Override
	public ShortUrl handle(Long id, String destinationUrl) throws UrlNotFoundDomainException {
		ShortUrl shortUrl = this.shortUrlRepository.findById(id)
				.orElseThrow(() -> new UrlNotFoundDomainException(Long.toString(id)));
		shortUrl.setDestination(destinationUrl);
		shortUrl = this.shortUrlRepository.save(shortUrl);
		return shortUrl;
	}

}
