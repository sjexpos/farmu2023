package com.farmu.interview.service.urlshortener.usecases.urls;

import com.farmu.interview.service.urlshortener.domain.ShortUrl;

public interface CreateShortUrlUseCase {

    ShortUrl handle(String url);

}
