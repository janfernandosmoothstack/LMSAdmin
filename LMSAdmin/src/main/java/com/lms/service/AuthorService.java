package com.lms.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.lms.dao.AuthorDao;
import com.lms.pojo.Author;

public class AuthorService {
	
	@Autowired
	AuthorDao authorDao;
	
	public Author getAuthorById(int authorId) {
		return authorDao.getAuthorById(authorId);
	}
}
