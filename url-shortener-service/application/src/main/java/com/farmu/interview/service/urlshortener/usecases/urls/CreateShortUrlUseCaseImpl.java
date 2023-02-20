package com.farmu.interview.service.urlshortener.usecases.urls;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.farmu.interview.service.urlshortener.domain.ShortUrl;
import com.farmu.interview.service.urlshortener.repository.ShortUrlRepository;

@Component
public class CreateShortUrlUseCaseImpl implements CreateShortUrlUseCase {

    private final ShortUrlRepository shortUrlRepository;

    public CreateShortUrlUseCaseImpl(ShortUrlRepository shortUrlRepository) {
        this.shortUrlRepository = shortUrlRepository;
    }

    public ShortUrl handle(String url) {
       String key = UUID.randomUUID().toString().replace("-", "");
       ShortUrl shortUrl = ShortUrl.builder()
                            .key(key)
                            .destination(url)
                            .build();
       shortUrl = this.shortUrlRepository.save(shortUrl);
       return shortUrl;
    }

}
