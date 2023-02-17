package com.farmu.interview.service.urlshortener.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.farmu.interview.service.urlshortener.domain.ShortUrl;

@Repository
public interface ShortUrlJpaRepository extends JpaRepository<ShortUrl, Long>, ShortUrlRepository {

    @Override
    Page<ShortUrl> findAll(Pageable pageable);
}
