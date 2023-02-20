package com.farmu.interview.service.urlshortener.usecases.urls;

import org.springframework.stereotype.Component;

import com.farmu.interview.service.urlshortener.domain.ShortUrl;
import com.farmu.interview.service.urlshortener.exception.UrlNotFoundDomainException;
import com.farmu.interview.service.urlshortener.repository.ShortUrlRepository;

@Component
public class DeleteShortUrlUseCaseImpl implements DeleteShortUrlUseCase {

    private final ShortUrlRepository shortUrlRepository;

	public DeleteShortUrlUseCaseImpl(ShortUrlRepository shortUrlRepository) {
		super();
		this.shortUrlRepository = shortUrlRepository;
	}

	@Override
	public ShortUrl handle(long id) throws UrlNotFoundDomainException {
		ShortUrl shortUrl = this.shortUrlRepository.findById(id)
				.orElseThrow(() -> new UrlNotFoundDomainException(Long.toString(id)));
		this.shortUrlRepository.delete(shortUrl);
		return shortUrl;
	}

}
