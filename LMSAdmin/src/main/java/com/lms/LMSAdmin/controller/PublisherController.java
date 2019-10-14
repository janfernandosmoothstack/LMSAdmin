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

import com.lms.LMSAdmin.pojo.Publisher;
import com.lms.LMSAdmin.service.PublisherService;

@RestController
@RequestMapping("/lmsAdmin/publisher")
public class PublisherController {

	@Autowired
	PublisherService pubService;
	
	//Create a record
	@RequestMapping(value = "/create/pubName/{publisherName}/pubAddress/{publisherAddress}/pubPhone/{publisherPhone}", 
			method = {RequestMethod.POST, RequestMethod.GET})
	
	public ResponseEntity<String> insertPub(@PathVariable("publisherName") String pubName, 
			@PathVariable("publisherAddress") String pubAddress, @PathVariable("publisherPhone") String pubPhone) {
		
		pubService.insertPub(pubName, pubAddress, pubPhone);
		return new ResponseEntity<String>("Publisher record created.", HttpStatus.CREATED);
	}
	
	//Update a record
	@RequestMapping(value = "/update/pubId/{publisherId}/pubName/{publisherName}/pubAddress/{publisherAddress}/pubPhone/{publisherPhone}", 
			method = {RequestMethod.PUT, RequestMethod.GET})
	
	public ResponseEntity<String> updatePub(@PathVariable("publisherId") int pubId, @PathVariable("publisherName") String pubName, 
			@PathVariable("publisherAddress") String pubAddress, @PathVariable("publisherPhone") String pubPhone) {
		
		boolean checkId = pubService.ifExists(pubId);
		
		if(checkId == true) {
			pubService.updatePub(pubId, pubName, pubAddress, pubPhone);
			return new ResponseEntity<String>("Publisher record updated.", HttpStatus.ACCEPTED);
		}else {
			return new ResponseEntity<String>("Invalid ID.", HttpStatus.NOT_FOUND);
		}
	}
	
	//Delete a record
	@RequestMapping(value = "/delete/pubId/{publisherId}", method = {RequestMethod.DELETE, RequestMethod.GET})
	public ResponseEntity<String> deletePub(@PathVariable("publisherId") int pubId) {
		
		boolean checkId = pubService.ifExists(pubId);
		
		if(checkId == true) {
			pubService.deletePub(pubId);
			return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
		}else {
			return new ResponseEntity<String>("Invalid ID.", HttpStatus.NOT_FOUND);
		}
	}
	
	//View all records
	@RequestMapping(value = "/view", method = RequestMethod.GET)
	@ResponseStatus(code = HttpStatus.OK)
	public List<Publisher> getAllPubs() {
		return pubService.getAllPubs();
	}
}
