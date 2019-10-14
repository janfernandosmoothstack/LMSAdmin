package com.lms.LMSAdmin.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.lms.LMSAdmin.pojo.Borrower;

@Component
public class BorrowerDao {
	
	//Insert Borrower record
	public void insertBorr(String borrName, String borrAddress, String borrPhone) {
		String sql = "INSERT INTO tbl_borrower (name, address, phone) "
				+ "VALUES (?, ?, ?)";
		
		try (Connection con = getCon(); 
				PreparedStatement ps = con.prepareStatement(sql)){
			
			ps.setString(1, borrName);
			ps.setString(2, borrAddress);
			ps.setString(3, borrPhone);
			ps.executeUpdate();
		 	
		} catch (SQLException e) {
			System.out.println(e);
		} 
	}
	
	//Update record
	public void updateBorr(int cardNo, String name, String address, String phone) {
		String sql = "UPDATE tbl_borrower"
				+ " SET name = ?, address = ?, phone = ? "
				+ "WHERE cardNo = ?";
		
		try (Connection con = getCon(); 
				PreparedStatement ps = con.prepareStatement(sql)){
			
			ps.setString(1, name);
			ps.setString(2, address);
			ps.setString(3, phone);
			ps.setInt(4, cardNo);
			ps.executeUpdate();
		 	
		} catch (SQLException e) {
			System.out.println(e);
		}
	}
	
	//Delete record
	public void deleteBorr(int cardNo) {
		String sql = "DELETE FROM tbl_borrower"
				+ " WHERE cardNo = ?";
		
		try (Connection con = getCon();
				PreparedStatement ps = con.prepareStatement(sql)){
			
			ps.setInt(1, cardNo);
			ps.executeUpdate();
		 	
		} catch (SQLException e) {
			System.out.println(e);
		}
	}
	
	//Get all records
	public List<Borrower> getAllBorrs() {
		List<Borrower> borrList = null;
		
		String sql = "SELECT cardNo, name, address, phone FROM tbl_borrower";
		
		try (Connection con = getCon(); 
				PreparedStatement ps = con.prepareStatement(sql)){
	
            ResultSet rs = ps.executeQuery();         

            borrList = new ArrayList<Borrower>();
        	
            while (rs.next()) {
            	Borrower borr = new Borrower();
            	
            	borr.setCardNo(rs.getInt("cardNo"));
            	borr.setName(rs.getString("name"));
            	borr.setAddress(rs.getString("address"));
            	borr.setPhone(rs.getString("phone"));
            	borrList.add(borr);
            }
        } catch (SQLException e) {
        	System.out.println(e);
        }
		
		return borrList;
	}
	
	public Connection getCon() {
		Connection con = null;
		
		try {
            con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root","Iamsherlocked#2.0");
            
  		} catch(SQLException e) {
			System.out.println(e);
        } 	
		
		return con;
	}
}
