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

import com.lms.LMSAdmin.pojo.Publisher;
import com.lms.LMSAdmin.service.PublisherService;

@RestController
@RequestMapping("/LMSAdmin/publishers")
@Produces({"application/xml", "application/json"})
@Consumes({"application/xml", "application/json"})
public class PublisherController {
	
	@Autowired
	PublisherService pubService;
	
	//Create a record
	@PostMapping("/pubName/{publisherName}/pubAddress/{publisherAddress}/pubPhone/{publisherPhone}")
	public ResponseEntity<?> insertPub(@RequestBody Publisher publisher) {
		
		pubService.insertPub(publisher);
		return new ResponseEntity<Publisher>(HttpStatus.CREATED);
	}
	
	//Update a record
	@PutMapping("/pubId/{publisherId}/pubName/{publisherName}/pubAddress/{publisherAddress}/pubPhone/{publisherPhone}")
	public ResponseEntity<?> updatePub(@RequestBody Publisher publisher) {
		
		boolean checkId = pubService.ifExists(publisher.getPublisherId());
		
		if(checkId == true) {
			pubService.updatePub(publisher);
			return new ResponseEntity<Publisher>(HttpStatus.OK);
		}else {
			return new ResponseEntity<Publisher>(HttpStatus.NOT_FOUND);
		}
	}
	
	//Delete a record
	@DeleteMapping("/pubId/{publisherId}")
	public ResponseEntity<?> deletePub(@RequestBody Publisher publisher) {
		
		boolean checkId = pubService.ifExists(publisher.getPublisherId());
		
		if(checkId == true) {
			pubService.deletePub(publisher);
			return new ResponseEntity<Publisher>(HttpStatus.NO_CONTENT);
		}else {
			return new ResponseEntity<Publisher>(HttpStatus.NOT_FOUND);
		}
	}
	
	//Get all records
	@GetMapping("")
	@ResponseStatus(code = HttpStatus.OK)
	public List<Publisher> getAllPubs() {
		return pubService.getAllPubs();
	}
}
