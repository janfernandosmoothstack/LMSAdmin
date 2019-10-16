package com.lms.LMSAdmin.service;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lms.LMSAdmin.dao.OverrideDao;
import com.lms.LMSAdmin.pojo.BookLoans;
import com.lms.LMSAdmin.pojo.Override;

@Component
public class OverrideService {
	
	@Autowired
	OverrideDao overDao;
	
	//Get current due date
	public Date getDueDate(Override override) {
		return overDao.getDueDate(override);		
	}
	
	//Override due date
	public void overDueDate(Override override, Date currDueDate) {
		overDao.overrideDueDate(override, currDueDate);
	}
	
	//Validate Id's
	public boolean ifExists(int cardNo, int bookId, int branchId) {
		List<BookLoans> list = overDao.getBookLoans();
		boolean exists = false;
		
		//Check if the cardNo exists
		exists = list.stream()
				.anyMatch(id -> id.getBorrower().getCardNo().equals(cardNo) &&
						id.getBranch().getBranchId().equals(branchId) &&
						id.getBook().getBookId().equals(bookId));
	
		return exists;
	}
}
