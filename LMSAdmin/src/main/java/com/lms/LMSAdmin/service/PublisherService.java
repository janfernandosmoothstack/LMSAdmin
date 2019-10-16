package com.lms.LMSAdmin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lms.LMSAdmin.dao.PublisherDao;
import com.lms.LMSAdmin.pojo.Publisher;

@Component
public class PublisherService {
	
	@Autowired
	PublisherDao pubDao;
	
	//Insert publisher record
	public void insertPub(Publisher publisher) {
		pubDao.insertPub(publisher);
	}
	
	//Update publisher record
	public void updatePub(Publisher publisher) {
		pubDao.updatePub(publisher);
	}
	
	//Delete publisher record
	public void deletePub(Publisher publisher) {
		pubDao.deletePub(publisher);
	}
	
	//Get all publisher records
	public List<Publisher> getAllPubs() {
		return pubDao.getAllPubs();
	}
	
	//Get one publisher
	public Publisher getPublisherById(int publisherId) {
		return pubDao.getPublisherById(publisherId);
	}
	
	//Validate publisher Id
	public boolean ifExists(int pubId) {
		List<Publisher> list = pubDao.getAllPubs();
		
		boolean exists = list.stream()
				.anyMatch(id -> id.getPublisherId().equals(pubId));
	
		return exists;
	}
}
