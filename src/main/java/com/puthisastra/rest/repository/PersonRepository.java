package com.puthisastra.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.puthisastra.rest.domain.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {
	
}
