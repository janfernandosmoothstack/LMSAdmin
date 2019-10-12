package com.lms.ss.dao;

import com.lms.ss.pojo.Author;

public class AuthorDao {
	public Author getAuthorById(int authorId) {
		Author author = new Author();
		
		author.setAuthorId(authorId);
		author.setAuthorName(Math.random() + "janet");
		
		return author;
	}
}
