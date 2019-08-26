package com.puthisastra.rest.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import com.puthisastra.rest.domain.Tag;
import com.puthisastra.rest.domain.Vocab;
import com.puthisastra.rest.dto.CreateVocabDTO;
import com.puthisastra.rest.repository.CategoryRepository;
import com.puthisastra.rest.repository.TagRepository;
import com.puthisastra.rest.repository.VocabRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;

@RestController
@RequestMapping("/api/v1/vocabs")
@Api(value="vocabs", description="Vocab REST endpoint")
public class VocabController {
	
	@Autowired
	private TagRepository tagRepository;
	
	@Autowired
	private VocabRepository vocabRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@ApiOperation(value = "View a list of Dictionary")
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "Successfully retrieved list"),
	        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden")
	    })
	@GetMapping
	public ResponseEntity<List<Vocab>> getAll() {
		return ResponseEntity.ok().body(vocabRepository.findAll());
	}
	
	@ApiOperation(value = "Get a dictionary by id")
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "Successfully get dictionary"),
	        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	        @ApiResponse(code = 404, message = "The dictionary is not existing")
	    })
	@GetMapping("/{id}")
	public ResponseEntity<Vocab> getById(@PathVariable(value = "id") Long vocabId) {
		Optional<Vocab> vocab = vocabRepository.findById(vocabId);
		if (!vocab.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return ResponseEntity.ok().body(vocab.get());
	}
	
	@PostMapping
	@ApiOperation(value = "Save a new dictionary")
	@ApiResponses(value = {
	        @ApiResponse(code = 201, message = "Successfully save dictionary"),
	        @ApiResponse(code = 400, message = "Request parameters are invalid"),
	        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden")
	    })
	public ResponseEntity<Vocab> create(@Valid @RequestBody CreateVocabDTO dataDTO) {
		
		Category category = categoryRepository.findById(dataDTO.getCategory_id()).get();
		
		Tag tag = tagRepository.findById(dataDTO.getTag_id()).get();
		
		Vocab newvocab = new Vocab();
		
		newvocab.setDescription_en(dataDTO.getDescription_en());
		
		newvocab.setImage_url(dataDTO.getImage_url());
		
		newvocab.setKey_en(dataDTO.getKey_en());
		
		newvocab.setCategory(Arrays.asList(category));
		
		newvocab.setTag(Arrays.asList(tag));
		
		return new ResponseEntity<>(vocabRepository.save(newvocab), HttpStatus.CREATED);
		
		
	}
	 
	@PutMapping("/{id}")
	@ApiOperation(value = "Update data of existing Vocab")
	@ApiResponses(value = {
	        @ApiResponse(code = 201, message = "Successfully updated dictionary"),
	        @ApiResponse(code = 400, message = "Request parameters are invalid"),
	        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	        @ApiResponse(code = 404, message = "Vocab not found")
	    })
	public ResponseEntity<Vocab> update(@PathVariable(value = "id") Long vocabId, @Valid @RequestBody Vocab word) {
		Vocab vocabInDb = vocabRepository.getOne(vocabId);
		return new ResponseEntity<>(vocabRepository.save(vocabInDb), HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{id}")
	@ApiOperation(value = "Delete existing Vocab")
	@ApiResponses(value = {
	        @ApiResponse(code = 204, message = "Successfully deleted Dictionary"),
	        @ApiResponse(code = 400, message = "Request parameters are invalid"),
	        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	        @ApiResponse(code = 404, message = "Dictionary not found")
	    })
	public ResponseEntity<Void> delete(@PathVariable(value = "id") Long vocabId) {
		if (!vocabRepository.existsById(vocabId)) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		vocabRepository.deleteById(vocabId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	
	@GetMapping("/search/{key}")
//	@ApiOperation(value = "Search Vocab en key by input text")
	public ResponseEntity<Map<String, Object>> SearchByCategory(
			@RequestParam(value="key",required=true) String searchParam) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		try {

			List<Vocab> result = vocabRepository.searchByVocabEnKey(searchParam);
			if (!result.isEmpty()) {
				map.put("data", result);
				map.put("message", "Search found");
				map.put("status", true);
			} else {
				map.put("message", "Search not found");
				map.put("status", false);
			}
		} catch (Exception e) {
			map.put("message", "Something when wrong!");
			map.put("status", false);
			e.printStackTrace();
		}
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
	}
	
}
