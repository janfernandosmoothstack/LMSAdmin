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
	public void insertBranch(String branchName, String branchAddress) {
		branDao.insertBranch(branchName, branchAddress);
	}
	
	//Update record
	public void updateBranch(int branchId, String branchName, String branchAddress) {
		branDao.updateBranch(branchId, branchName, branchAddress);
	}
	
	//Dispatch books to new branch
	public void dispatchBooks(int branchId, int newBranId) {
		branDao.dispatchBooks(branchId, newBranId);
	}
	
	//Delete record
	public void deleteBranch(int branchId) {
		branDao.deleteBranch(branchId);
	}
	
	//Get all records
	public List<LibraryBranch> getAllBranches() {
		return branDao.getAllBranches();
	}
	
	//Get all available branches to dispatch books to
	public List<LibraryBranch> getAvailableBranch(int branchId){
		return branDao.getAvailableBranch(branchId);
	}
	
	//Validate Id
	public boolean ifExists(int branchId) {
		List<LibraryBranch> list = branDao.getAllBranches();
		
		boolean exists = list.stream()
				.anyMatch(id -> id.getBranchId().equals(branchId));
	
		return exists;
	}
}
