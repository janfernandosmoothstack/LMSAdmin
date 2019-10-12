package com.lms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lms.pojo.Author;
import com.lms.service.AuthorService;

@RestController
public class AuthorController {
	
	@Autowired
	AuthorService authorService;
	
	@RequestMapping("/libsys/author/{authorId}")
	public Author getAuthorById(@PathVariable int authorId) {
		return authorService.getAuthorById(authorId);
	}
}
