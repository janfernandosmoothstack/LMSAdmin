package com.lms.LMSAdmin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lms.LMSAdmin.dao.BookDao;
import com.lms.LMSAdmin.pojo.Book;

@Component
public class BookService {
	
	@Autowired
	BookDao bookDao;
	
	//Insert record
	public void insertBook(Book book) {
		bookDao.insertBook(book);
	}
	
	//Update record
	public void updateBook(Book book) {
		bookDao.updateBook(book);
	}
	
	//Delete record
	public void deleteBook(Book book) {
		bookDao.deleteBook(book);
	}
	
	//Get all records
	public List<Book> getAllBooks() {
		return bookDao.getAllBooks();
	}
	
	//Validate Id
	public boolean ifExists(int bookId) {
		List<Book> list = bookDao.getAllBooks();
		
		boolean exists = list.stream()
				.anyMatch(id -> id.getBookId().equals(bookId));
	
		return exists;
	}
}
