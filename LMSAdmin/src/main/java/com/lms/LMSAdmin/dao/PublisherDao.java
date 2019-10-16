package com.lms.LMSAdmin.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.lms.LMSAdmin.pojo.Publisher;

@Component
public class PublisherDao extends Dao {
	
	//Insert Pub record
	public void insertPub(Publisher publisher) {
		String sql = "INSERT INTO tbl_publisher (publisherName, publisherAddress, publisherPhone) "
				+ "VALUES (?, ?, ?)";
		
		try (Connection con = getCon();
				PreparedStatement ps = con.prepareStatement(sql)){
			
			ps.setString(1, publisher.getPublisherName());
			ps.setString(2, publisher.getPublisherAddress());
			ps.setString(3, publisher.getPublisherPhone());
			ps.executeUpdate();
		 	
		} catch (SQLException e) {
			System.out.println(e);
		}
	}
	
	//Update pub record
	public void updatePub(Publisher publisher) {
		String sql = "UPDATE tbl_publisher"
				+ " SET publisherName = ?, publisherAddress = ?, publisherPhone = ? "
				+ "WHERE publisherId = ?";
		
		try (Connection con = getCon();
				PreparedStatement ps = con.prepareStatement(sql)){

			ps.setString(1, publisher.getPublisherName());
			ps.setString(2, publisher.getPublisherAddress());
			ps.setString(3, publisher.getPublisherPhone());
			ps.setInt(4, publisher.getPublisherId());
			ps.executeUpdate();
		 	
		} catch (SQLException e) {
			System.out.println(e);
		}
	}
	
	//Delete pub record
	public void deletePub(Publisher publisher) {
		String sql = "DELETE FROM tbl_publisher"
				+ " WHERE publisherId = ?";
		
		try (Connection con = getCon();
				PreparedStatement ps = con.prepareStatement(sql)){
			
			ps.setInt(1, publisher.getPublisherId());
			ps.executeUpdate();
		 	
		} catch (SQLException e) {
			System.out.println(e);
		}
	}
	
	//Get all pub records
	public List<Publisher> getAllPubs() {
		List<Publisher> pubList = null;
		
		String sql = "SELECT publisherId, publisherName, publisherAddress, publisherPhone "
				+ "FROM tbl_publisher";
		
		try (Connection con = getCon();
				PreparedStatement ps = con.prepareStatement(sql)){
			
            ResultSet rs = ps.executeQuery();  

        	pubList = new ArrayList<Publisher>();
        	
            while (rs.next()) {
            	Publisher pub = new Publisher();
            	
            	pub.setPublisherId(rs.getInt("publisherId"));
            	pub.setPublisherName(rs.getString("publisherName"));
            	pub.setPublisherAddress(rs.getString("publisherAddress"));
            	pub.setPublisherPhone(rs.getString("publisherPhone"));
                pubList.add(pub);
            }
        } catch (SQLException e) {
        	System.out.println(e);
        }
		
		return pubList;
	}
}
