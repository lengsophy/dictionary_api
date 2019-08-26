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
import com.puthisastra.rest.domain.Vocab;
import com.puthisastra.rest.repository.CategoryRepository;
import com.puthisastra.rest.repository.VocabRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;

@RestController
@RequestMapping("/categorys")
@Api(value="categorys", description="Category REST endpoint")
public class CategoryController {

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private VocabRepository vocabRepository;
	
	@ApiOperation(value = "View a list of Categorys")
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "Successfully retrieved list"),
	        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden")
	    })
	
	@GetMapping
	public ResponseEntity<List<Category>> getAll() {
		return ResponseEntity.ok().body(categoryRepository.findAll());
	}
	
	@ApiOperation(value = "Get a category by id")
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "Successfully get Category"),
	        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	        @ApiResponse(code = 404, message = "The Category is not existing")
	    })
	@GetMapping("/{id}")
	public ResponseEntity<Category> getById(@PathVariable(value = "id") Long categoryId) {
		Optional<Category> category = categoryRepository.findById(categoryId);
		if (!category.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return ResponseEntity.ok().body(category.get());
	}
	
	@PostMapping
	@ApiOperation(value = "Save a new Cagegory")
	@ApiResponses(value = {
	        @ApiResponse(code = 201, message = "Successfully save Category"),
	        @ApiResponse(code = 400, message = "Request parameters are invalid"),
	        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden")
	    })
	public ResponseEntity<Category> create(@Valid @RequestBody Category category) {
		return new ResponseEntity<>(categoryRepository.save(category), HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	@ApiOperation(value = "Update data of existing Category")
	@ApiResponses(value = {
	        @ApiResponse(code = 201, message = "Successfully updated dictionary"),
	        @ApiResponse(code = 400, message = "Request parameters are invalid"),
	        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	        @ApiResponse(code = 404, message = "Word not found")
	    })
	public ResponseEntity<Category> update(@PathVariable(value = "id") Long categoryId, @Valid @RequestBody Category category) {
		Category categoryInDb = categoryRepository.getOne(categoryId);
		categoryInDb.setCategory_en(category.getCategory_en());
		return new ResponseEntity<>(categoryRepository.save(categoryInDb), HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{id}")
	@ApiOperation(value = "Delete existing category")
	@ApiResponses(value = {
	        @ApiResponse(code = 204, message = "Successfully deleted Category"),
	        @ApiResponse(code = 400, message = "Request parameters are invalid"),
	        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	        @ApiResponse(code = 404, message = "Dictionary not found")
	    })
	public ResponseEntity<Void> delete(@PathVariable(value = "id") Long wordId) {
		if (!categoryRepository.existsById(wordId)) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		categoryRepository.deleteById(wordId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/search/{key}")
//	@ApiOperation(value = "Search Category by input text")
	public ResponseEntity<Map<String, Object>> SearchByCategory(
			@RequestParam(value="key",required=true) String searchParam) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		try {

			List<Vocab> result = vocabRepository.searchByCategoryEnKey(searchParam);
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
