package com.lms.LMSAdmin.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.lms.LMSAdmin.pojo.Borrower;

@Component
public class BorrowerDao extends Dao{
	
	//Insert Borrower record
	public void insertBorr(Borrower borrower) {
		String sql = "INSERT INTO tbl_borrower (name, address, phone) "
				+ "VALUES (?, ?, ?)";
		
		try (Connection con = getCon(); 
				PreparedStatement ps = con.prepareStatement(sql)){
			
			ps.setString(1, borrower.getName());
			ps.setString(2, borrower.getAddress());
			ps.setString(3, borrower.getAddress());
			ps.executeUpdate();
		 	
		} catch (SQLException e) {
			System.out.println(e);
		} 
	}
	
	//Update record
	public void updateBorr(Borrower borrower) {
		String sql = "UPDATE tbl_borrower"
				+ " SET name = ?, address = ?, phone = ? "
				+ "WHERE cardNo = ?";
		
		try (Connection con = getCon(); 
				PreparedStatement ps = con.prepareStatement(sql)){
			
			ps.setString(1, borrower.getName());
			ps.setString(2, borrower.getAddress());
			ps.setString(3, borrower.getPhone());
			ps.setInt(4, borrower.getCardNo());
			ps.executeUpdate();
		 	
		} catch (SQLException e) {
			System.out.println(e);
		}
	}
	
	//Delete record
	public void deleteBorr(Borrower borrower) {
		String sql = "DELETE FROM tbl_borrower"
				+ " WHERE cardNo = ?";
		
		try (Connection con = getCon();
				PreparedStatement ps = con.prepareStatement(sql)){
			
			ps.setInt(1, borrower.getCardNo());
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
}
