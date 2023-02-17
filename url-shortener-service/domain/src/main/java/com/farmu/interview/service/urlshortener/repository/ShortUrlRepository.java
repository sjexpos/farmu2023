package com.farmu.interview.service.urlshortener.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.farmu.interview.service.urlshortener.domain.ShortUrl;

import java.util.Optional;

public interface ShortUrlRepository extends GenericRepository<ShortUrl, Long> {

    Optional<ShortUrl> findById(long id);

    Optional<ShortUrl> findByKey(String key);

    Page<ShortUrl> findAll(Pageable pageable);

}
