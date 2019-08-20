package com.puthisastra.rest.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.puthisastra.rest.domain.Category;
import com.puthisastra.rest.domain.Translation;
import com.puthisastra.rest.domain.Word;
import com.puthisastra.rest.dto.CreateWordDTO;
import com.puthisastra.rest.repository.CategoryRepository;
import com.puthisastra.rest.repository.TranslationRepository;
import com.puthisastra.rest.repository.WordRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;

@RestController
@RequestMapping("/words")
@Api(value="words", description="Word REST endpoint")
public class WordController {

	@Autowired
	private WordRepository wordRepository;
	
	@Autowired
	private TranslationRepository translationRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@ApiOperation(value = "View a list of Dictionary")
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "Successfully retrieved list"),
	        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden")
	    })
	@GetMapping
	public ResponseEntity<List<Word>> getAll() {
		return ResponseEntity.ok().body(wordRepository.findAll());
	}
	
	@ApiOperation(value = "Get a dictionary by id")
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "Successfully get dictionary"),
	        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	        @ApiResponse(code = 404, message = "The dictionary is not existing")
	    })
	@GetMapping("/{id}")
	public ResponseEntity<Word> getById(@PathVariable(value = "id") Long wordId) {
		Optional<Word> word = wordRepository.findById(wordId);
		if (!word.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return ResponseEntity.ok().body(word.get());
	}
	
	@PostMapping
	@ApiOperation(value = "Save a new dictionary")
	@ApiResponses(value = {
	        @ApiResponse(code = 201, message = "Successfully save dictionary"),
	        @ApiResponse(code = 400, message = "Request parameters are invalid"),
	        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden")
	    })
	public ResponseEntity<Word> create(@Valid @RequestBody CreateWordDTO dataDTO) {
		Word word = new Word();
		
		Category category = categoryRepository.findById(dataDTO.getCategory_id()).get();
		word.setCategory(category);
		
		Translation translation = new Translation();
		translation.setTranslate_en(dataDTO.getTranslate_en());
		translation.setTranslate_fn(dataDTO.getTranslate_fn());
		translation.setTranslate_kh(dataDTO.getTranslate_kh());
		
		translationRepository.save(translation);
		word.setWord_en(dataDTO.getWord_en());
		word.setWord_fn(dataDTO.getWord_fn());
		word.setWord_kh(dataDTO.getWord_kh());
		word.setWord_key(dataDTO.getWord_key());
		//word.setTranslate(translate);
	//	word.setTranslation(translation);
		word.setTranslation(Arrays.asList(translation));
		Word saveword = wordRepository.save(word);
		
		return new ResponseEntity<>(saveword, HttpStatus.CREATED);
	}
	 
	@PutMapping("/{id}")
	@ApiOperation(value = "Update data of existing word")
	@ApiResponses(value = {
	        @ApiResponse(code = 201, message = "Successfully updated dictionary"),
	        @ApiResponse(code = 400, message = "Request parameters are invalid"),
	        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	        @ApiResponse(code = 404, message = "Word not found")
	    })
	public ResponseEntity<Word> update(@PathVariable(value = "id") Long wordId, @Valid @RequestBody Word word) {
		Word wordInDb = wordRepository.getOne(wordId);
		wordInDb.setWord_key(word.getWord_key());
		return new ResponseEntity<>(wordRepository.save(wordInDb), HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{id}")
	@ApiOperation(value = "Delete existing word")
	@ApiResponses(value = {
	        @ApiResponse(code = 204, message = "Successfully deleted Dictionary"),
	        @ApiResponse(code = 400, message = "Request parameters are invalid"),
	        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	        @ApiResponse(code = 404, message = "Dictionary not found")
	    })
	public ResponseEntity<Void> delete(@PathVariable(value = "id") Long wordId) {
		if (!wordRepository.existsById(wordId)) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		wordRepository.deleteById(wordId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/search")
	public ResponseEntity<List<Word>> search(
			@RequestParam(name = "search")
			@ApiParam(allowableValues = "name,title,author")
			String searchParams) {
		return new ResponseEntity<>(Arrays.asList(), HttpStatus.OK);
	}
	
}
