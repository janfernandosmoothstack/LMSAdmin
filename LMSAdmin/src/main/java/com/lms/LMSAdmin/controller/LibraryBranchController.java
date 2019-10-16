package com.lms.LMSAdmin.controller;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.lms.LMSAdmin.pojo.LibraryBranch;
import com.lms.LMSAdmin.service.LibraryBranchService;

@RestController
@RequestMapping("/LMSAdmin/branches")
@Produces({"application/xml", "application/json"})
@Consumes({"application/xml", "application/json"})
public class LibraryBranchController {
	
	@Autowired
	LibraryBranchService branService;
	
	//Create a record
	@PostMapping("/branchName/{branchName}/branchAddress/{branchAddress}")
	public ResponseEntity<LibraryBranch> insertBranch(@RequestBody LibraryBranch branch) {
		
		branService.insertBranch(branch);
		return new ResponseEntity<LibraryBranch>(HttpStatus.CREATED);
	}
	
	//Update a record
	@PutMapping("/branchId/{branchId}/branchName/{branchName}/branchAddress/{branchAddress}")
	public ResponseEntity<?> updateBranch(@RequestBody LibraryBranch branch) {
		
		boolean checkId = branService.ifExists(branch.getBranchId());
		
		if(checkId == true) {
			branService.updateBranch(branch);
			return new ResponseEntity<LibraryBranch>(HttpStatus.OK);
		}else {
			return new ResponseEntity<LibraryBranch>(HttpStatus.NOT_FOUND);
		}
	}
	
	//Delete branch
	@DeleteMapping("/branchId/{branchId}")
	public ResponseEntity<?> deleteBranch(@RequestBody LibraryBranch branch) {
		
		boolean checkId = branService.ifExists(branch.getBranchId());
		
		if(checkId == true) {
			branService.deleteBranch(branch);
			return new ResponseEntity<LibraryBranch>(HttpStatus.NO_CONTENT);
		}else {
			return new ResponseEntity<LibraryBranch>(HttpStatus.NOT_FOUND);
		}
	}
	
	//Get all records
	@GetMapping("")
	@ResponseStatus(code = HttpStatus.OK)
	public List<LibraryBranch> getAllBranches() {
		return branService.getAllBranches();
	}
}
