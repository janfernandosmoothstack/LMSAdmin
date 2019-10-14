package com.lms.LMSAdmin.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.lms.LMSAdmin.pojo.Author;

@Component
public class AuthorDao extends Dao{
	
	//Insert author record
	public void insertAuthor(String authorName) {
		String sql = "INSERT INTO tbl_author (authorName) "
				+ "VALUE (?)";
		
		try (Connection con = getCon();
				PreparedStatement ps = con.prepareStatement(sql)){
			
			ps.setString(1, authorName);
			ps.executeUpdate();
		 	
		} catch (SQLException e) {
			System.out.println(e);
		}
	}
	
	//Update author record
	public void updateAuthor(int authorId, String authorName) {
		String sql = "UPDATE tbl_author"
				+ " SET authorName = ? "
				+ "WHERE authorId = ?";
		
		try (Connection con = getCon();
				PreparedStatement ps = con.prepareStatement(sql)){
			
			ps.setString(1, authorName);
			ps.setInt(2, authorId);
			ps.executeUpdate();
		 	
		} catch (SQLException e) {
			System.out.println(e);
		}
	}
	
	//Delete author record
	public void deleteAuthor(int authorId) {
		String sql = "DELETE FROM tbl_author"
				+ " WHERE authorId = ?";
		
		try (Connection con = getCon();
				PreparedStatement ps = con.prepareStatement(sql)){
			
			ps.setInt(1, authorId);
			ps.executeUpdate();
		 	
		} catch (SQLException e) {
			System.out.println(e);
		}
	}
	
	//Get all author records
	public List<Author> getAllAuthors() {
		List<Author> authorList = null;
		
		String sql = "SELECT authorId, authorName FROM tbl_author";
		
		try (Connection con = getCon();
				PreparedStatement ps = con.prepareStatement(sql)){
			
            ResultSet rs = ps.executeQuery();         

        	authorList = new ArrayList<Author>();
        	
            while (rs.next()) {
            	Author auth = new Author();
            	
                auth.setAuthorId(rs.getInt("authorId"));
                auth.setAuthorName(rs.getString("authorName"));
                authorList.add(auth);
            }
        } catch (SQLException e) {
        	System.out.println(e);
        }
		
		return authorList;
	}
}
