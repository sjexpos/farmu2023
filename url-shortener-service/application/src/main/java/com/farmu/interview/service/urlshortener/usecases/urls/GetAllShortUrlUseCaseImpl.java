package com.farmu.interview.service.urlshortener.usecases.urls;

import com.farmu.interview.service.urlshortener.domain.ShortUrl;
import com.farmu.interview.service.urlshortener.repository.ShortUrlRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

@Component
public class GetAllShortUrlUseCaseImpl implements GetAllShortUrlUseCase {
    
    private final ShortUrlRepository shortUrlRepository;

    public GetAllShortUrlUseCaseImpl(ShortUrlRepository shortUrlRepository) {
        this.shortUrlRepository = shortUrlRepository;
    }

    public Page<ShortUrl> handle(PageRequest request) {
        return this.shortUrlRepository.findAll(request);
    }

}
