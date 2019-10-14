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
	public void insertBorr(String borrName, String borrAddress, String borrPhone) {
		borrDao.insertBorr(borrName, borrAddress, borrPhone);
	}
	
	//Update record
	public void updateBorr(int cardNo, String borrName, String borrAddress, String borrPhone) {
		borrDao.updateBorr(cardNo, borrName, borrAddress, borrPhone);
	}
	
	//Delete record
	public void deleteBorr(int cardNo) {
		borrDao.deleteBorr(cardNo);
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
