package com.lms.LMSAdmin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.lms.LMSAdmin.pojo.Borrower;
import com.lms.LMSAdmin.service.BorrowerService;

@RestController
public class BorrowerController {
	
	@Autowired
	BorrowerService borrService;
	
	//Create a record
	@RequestMapping(value = "/lmsAdmin/menu/5/borrMenu/1/borrName/{name}/borrAddress/{address}/borrPhone/{phone}", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseStatus(code = HttpStatus.CREATED, reason = "Borrower record created.")
	public void insertBorr(@PathVariable("name") String borrName, @PathVariable("address") String borrAddress, @PathVariable("phone") String borrPhone) {
		borrService.insertBorr(borrName, borrAddress, borrPhone);
	}
	
	//Update a record
	@RequestMapping(value = "/lmsAdmin/menu/5/borrMenu/2/cardNo/{cardNo}/borrName/{name}/borrAddress/{address}/borrPhone/{phone}", method = {RequestMethod.PUT, RequestMethod.GET})
	@ResponseStatus(code = HttpStatus.OK, reason = "Borrower record updated.")
	public void updateBorr(@PathVariable("cardNo") int cardNo, @PathVariable("name") String borrName, @PathVariable("address") String borrAddress, @PathVariable("phone") String borrPhone) {
		borrService.updateBorr(cardNo, borrName, borrAddress, borrPhone);
	}
	
	//Delete a record
	@RequestMapping(value = "/lmsAdmin/menu/5/borrMenu/3/cardNo/{cardNo}", method = {RequestMethod.DELETE, RequestMethod.GET})
	@ResponseStatus(code = HttpStatus.NO_CONTENT, reason = "Borrower record deleted.")
	public void deleteBorr(@PathVariable("cardNo") int cardNo) {
		borrService.deleteBorr(cardNo);
	}
	
	//View all records
	@RequestMapping(value = "/lmsAdmin/menu/5/borrMenu/4", method = RequestMethod.GET)
	@ResponseStatus(code = HttpStatus.OK)
	public List<Borrower> getAllBorrs() {
		return borrService.getAllBorrs();
	}
}
