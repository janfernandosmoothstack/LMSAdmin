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

import com.lms.LMSAdmin.pojo.Book;
import com.lms.LMSAdmin.service.AuthorService;
import com.lms.LMSAdmin.service.BookService;
import com.lms.LMSAdmin.service.PublisherService;

@RestController
@RequestMapping(value = "/LMSAdmin/book",
	consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}, 
	produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})

public class BookController {
	
	@Autowired
	BookService bookService;
	
	@Autowired
	AuthorService authService;
	
	@Autowired
	PublisherService pubService;
	
	//Create a record
	@RequestMapping(value = "/create/title/{title}/authId/{authId}/pubId/{pubId}", method = {RequestMethod.POST, RequestMethod.GET})
	public ResponseEntity<String> insertBook(@PathVariable("title") String title, @PathVariable("authId") int authId, 
			@PathVariable("pubId") int pubId) {
		
		boolean checkId = authService.ifExists(authId);
				
		if(checkId == true) {
			checkId = pubService.ifExists(pubId);
			
			if(checkId == true) {
				bookService.insertBook(title, authId, pubId);
				return new ResponseEntity<String>("Book record created.", HttpStatus.CREATED);
			} else {
				return new ResponseEntity<String>("Invalid publisher ID.", HttpStatus.NOT_FOUND);
			}
		} else {
			return new ResponseEntity<String>("Invalid author ID.", HttpStatus.NOT_FOUND);
		}
	}
	
	//Update a record
	@RequestMapping(value = "/update/bookId/{bookId}/title/{title}/authId/{authId}/pubId/{pubId}", 
			method = {RequestMethod.PUT, RequestMethod.GET})

	public ResponseEntity<String> updateBook(@PathVariable("bookId") int bookId, @PathVariable("title") String title, 
			@PathVariable("authId") int authId, @PathVariable("pubId") int pubId) {
		
		boolean checkId = bookService.ifExists(bookId);
		
		if(checkId == true) {
			checkId = authService.ifExists(authId);
			
			if(checkId == true) {
				checkId = pubService.ifExists(pubId);
				
				if(checkId == true) {
					bookService.updateBook(bookId, title, authId, pubId);
					return new ResponseEntity<String>("Book record updated.", HttpStatus.OK);
				}else {
					return new ResponseEntity<String>("Invalid publisher ID.", HttpStatus.NOT_FOUND);
				}
			} else {
				return new ResponseEntity<String>("Invalid author ID.", HttpStatus.NOT_FOUND);
			}
		} else {
			return new ResponseEntity<String>("Invalid book ID.", HttpStatus.NOT_FOUND);
		}
	}
	
	//Delete a record
	@RequestMapping(value = "/delete/bookId/{bookId}", method = {RequestMethod.DELETE, RequestMethod.GET})
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public ResponseEntity<String> deleteBook(@PathVariable("bookId") int bookId) {
		
		boolean checkId = bookService.ifExists(bookId);
		
		if(checkId == true) {
			bookService.deleteBook(bookId);
			return new ResponseEntity<String>("Book record deleted.", HttpStatus.ACCEPTED);
		}else {
			return new ResponseEntity<String>("Invalid book ID.", HttpStatus.NOT_FOUND);
		}	
	}
	
	//View all records
	@RequestMapping(value = "/view", method = RequestMethod.GET)
	@ResponseStatus(code = HttpStatus.OK)
	public List<Book> getAllBooks() {
		return bookService.getAllBooks();
	}
}
