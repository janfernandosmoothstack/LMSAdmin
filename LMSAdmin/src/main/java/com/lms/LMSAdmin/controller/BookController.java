package com.lms.LMSAdmin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.lms.LMSAdmin.pojo.Book;
import com.lms.LMSAdmin.service.BookService;

@RestController
public class BookController {
	
	@Autowired
	BookService bookService;
	
	//Create a record
	@RequestMapping(value = "/lmsAdmin/menu/1/bookMenu/1/title/{title}/authId/{authId}/pubId/{pubId}", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseStatus(code = HttpStatus.CREATED, reason = "Book record created.")
	public void insertBook(@PathVariable("title") String title, @PathVariable("authId") int authId, @PathVariable("pubId") int pubId) {
		bookService.insertBook(title, authId, pubId);
	}
	
	//Update a record
	@RequestMapping(value = "/lmsAdmin/menu/1/bookMenu/2/bookId/{bookId}/title/{title}/authId/{authId}/pubId/{pubId}", method = {RequestMethod.PUT, RequestMethod.GET})
	@ResponseStatus(code = HttpStatus.OK, reason = "Book record updated.")
	public void updateBook(@PathVariable("bookId") int bookId, @PathVariable("title") String title, @PathVariable("authId") int authId, @PathVariable("pubId") int pubId) {
		bookService.updateBook(bookId, title, authId, pubId);
	}
	
	//Delete a record
	@RequestMapping(value = "/lmsAdmin/menu/1/bookMenu/3/bookId/{bookId}", method = {RequestMethod.DELETE, RequestMethod.GET})
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void deleteBook(@PathVariable("bookId") int bookId) {
		bookService.deleteBook(bookId);
	}
	
	//View all records
	@RequestMapping(value = "/lmsAdmin/menu/1/bookMenu/4", method = RequestMethod.GET)
	@ResponseStatus(code = HttpStatus.OK)
	public List<Book> getAllBooks() {
		return bookService.getAllBooks();
	}
}
