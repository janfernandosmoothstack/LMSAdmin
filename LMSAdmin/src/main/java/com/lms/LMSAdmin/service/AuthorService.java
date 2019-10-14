package com.lms.LMSAdmin.service;

import java.sql.SQLException;
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
	public void insertAuthor(String authorName) {
		authorDao.insertAuthor(authorName);
	}
	
	//Update author record
	public void updateAuthor(int authorId, String authorName) {
		authorDao.updateAuthor(authorId, authorName);
	}
	
	//Delete author record
	public void deleteAuthor(int authorId) {
		authorDao.deleteAuthor(authorId);
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
	
	//test//////////
	public Author getAuthorById(int authorId) throws SQLException{
		return authorDao.getAuthorById(authorId);
	}
}
