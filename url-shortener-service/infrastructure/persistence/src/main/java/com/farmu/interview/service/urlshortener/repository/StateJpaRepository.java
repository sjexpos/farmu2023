package com.farmu.interview.service.urlshortener.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.farmu.interview.service.urlshortener.domain.State;

@Repository
public interface StateJpaRepository extends JpaRepository<State, Long>, StateRepository {

    @Override
    Page<State> findAll(Pageable pageable);
}
