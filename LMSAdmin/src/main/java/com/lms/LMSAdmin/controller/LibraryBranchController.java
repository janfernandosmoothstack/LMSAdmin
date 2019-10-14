package com.lms.LMSAdmin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.lms.LMSAdmin.pojo.LibraryBranch;
import com.lms.LMSAdmin.service.LibraryBranchService;

@RestController
@RequestMapping("/LMSAdmin/libraryBranch")
public class LibraryBranchController {
	
	@Autowired
	LibraryBranchService branService;
	
	//Create a record
	@RequestMapping(value = "/create/branchName/{name}/branchAddress/{address}", 
			method = {RequestMethod.POST, RequestMethod.GET})
	
	public ResponseEntity<String> insertBranch(@PathVariable("name") String branchName, @PathVariable("address") String branchAddress) {
		
		branService.insertBranch(branchName, branchAddress);
		return new ResponseEntity<String>("Library Branch record created.", HttpStatus.CREATED);
	}
	
	//Update a record
	@RequestMapping(value = "/update/branchId/{branchId}/branchName/{name}/branchAddress/{address}", 
			method = {RequestMethod.PUT, RequestMethod.GET})
	
	public ResponseEntity<String> updateBranch(@PathVariable("branchId") int branchId, @PathVariable("name") String branchName, 
			@PathVariable("address") String branchAddress) {
		
		boolean checkId = branService.ifExists(branchId);
		
		if(checkId == true) {
			branService.updateBranch(branchId, branchName, branchAddress);
			return new ResponseEntity<String>("Library Branch record updated.", HttpStatus.ACCEPTED);
		}else {
			return new ResponseEntity<String>("Invalid ID.", HttpStatus.NOT_FOUND);
		}
	}
	
	//View all available branches to dispatch books to
	@RequestMapping(value = "/dispatchDelete/branchId/{branchId}/newBranchId", method = RequestMethod.GET)
	@ResponseStatus(code = HttpStatus.OK)
	public List<LibraryBranch> getAvailableBranch(@PathVariable("branchId") int branchId) {
		return branService.getAvailableBranch(branchId);
	}
	
	//Dispatch books then delete branch
	@RequestMapping(value = "/dispatchDelete/branchId/{branchId}//newBranchId/{newBranId}", 
			method = {RequestMethod.DELETE, RequestMethod.GET})
	
	public ResponseEntity<String> dispatchDeleteBranch(@PathVariable("branchId") int branchId, @PathVariable("newBranId") int newBranId) {
		
		boolean checkId = branService.ifExists(branchId);
		
		if(checkId == true) {
			branService.dispatchBooks(branchId, newBranId);
			branService.deleteBranch(branchId);
			return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
		}else {
			return new ResponseEntity<String>("Invalid ID.", HttpStatus.NOT_FOUND);
		}
	}
	
	//Delete branch
	@RequestMapping(value = "/delete/libBranMenu/3/branchId/{branchId}", 
			method = {RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.GET})
	
	public ResponseEntity<String> deleteBranch(@PathVariable("branchId") int branchId) {
		boolean checkId = branService.ifExists(branchId);
		
		if(checkId == true) {
			branService.deleteBranch(branchId);
			return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
		}else {
			return new ResponseEntity<String>("Invalid ID.", HttpStatus.NOT_FOUND);
		}
	}
	
	//View all records
	@RequestMapping(value = "/view", method = RequestMethod.GET)
	@ResponseStatus(code = HttpStatus.OK)
	public List<LibraryBranch> getAllBranches() {
		return branService.getAllBranches();
	}
}
