package com.lms.LMSAdmin;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.lms.dao.AuthorDao;
import com.lms.service.AuthorService;

@Configuration
public class BeanConfig {
	
	@Bean
	public AuthorDao getAuthorDao() {
		return new AuthorDao();
	}
	
	@Bean
	public AuthorService getAuthorService() {
		return new AuthorService();
	}
}
