package com.lms.LMSAdmin.controller;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.lms.LMSAdmin.service.OverrideService;

@RestController
public class OverrideController {
	
	@Autowired
	OverrideService overService;
	
	//Override due date
	@RequestMapping(value = "/lmsAdmin/menu/6/overrideDueDate/cardNo/{cardNo}/branchId/{branchId}/bookId/{bookId}/extraDays/{days}", method = {RequestMethod.PUT, RequestMethod.GET})
	@ResponseStatus(code = HttpStatus.CREATED, reason = "Override successful.")
	public void overDueDate(@PathVariable("cardNo") int cardNo, @PathVariable("branchId") int branchId, @PathVariable("bookId") int bookId, @PathVariable("days") int days) {
		//Get the current due date
		Date currDueDate = overService.getDueDate(cardNo, bookId, branchId);
		
		overService.overDueDate(cardNo, bookId, branchId, currDueDate, days);
	}
}
