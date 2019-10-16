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
	public void insertBranch(LibraryBranch branch) {
		String sql = "INSERT INTO tbl_library_branch (branchName, branchAddress) "
				+ "VALUES (?, ?)";
		
		try (Connection con = getCon(); 
				PreparedStatement ps = con.prepareStatement(sql)){
			
			ps.setString(1, branch.getBranchName());
			ps.setString(2, branch.getBranchAddress());
			ps.executeUpdate();
		 	
		} catch (SQLException e) {
			System.out.println(e);
		}
	}
	
	//Update record
	public void updateBranch(LibraryBranch branch) {
		String sql = "UPDATE tbl_library_branch"
				+ " SET branchName = ?, branchAddress = ? "
				+ "WHERE branchId = ?";
		
		try (Connection con = getCon(); 
				PreparedStatement ps = con.prepareStatement(sql)){
			
			ps.setString(1, branch.getBranchName());
			ps.setString(2, branch.getBranchAddress());
			ps.setInt(3, branch.getBranchId());
			ps.executeUpdate();
		 	
		} catch (SQLException e) {
			System.out.println(e);
		}
	}
	
	//Delete record
	public void deleteBranch(LibraryBranch branch) {
		String sql = "DELETE FROM tbl_library_branch"
				+ " WHERE branchId = ?";
		
		try (Connection con = getCon();
				PreparedStatement ps = con.prepareStatement(sql)){
			
			ps.setInt(1, branch.getBranchId());
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
}
