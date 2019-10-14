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
	public void insertPub(String pubName, String pubAddress, String pubPhone) {
		String sql = "INSERT INTO tbl_publisher (publisherName, publisherAddress, publisherPhone) "
				+ "VALUES (?, ?, ?)";
		
		try (Connection con = getCon();
				PreparedStatement ps = con.prepareStatement(sql)){
			
			ps.setString(1, pubName);
			ps.setString(2, pubAddress);
			ps.setString(3, pubPhone);
			ps.executeUpdate();
		 	
		} catch (SQLException e) {
			System.out.println(e);
		}
	}
	
	//Update pub record
	public void updatePub(int pubId, String pubName, String pubAddress, String pubPhone) {
		String sql = "UPDATE tbl_publisher"
				+ " SET publisherName = ?, publisherAddress = ?, publisherPhone = ? "
				+ "WHERE publisherId = ?";
		
		try (Connection con = getCon();
				PreparedStatement ps = con.prepareStatement(sql)){

			ps.setString(1, pubName);
			ps.setString(2, pubAddress);
			ps.setString(3, pubPhone);
			ps.setInt(4, pubId);
			ps.executeUpdate();
		 	
		} catch (SQLException e) {
			System.out.println(e);
		}
	}
	
	//Delete pub record
	public void deletePub(int pubId) {
		String sql = "DELETE FROM tbl_publisher"
				+ " WHERE publisherId = ?";
		
		try (Connection con = getCon();
				PreparedStatement ps = con.prepareStatement(sql)){
			
			ps.setInt(1, pubId);
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
