package com.farmu.interview.service.urlshortener.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.farmu.interview.service.urlshortener.domain.State;

import java.util.List;
import java.util.Optional;

public interface StateRepository extends GenericRepository<State, Long> {
    Optional<State> findById(long id);

    List<State> findAllByCountryId(long countryId);

    Page<State> findAll(Pageable pageable);
}
