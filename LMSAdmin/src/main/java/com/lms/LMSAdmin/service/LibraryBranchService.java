package com.lms.LMSAdmin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lms.LMSAdmin.dao.LibraryBranchDao;
import com.lms.LMSAdmin.pojo.LibraryBranch;

@Component
public class LibraryBranchService {
	
	@Autowired
	LibraryBranchDao branDao;
	
	//Insert record
	public void insertBranch(LibraryBranch branch) {
		branDao.insertBranch(branch);
	}
	
	//Update record
	public void updateBranch(LibraryBranch branch) {
		branDao.updateBranch(branch);
	}
	
	//Delete record
	public void deleteBranch(LibraryBranch branch) {
		branDao.deleteBranch(branch);
	}
	
	//Get all records
	public List<LibraryBranch> getAllBranches() {
		return branDao.getAllBranches();
	}
	
	//Validate Id
	public boolean ifExists(int branchId) {
		List<LibraryBranch> list = branDao.getAllBranches();
		
		boolean exists = list.stream()
				.anyMatch(id -> id.getBranchId().equals(branchId));
	
		return exists;
	}
}
