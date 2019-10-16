package com.lms.LMSAdmin.controller;

import java.sql.Date;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.lms.LMSAdmin.service.OverrideService;
import com.lms.LMSAdmin.pojo.BookLoans;
import com.lms.LMSAdmin.pojo.Override;

@RestController
@RequestMapping("/LMSAdmin")
@Produces({"application/xml", "application/json"})
@Consumes({"application/xml", "application/json"})
public class OverrideController {

	@Autowired
	OverrideService overService;
	
	//Override due date
	@PutMapping("/duedate/cardNo/{cardNo}/branchId/{branchId}/bookId/{bookId}/extraDays/{days}")
	public ResponseEntity<?> overDueDate(@RequestBody Override override) {
		
		boolean checkIds = overService.ifExists(override.getCardNo(), override.getBranchId(), override.getBookId());
		
		if(checkIds == true) {
			//Get the current due date
			Date currDueDate = overService.getDueDate(override);
			overService.overDueDate(override, currDueDate);
			
			return new ResponseEntity<Override>(HttpStatus.OK);
		}else {
			return new ResponseEntity<Override>(HttpStatus.NOT_FOUND);
		}
	}
	
	
	//Get all book loans
	@GetMapping("loans")
	@ResponseStatus(code = HttpStatus.OK)
	public List<BookLoans> getBookLoans() {
		return overService.getBookLoans();
	}
}
