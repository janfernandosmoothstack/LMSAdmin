package com.lms.LMSAdmin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.lms.LMSAdmin.pojo.LibraryBranch;
import com.lms.LMSAdmin.service.LibraryBranchService;

@RestController
public class LibraryBranchController {
	
	@Autowired
	LibraryBranchService branService;
	
	//Create a record
	@RequestMapping(value = "/lmsAdmin/menu/4/libBranMenu/1/branchName/{name}/branchAddress/{address}", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseStatus(code = HttpStatus.CREATED, reason = "Library Branch record created.")
	public void insertBranch(@PathVariable("name") String branchName, @PathVariable("address") String branchAddress) {
		branService.insertBranch(branchName, branchAddress);
	}
	
	//Update a record
	@RequestMapping(value = "/lmsAdmin/menu/4/libBranMenu/2/branchId/{branchId}/branchName/{name}/branchAddress/{address}", method = {RequestMethod.PUT, RequestMethod.GET})
	@ResponseStatus(code = HttpStatus.OK, reason = "Library Branch record updated.")
	public void updateBranch(@PathVariable("branchId") int branchId, @PathVariable("name") String branchName, @PathVariable("address") String branchAddress) {
		branService.updateBranch(branchId, branchName, branchAddress);
	}
	
	//View all available branches to dispatch books to
	@RequestMapping(value = "/lmsAdmin/menu/4/libBranMenu/3/branchId/{branchId}/deleteOpt/1/newBranchId", method = RequestMethod.GET)
	@ResponseStatus(code = HttpStatus.OK)
	public List<LibraryBranch> getAvailableBranch(@PathVariable("branchId") int branchId) {
		return branService.getAvailableBranch(branchId);
	}
	
	//Dispatch books then delete branch
	@RequestMapping(value = "/lmsAdmin/menu/4/libBranMenu/3/branchId/{branchId}/deleteOpt/1/newBranchId/{newBranId}", method = {RequestMethod.DELETE, RequestMethod.GET})
	@ResponseStatus(code = HttpStatus.NO_CONTENT, reason = "Library Branch record deleted.")
	public void dispatchDeleteBranch(@PathVariable("branchId") int branchId, @PathVariable("newBranId") int newBranId) {
		branService.dispatchBooks(branchId, newBranId);
		branService.deleteBranch(branchId);
	}
	
	//Delete branch
	@RequestMapping(value = "/lmsAdmin/menu/4/libBranMenu/3/branchId/{branchId}/deleteOpt/2", method = {RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.GET})
	@ResponseStatus(code = HttpStatus.NO_CONTENT, reason = "Library Branch record deleted.")
	public void deleteBranch(@PathVariable("branchId") int branchId) {
		branService.deleteBranch(branchId);
	}
	
	//View all records
	@RequestMapping(value = "/lmsAdmin/menu/4/libBranMenu/4", method = RequestMethod.GET)
	@ResponseStatus(code = HttpStatus.OK)
	public List<LibraryBranch> getAllBranches() {
		return branService.getAllBranches();
	}
}
