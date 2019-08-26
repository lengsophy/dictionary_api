package com.puthisastra.rest.repository;

import java.util.List;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.puthisastra.rest.domain.Vocab;

public interface VocabRepository extends JpaRepository<Vocab, Long> {
	
	   @Query(
	            value = "SELECT v.* FROM tbl_category c inner join `tbl_vocab_category` cv on c.`id` = cv.`category_id` inner join `tbl_vocab` v on cv.`tbl_vocab_id`=v.`id` where c.`category_en` LIKE %:searchCat%",
	            nativeQuery = true
	    )
	    public List<Vocab> searchByCategoryEnKey(@Param("searchCat") String searchCat);
	   
	   
	   @Query(
	            value = "SELECT v.* FROM tbl_tag tag inner join `tbl_vocab_tag` tv on tag.`id` = tv.`tag_id` inner join `tbl_vocab` v on tv.`tbl_vocab_id`= v.`id` where tag.`year` = :year AND tag.`term` = :term",
	            nativeQuery = true
	    )
	    public List<Vocab> searchByTags(@Param("year") String searchByYear, @Param("term") String searchByTerm);
	   
	   
	   @Query(
	            value = "SELECT v.* FROM tbl_vocab v where v.`key_en` LIKE %:keyEN%",
	            nativeQuery = true
	    )
	    public List<Vocab> searchByVocabEnKey(@Param("keyEN") String keyEN);
	   
	   
	
}
