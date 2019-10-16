package com.lms.LMSAdmin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lms.LMSAdmin.dao.BorrowerDao;
import com.lms.LMSAdmin.pojo.Borrower;

@Component
public class BorrowerService {
	
	@Autowired
	BorrowerDao borrDao;
	
	//Insert record
	public void insertBorr(Borrower borrower) {
		borrDao.insertBorr(borrower);
	}
	
	//Update record
	public void updateBorr(Borrower borrower) {
		borrDao.updateBorr(borrower);
	}
	
	//Delete record
	public void deleteBorr(Borrower borrower) {
		borrDao.deleteBorr(borrower);
	}
	
	//Get all records
	public List<Borrower> getAllBorrs() {
		return borrDao.getAllBorrs();
	}
	
	//Validate Id
	public boolean ifExists(int cardNo) {
		List<Borrower> list = borrDao.getAllBorrs();
		
		boolean exists = list.stream()
				.anyMatch(id -> id.getCardNo().equals(cardNo));
	
		return exists;
	}
}
