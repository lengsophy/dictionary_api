package com.puthisastra.rest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.puthisastra.rest.domain.Tag;

public interface TagRepository extends JpaRepository<Tag, Long> {
	List<Tag> findByTerm(String term);
}
