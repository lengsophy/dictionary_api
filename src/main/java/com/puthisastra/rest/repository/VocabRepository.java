package com.puthisastra.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.puthisastra.rest.domain.Vocab;

public interface VocabRepository extends JpaRepository<Vocab, Long> {
	
}
