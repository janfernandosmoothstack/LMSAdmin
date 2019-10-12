package com.lms.LMSAdmin.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.lms.LMSAdmin.dao.AuthorDao;
import com.lms.LMSAdmin.pojo.Author;

public class AuthorService {
	
	@Autowired
	AuthorDao authorDao;
	
	public Author getAuthorById(int authorId) {
		return authorDao.getAuthorById(authorId);
	}
}
