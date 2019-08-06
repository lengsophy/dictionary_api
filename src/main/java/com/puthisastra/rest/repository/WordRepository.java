package com.puthisastra.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.puthisastra.rest.domain.Word;

public interface WordRepository extends JpaRepository<Word, Long> {
	
}
