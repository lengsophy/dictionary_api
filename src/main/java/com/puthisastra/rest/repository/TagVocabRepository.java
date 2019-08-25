package com.puthisastra.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.puthisastra.rest.domain.TagVocab;

public interface TagVocabRepository extends JpaRepository<TagVocab, Long> {
	
}
