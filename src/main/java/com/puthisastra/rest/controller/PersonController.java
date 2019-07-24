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

import com.puthisastra.rest.domain.Person;
import com.puthisastra.rest.repository.PersonRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;

@RestController
@RequestMapping("/persons")
@Api(value="persons", description="Persons REST endpoint")
public class PersonController {

	@Autowired
	private PersonRepository personRepository;
	
	@ApiOperation(value = "View a list of persons")
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "Successfully retrieved list"),
	        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden")
	    })
	@GetMapping
	public ResponseEntity<List<Person>> getAll() {
		return ResponseEntity.ok().body(personRepository.findAll());
	}
	
	@ApiOperation(value = "Get a person by id")
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "Successfully get person"),
	        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	        @ApiResponse(code = 404, message = "The person is not existing")
	    })
	@GetMapping("/{id}")
	public ResponseEntity<Person> getById(@PathVariable(value = "id") Long personId) {
		Optional<Person> person = personRepository.findById(personId);
		if (!person.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return ResponseEntity.ok().body(person.get());
	}
	
	@PostMapping
	@ApiOperation(value = "Save a new person")
	@ApiResponses(value = {
	        @ApiResponse(code = 201, message = "Successfully save person"),
	        @ApiResponse(code = 400, message = "Request parameters are invalid"),
	        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden")
	    })
	public ResponseEntity<Person> create(@Valid @RequestBody Person person) {
		return new ResponseEntity<>(personRepository.save(person), HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	@ApiOperation(value = "Update data of existing person")
	@ApiResponses(value = {
	        @ApiResponse(code = 201, message = "Successfully updated person"),
	        @ApiResponse(code = 400, message = "Request parameters are invalid"),
	        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	        @ApiResponse(code = 404, message = "Person not found")
	    })
	public ResponseEntity<Person> update(@PathVariable(value = "id") Long personId, @Valid @RequestBody Person person) {
		Person personInDb = personRepository.getOne(personId);
		personInDb.setFirstname(person.getFirstname());
		personInDb.setLastname(person.getLastname());
		personInDb.setAge(person.getAge());
		return new ResponseEntity<>(personRepository.save(personInDb), HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{id}")
	@ApiOperation(value = "Delete existing person")
	@ApiResponses(value = {
	        @ApiResponse(code = 204, message = "Successfully deleted person"),
	        @ApiResponse(code = 400, message = "Request parameters are invalid"),
	        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	        @ApiResponse(code = 404, message = "Person not found")
	    })
	public ResponseEntity<Void> delete(@PathVariable(value = "id") Long personId) {
		if (!personRepository.existsById(personId)) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		personRepository.deleteById(personId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/search")
	public ResponseEntity<List<Person>> search(
			@RequestParam(name = "search")
			@ApiParam(allowableValues = "name,title,author")
			String searchParams) {
		return new ResponseEntity<>(Arrays.asList(), HttpStatus.OK);
	}
	
}
