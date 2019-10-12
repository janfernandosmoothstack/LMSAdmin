package com.lms.ss.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.lms.ss.dao.AuthorDao;
import com.lms.ss.pojo.Author;

public class AuthorService {
	
	@Autowired
	AuthorDao authorDao;
	
	public Author getAuthorById(int authorId) {
		return authorDao.getAuthorById(authorId);
	}
}
