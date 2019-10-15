package com.lms.LMSAdmin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.lms.LMSAdmin.pojo.Author;
import com.lms.LMSAdmin.service.AuthorService;

@RestController
@RequestMapping(value = "/LMSAdmin/authors")
public class AuthorController {

	@Autowired
	AuthorService authorService;
	
	//Create a record
	@RequestMapping(value = "/author/authorName/{authorName}", method = {RequestMethod.POST, RequestMethod.GET},
			consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<String> insertAuthor(@PathVariable String authorName) {
		
		authorService.insertAuthor(authorName);
		return new ResponseEntity<String>("Author record created.", HttpStatus.CREATED);
	}
	
	//Update a record
	@RequestMapping(value = "/author/authorId/{authorId}/authorName/{authorName}", method = {RequestMethod.PUT, RequestMethod.GET},
			consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<String> updateAuthor(@PathVariable("authorId") int authorId, @PathVariable("authorName") String authorName) {
		
		boolean checkId = authorService.ifExists(authorId);
		
		if(checkId == true) {
			authorService.updateAuthor(authorId, authorName);
			return new ResponseEntity<String>("Author record updated.", HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("Invalid ID.", HttpStatus.NOT_FOUND);
		}
	}
	
	//Delete a record
	@RequestMapping(value = "/author/authorId/{authorId}", method = {RequestMethod.DELETE, RequestMethod.GET},
			consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<String> deleteAuthor(@PathVariable int authorId) {
		
		boolean checkId = authorService.ifExists(authorId);
		
		if(checkId == true) {
			authorService.deleteAuthor(authorId);
			return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
		}else {
			return new ResponseEntity<String>("Invalid ID.", HttpStatus.NOT_FOUND);
		}
	}
	
	//Get all records
	@RequestMapping(value = "/author", method = RequestMethod.GET, 
			produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	@ResponseStatus(code = HttpStatus.OK)
	public List<Author> getAllAuthors() {
		return authorService.getAllAuthors();
	}
}
