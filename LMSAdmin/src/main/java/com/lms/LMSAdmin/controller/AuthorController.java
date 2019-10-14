package com.lms.LMSAdmin.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.lms.LMSAdmin.pojo.Author;
import com.lms.LMSAdmin.service.AuthorService;

@RestController
public class AuthorController {
	
	@Autowired
	AuthorService authorService;
	
	//test
	@RequestMapping("/lmsAdmin/author/{authorId}")
	public Author getAuthorById(@PathVariable int authorId) throws SQLException {
		return authorService.getAuthorById(authorId);
	}
	
	/*@RequestMapping("/lmsAdmin/menu/1/authMenu/{authOpt}")
	public void authorMenu(@PathVariable int authOpt) throws SQLException {
		switch(authOpt) {
			case 1:
				insertAuthor();
 			case 4: 
				getAllAuthors();
				break;
			default:
				
				break;
		}
	}*/
	
	//Create a record
	@RequestMapping(value = "/lmsAdmin/menu/2/authMenu/1/authName/{authorName}", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseStatus(code = HttpStatus.CREATED, reason = "Author record created.")
	public void insertAuthor(@PathVariable String authorName) {
		authorService.insertAuthor(authorName);
	}
	
	//Update a record
	@RequestMapping(value = "/lmsAdmin/menu/2/authMenu/2/authId/{authorId}/authName/{authorName}", method = {RequestMethod.PUT, RequestMethod.GET})
	@ResponseStatus(code = HttpStatus.OK, reason = "Author record updated.")
	public void updateAuthor(@PathVariable("authorId") int authorId, @PathVariable("authorName") String authorName) {
		authorService.updateAuthor(authorId, authorName);
	}
	
	//Delete a record
	@RequestMapping(value = "/lmsAdmin/menu/2/authMenu/3/authId/{authorId}", method = {RequestMethod.DELETE, RequestMethod.GET})
	@ResponseStatus(code = HttpStatus.NO_CONTENT, reason = "Author record deleted.")
	public void deleteAuthor(@PathVariable int authorId) {
		authorService.deleteAuthor(authorId);
	}
	
	//View all records
	@RequestMapping(value = "/lmsAdmin/menu/2/authMenu/4", method = RequestMethod.GET)
	@ResponseStatus(code = HttpStatus.OK)
	public List<Author> getAllAuthors() {
		return authorService.getAllAuthors();
	}
}
