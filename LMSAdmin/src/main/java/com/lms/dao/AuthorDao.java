package com.lms.dao;

import com.lms.pojo.Author;

public class AuthorDao {
	public Author getAuthorById(int authorId) {
		Author author = new Author();
		
		author.setAuthorId(authorId);
		author.setAuthorName("janet");
		
		return author;
	}
}
