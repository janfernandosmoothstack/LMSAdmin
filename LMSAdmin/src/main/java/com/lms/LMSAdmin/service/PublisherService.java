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
	public void insertPub(String pubName, String pubAddress, String pubPhone) {
		pubDao.insertPub(pubName, pubAddress, pubPhone);
	}
	
	//Update publisher record
	public void updatePub(int pubId, String pubName, String pubAddress, String pubPhone) {
		pubDao.updatePub(pubId, pubName, pubAddress, pubPhone);
	}
	
	//Delete publisher record
	public void deletePub(int pubId) {
		pubDao.deletePub(pubId);
	}
	
	//Get all publisher records
	public List<Publisher> getAllPubs() {
		return pubDao.getAllPubs();
	}
	
	//Validate publisher Id
	public boolean ifExists(int pubId) {
		List<Publisher> list = pubDao.getAllPubs();
		
		boolean exists = list.stream()
				.anyMatch(id -> id.getPublisherId().equals(pubId));
	
		return exists;
	}
}
