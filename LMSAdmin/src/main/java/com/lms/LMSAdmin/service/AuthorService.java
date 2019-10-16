package com.lms.LMSAdmin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lms.LMSAdmin.dao.AuthorDao;
import com.lms.LMSAdmin.pojo.Author;

@Component
public class AuthorService {
	
	@Autowired
	AuthorDao authorDao;
	
	//Insert author record
	public void insertAuthor(Author author) {
		authorDao.insertAuthor(author);
	}
	
	//Update author record
	public void updateAuthor(Author author) {
		authorDao.updateAuthor(author);
	}
	
	//Delete author record
	public void deleteAuthor(Author author) {
		authorDao.deleteAuthor(author);
	}
	
	//Get all author records
	public List<Author> getAllAuthors() {
		return authorDao.getAllAuthors();
	}
	
	//Validate author Id
	public boolean ifExists(int authorId) {
		List<Author> list = authorDao.getAllAuthors();
		
		boolean exists = list.stream()
				.anyMatch(id -> id.getAuthorId().equals(authorId));
	
		return exists;
	}
}
