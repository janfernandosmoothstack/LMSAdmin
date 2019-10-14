package com.lms.LMSAdmin.service;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lms.LMSAdmin.dao.OverrideDao;
import com.lms.LMSAdmin.pojo.BookLoans;

@Component
public class OverrideService {
	
	@Autowired
	OverrideDao overDao;
	
	//Get current due date
	public Date getDueDate(int cardNo, int bookId, int branchId) {
		return overDao.getDueDate(cardNo, bookId, branchId);		
	}
	
	//Override due date
	public void overDueDate(int cardNo, int bookId, int branchId, Date currDueDate, int days) {
		overDao.overrideDueDate(cardNo, bookId, branchId, currDueDate, days);
	}
	
	//Validate Id's
	public boolean ifBorrExists(int cardNo, int bookId, int branchId) {
		List<BookLoans> list = overDao.getBookLoans();
		boolean exists = false;
		
		//Check if the cardNo exists
		exists = list.stream()
				.anyMatch(id -> id.getBorrower().getCardNo().equals(cardNo));
		
		if(exists == false) {
			return exists;
		} else {
			//Check if the bookId exists
			exists = list.stream()
					.anyMatch(id -> id.getBook().getBookId().equals(bookId));
			
			if(exists == false) {
				return exists;
			} else {
				//Check if the branchId exists
				exists = list.stream()
						.anyMatch(id -> id.getBranch().getBranchId().equals(branchId));
				
				if(exists = false) {
					return exists;
				}
			}
		}
	
		return exists;
	}
}
