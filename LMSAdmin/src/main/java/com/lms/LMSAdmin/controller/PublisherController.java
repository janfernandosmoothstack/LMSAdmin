package com.lms.LMSAdmin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.lms.LMSAdmin.pojo.Publisher;
import com.lms.LMSAdmin.service.PublisherService;

@RestController
public class PublisherController {

	@Autowired
	PublisherService pubService;
	
	//Create a record
	@RequestMapping(value = "/lmsAdmin/menu/3/pubMenu/1/pubName/{publisherName}/pubAddress/{publisherAddress}/pubPhone/{publisherPhone}", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseStatus(code = HttpStatus.CREATED, reason = "Publisher record created.")
	public void insertPub(@PathVariable("publisherName") String pubName, @PathVariable("publisherAddress") String pubAddress, @PathVariable("publisherPhone") String pubPhone) {
		pubService.insertPub(pubName, pubAddress, pubPhone);
	}
	
	//Update a record
	@RequestMapping(value = "/lmsAdmin/menu/3/pubMenu/2/pubId/{publisherId}/pubName/{publisherName}/pubAddress/{publisherAddress}/pubPhone/{publisherPhone}", method = {RequestMethod.PUT, RequestMethod.GET})
	@ResponseStatus(code = HttpStatus.OK, reason = "Publisher record updated.")
	public void updatePub(@PathVariable("publisherId") int pubId, @PathVariable("publisherName") String pubName, @PathVariable("publisherAddress") String pubAddress, @PathVariable("publisherPhone") String pubPhone) {
		pubService.updatePub(pubId, pubName, pubAddress, pubPhone);
	}
	
	//Delete a record
	@RequestMapping(value = "/lmsAdmin/menu/3/pubMenu/3/pubId/{publisherId}", method = {RequestMethod.DELETE, RequestMethod.GET})
	@ResponseStatus(code = HttpStatus.NO_CONTENT, reason = "Publisher record deleted.")
	public void deletePub(@PathVariable("publisherId") int pubId) {
		pubService.deletePub(pubId);
	}
	
	//View all records
	@RequestMapping(value = "/lmsAdmin/menu/3/pubMenu/4", method = RequestMethod.GET)
	@ResponseStatus(code = HttpStatus.OK)
	public List<Publisher> getAllPubs() {
		return pubService.getAllPubs();
	}
}
