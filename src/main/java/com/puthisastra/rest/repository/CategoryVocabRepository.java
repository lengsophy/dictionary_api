package com.puthisastra.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.puthisastra.rest.domain.CategoryVocab;

public interface CategoryVocabRepository extends JpaRepository<CategoryVocab, Long> {
	
}
