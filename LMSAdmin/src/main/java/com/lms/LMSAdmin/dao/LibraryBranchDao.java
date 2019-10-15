package com.lms.LMSAdmin.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.lms.LMSAdmin.pojo.LibraryBranch;

@Component
public class LibraryBranchDao extends Dao {
	
	//Insert record
	public void insertBranch(String branchName, String branchAddress) {
		String sql = "INSERT INTO tbl_library_branch (branchName, branchAddress) "
				+ "VALUES (?, ?)";
		
		try (Connection con = getCon(); 
				PreparedStatement ps = con.prepareStatement(sql)){
			
			ps.setString(1, branchName);
			ps.setString(2, branchAddress);
			ps.executeUpdate();
		 	
		} catch (SQLException e) {
			System.out.println(e);
		}
	}
	
	//Update record
	public void updateBranch(int branchId, String branchName, String branchAddress) {
		String sql = "UPDATE tbl_library_branch"
				+ " SET branchName = ?, branchAddress = ? "
				+ "WHERE branchId = ?";
		
		try (Connection con = getCon(); 
				PreparedStatement ps = con.prepareStatement(sql)){
			
			ps.setString(1, branchName);
			ps.setString(2, branchAddress);
			ps.setInt(3, branchId);
			ps.executeUpdate();
		 	
		} catch (SQLException e) {
			System.out.println(e);
		}
	}
	
	//Delete record
	public void deleteBranch(int branchId) {
		String sql = "DELETE FROM tbl_library_branch"
				+ " WHERE branchId = ?";
		
		try (Connection con = getCon();
				PreparedStatement ps = con.prepareStatement(sql)){
			
			ps.setInt(1, branchId);
			ps.executeUpdate();
		 	
		} catch (SQLException e) {
			System.out.println(e);
		}
	}
	
	//Get all records
	public List<LibraryBranch> getAllBranches() {
		List<LibraryBranch> libBranList = null;
		
		String sql = "SELECT branchId, branchName, branchAddress FROM tbl_library_branch";
		
		try (Connection con = getCon();
				PreparedStatement ps = con.prepareStatement(sql)){
			
            ResultSet rs = ps.executeQuery();         

            libBranList = new ArrayList<LibraryBranch>();
        	
            while (rs.next()) {
            	LibraryBranch branch = new LibraryBranch();
            	
            	branch.setBranchId(rs.getInt("branchId"));
            	branch.setBranchName(rs.getString("branchName"));
            	branch.setBranchAddress(rs.getString("branchAddress"));
                libBranList.add(branch);
            }
        } catch (SQLException e) {
        	System.out.println(e);
        }
		
		return libBranList;
	}
	
	//Change the branchId of books to the new branchId
	public void dispatchBooks(int branId, int newBranId) {
		updateBookCopies(branId, newBranId);
		updateBookLoans(branId, newBranId);
	}
	
	//Update branchId in book copies
	public void updateBookCopies(int branId, int newBranId) {
		String sql = "UPDATE tbl_book_copies"
				+ " SET branchId = ? "
				+ "WHERE branchId = ?";
		
		try (Connection con = getCon();
				PreparedStatement ps = con.prepareStatement(sql)){
			
			ps.setInt(1, newBranId);
			ps.setInt(2, branId);
			ps.executeUpdate();
		 	
		} catch (SQLException e) {
			System.out.println(e);
		}
	}
	
	//Update branchId in book loans
	public void updateBookLoans(int branId, int newBranId) {
		String sql = "UPDATE tbl_book_loans"
						+ " SET branchId = ? "
						+ "WHERE branchId = ?";
		
		try (Connection con = getCon();
				PreparedStatement ps = con.prepareStatement(sql)){
	
			ps.setInt(1, newBranId);
			ps.setInt(2, branId);
			ps.executeUpdate();
		 	
		} catch (SQLException e) {
			System.out.println(e);
		}
	}
	
	//Get available branches
	public List<LibraryBranch> getAvailableBranch(int branId) {
		List<LibraryBranch> libBranList = null;
		
		String sql = "SELECT * FROM tbl_library_branch "
				+ "WHERE branchId <> " + branId;
		
		try (Connection con = getCon();
				PreparedStatement ps = con.prepareStatement(sql)){
			
            ResultSet rs = ps.executeQuery();         

            libBranList = new ArrayList<LibraryBranch>();
        	
            while (rs.next()) {
            	LibraryBranch branch = new LibraryBranch();
            	
            	branch.setBranchId(rs.getInt("branchId"));
            	branch.setBranchName(rs.getString("branchName"));
            	branch.setBranchAddress(rs.getString("branchAddress"));
                libBranList.add(branch);
            }
        } catch (SQLException e) {
        	System.out.println(e);
        }
		
		return libBranList;
	}
}
