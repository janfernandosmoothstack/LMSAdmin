package com.lms.LMSAdmin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lms.LMSAdmin.pojo.Author;
import com.lms.LMSAdmin.service.AuthorService;

@RestController
public class AuthorController {
	
	@Autowired
	AuthorService authorService;
	
	@RequestMapping(value = "/admin/author/{authorId}", method=RequestMethod.POST)
	public Author getAuthorById(@PathVariable int authorId) {
		return authorService.getAuthorById(authorId);
	}
}