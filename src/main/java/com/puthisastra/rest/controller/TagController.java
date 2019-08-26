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

import com.puthisastra.rest.domain.Tag;
import com.puthisastra.rest.domain.Vocab;
import com.puthisastra.rest.repository.TagRepository;
import com.puthisastra.rest.repository.VocabRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;

@RestController
@RequestMapping("/tags")
@Api(value="tags", description="Tag REST endpoint")
public class TagController {

	@Autowired
	private TagRepository tagRepository;
	
	@Autowired
	private VocabRepository vocabularyRepository;
	
	@ApiOperation(value = "View a list of Tags")
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "Successfully retrieved list"),
	        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden")
	    })
	@GetMapping
	public ResponseEntity<List<Tag>> getAll() {
		return ResponseEntity.ok().body(tagRepository.findAll());
	}
	
	@ApiOperation(value = "Get a Tags by id")
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "Successfully get Tags"),
	        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	        @ApiResponse(code = 404, message = "The Tag is not existing")
	    })
	
	@GetMapping("/{id}")
	public ResponseEntity<Tag> getById(@PathVariable(value = "id") Long tagId) {
		Optional<Tag> tag = tagRepository.findById(tagId);
		if (!tag.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return ResponseEntity.ok().body(tag.get());
	}
	
	@PostMapping
	@ApiOperation(value = "Save a new Cagegory")
	@ApiResponses(value = {
	        @ApiResponse(code = 201, message = "Successfully save Tag"),
	        @ApiResponse(code = 400, message = "Request parameters are invalid"),
	        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden")
	    })
	public ResponseEntity<Tag> create(@Valid @RequestBody Tag tag) {
		return new ResponseEntity<>(tagRepository.save(tag), HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	@ApiOperation(value = "Update data of existing Tag")
	@ApiResponses(value = {
	        @ApiResponse(code = 201, message = "Successfully updated dictionary"),
	        @ApiResponse(code = 400, message = "Request parameters are invalid"),
	        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	        @ApiResponse(code = 404, message = "Word not found")
	    })
	public ResponseEntity<Tag> update(@PathVariable(value = "id") Long tagId, @Valid @RequestBody Tag tag) {
		Tag tagInDb = tagRepository.getOne(tagId);
		tagInDb.setTerm(tag.getTerm());
		tagInDb.setYear(tag.getYear());
		return new ResponseEntity<>(tagRepository.save(tagInDb), HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{id}")
	@ApiOperation(value = "Delete existing tag")
	@ApiResponses(value = {
	        @ApiResponse(code = 204, message = "Successfully deleted Tag"),
	        @ApiResponse(code = 400, message = "Request parameters are invalid"),
	        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	        @ApiResponse(code = 404, message = "Tag not found")
	    })
	public ResponseEntity<Void> delete(@PathVariable(value = "id") Long wordId) {
		if (!tagRepository.existsById(wordId)) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		tagRepository.deleteById(wordId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/search/{year}/{term}")
//	@ApiOperation(value = "Search Tags by input text")
	public ResponseEntity<Map<String, Object>> SearchByCategory(
			@RequestParam(value="year",required=true) String year,
			@RequestParam(value="term",required=true) String term) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		try {

			List<Vocab> result = vocabularyRepository.searchByTags(year, term);
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
