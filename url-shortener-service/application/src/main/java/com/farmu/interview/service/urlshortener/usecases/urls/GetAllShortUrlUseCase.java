package com.farmu.interview.service.urlshortener.usecases.urls;

import com.farmu.interview.service.urlshortener.domain.ShortUrl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface GetAllShortUrlUseCase {
    
    Page<ShortUrl> handle(PageRequest request);

}
