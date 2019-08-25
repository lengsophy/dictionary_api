package com.puthisastra.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.puthisastra.rest.domain.Category;
public interface CategoryRepository extends JpaRepository<Category, Long> {
	
}
