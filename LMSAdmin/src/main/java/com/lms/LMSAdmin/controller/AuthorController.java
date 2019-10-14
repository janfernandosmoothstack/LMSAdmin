package com.lms.LMSAdmin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.lms.LMSAdmin.pojo.Author;
import com.lms.LMSAdmin.service.AuthorService;

@RestController
@RequestMapping("/lmsAdmin/menu/authMenu")
public class AuthorController {
	
	@Autowired
	AuthorService authorService;
	
	//Create a record
	@RequestMapping(value = "/create/authName/{authorName}", method = {RequestMethod.POST, RequestMethod.GET})
	public ResponseEntity<String> insertAuthor(@PathVariable String authorName) {
		
		authorService.insertAuthor(authorName);
		return new ResponseEntity<String>("Author record created.", HttpStatus.CREATED);
	}
	
	//Update a record
	@RequestMapping(value = "/update/authId/{authorId}/authName/{authorName}", method = {RequestMethod.PUT, RequestMethod.GET})
	public ResponseEntity<String> updateAuthor(@PathVariable("authorId") int authorId, @PathVariable("authorName") String authorName) {
		
		boolean checkId = authorService.ifExists(authorId);
		
		if(checkId == true) {
			authorService.updateAuthor(authorId, authorName);
			return new ResponseEntity<String>("Author record updated.", HttpStatus.ACCEPTED);
		}else {
			return new ResponseEntity<String>("Invalid ID.", HttpStatus.NOT_FOUND);
		}
	}
	
	//Delete a record
	@RequestMapping(value = "/delete/authId/{authorId}", method = {RequestMethod.DELETE, RequestMethod.GET})
	public ResponseEntity<String> deleteAuthor(@PathVariable int authorId) {
		
		boolean checkId = authorService.ifExists(authorId);
		
		if(checkId == true) {
			authorService.deleteAuthor(authorId);
			return new ResponseEntity<String>("Author record deleted.", HttpStatus.NO_CONTENT);
		}else {
			return new ResponseEntity<String>("Invalid ID.", HttpStatus.NOT_FOUND);
		}
	}
	
	//View all records
	@RequestMapping(value = "/view", method = RequestMethod.GET)
	@ResponseStatus(code = HttpStatus.OK)
	public List<Author> getAllAuthors() {
		return authorService.getAllAuthors();
	}
}
