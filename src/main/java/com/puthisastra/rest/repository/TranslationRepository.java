package com.puthisastra.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.puthisastra.rest.domain.Translation;
public interface TranslationRepository extends JpaRepository<Translation, Long> {

}
