package com.lms.LMSAdmin.dao;

import com.lms.LMSAdmin.pojo.Author;

public class AuthorDao {
	public Author getAuthorById(int authorId) {
		Author author = new Author();
		
		author.setAuthorId(authorId);
		author.setAuthorName(Math.random() + "janet");
		
		return author;
	}
}
