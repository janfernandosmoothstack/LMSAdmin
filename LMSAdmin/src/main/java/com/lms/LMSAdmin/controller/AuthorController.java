package com.lms.LMSAdmin.controller;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.lms.LMSAdmin.pojo.Author;
import com.lms.LMSAdmin.service.AuthorService;

@RestController
@RequestMapping("/LMSAdmin/authors")
@Produces({"application/xml", "application/json"})
@Consumes({"application/xml", "application/json"})
public class AuthorController {

	@Autowired
	AuthorService authorService;
	
	//Create a record
	@PostMapping("/authorName/{authorName}")
	public ResponseEntity<?> insertAuthor(@RequestBody Author author) {
		
		authorService.insertAuthor(author);
		return new ResponseEntity<Author>(author, HttpStatus.CREATED);
	}
	
	//Update a record
	@PutMapping("/authorId/{authorId}/authorName/{authorName}")
	public ResponseEntity<?> updateAuthor(@RequestBody Author author) {
		
		boolean checkId = authorService.ifExists(author.getAuthorId());
		
		if(checkId == true) {
			authorService.updateAuthor(author);
			return new ResponseEntity<Author>(author, HttpStatus.OK);
		}else {
			return new ResponseEntity<Author>(author, HttpStatus.NOT_FOUND);
		}
	}
	
	//Delete a record
	@DeleteMapping("/authorId/{authorId}")
	public ResponseEntity<?> deleteAuthor(@RequestBody Author author) {
		
		boolean checkId = authorService.ifExists(author.getAuthorId());
		
		if(checkId == true) {
			authorService.deleteAuthor(author);
			return new ResponseEntity<Author>(HttpStatus.NO_CONTENT);
		}else {
			return new ResponseEntity<Author>(author, HttpStatus.NOT_FOUND);
		}
	}
	
	//Get all records
	@GetMapping("")
	@ResponseStatus(code = HttpStatus.OK)
	public List<Author> getAllAuthors() {
		return authorService.getAllAuthors();
	}
}
