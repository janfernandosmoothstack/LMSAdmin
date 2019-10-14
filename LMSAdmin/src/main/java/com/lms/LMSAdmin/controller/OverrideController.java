package com.lms.LMSAdmin.controller;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lms.LMSAdmin.service.OverrideService;

@RestController
@RequestMapping(value = "/LMSAdmin/overrideDueDate",
	consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}, 
	produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})

public class OverrideController {
	
	@Autowired
	OverrideService overService;
	
	//Override due date
	@RequestMapping(value = "/cardNo/{cardNo}/branchId/{branchId}/bookId/{bookId}/extraDays/{days}", 
			method = {RequestMethod.PUT, RequestMethod.GET})

	public ResponseEntity<String> overDueDate(@PathVariable("cardNo") int cardNo, @PathVariable("branchId") int branchId, 
			@PathVariable("bookId") int bookId, @PathVariable("days") int days) {
		
		boolean checkIds = overService.ifExists(cardNo, bookId, branchId);
		
		if(checkIds == true) {
			//Get the current due date
			Date currDueDate = overService.getDueDate(cardNo, bookId, branchId);
			overService.overDueDate(cardNo, bookId, branchId, currDueDate, days);
			
			return new ResponseEntity<String>("Override successful.", HttpStatus.CREATED);
		}else {
			return new ResponseEntity<String>("Invalid ID.", HttpStatus.OK);
		}
	}
}
