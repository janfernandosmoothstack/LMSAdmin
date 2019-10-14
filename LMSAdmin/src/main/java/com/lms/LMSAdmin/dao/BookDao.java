package com.lms.LMSAdmin.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lms.LMSAdmin.pojo.Author;
import com.lms.LMSAdmin.pojo.Book;
import com.lms.LMSAdmin.pojo.Publisher;

@Component
public class BookDao extends Dao{
	
	@Autowired
	AuthorDao authDao;
	
	@Autowired
	PublisherDao pubDao;
	
	//Insert record
	public void insertBook(String title, int authId, int pubId) {
		String sql = "INSERT INTO tbl_book (title, authId, pubId) "
				+ "VALUES (?, ?, ?)";
		
		try (Connection con = getCon(); 
				PreparedStatement ps = con.prepareStatement(sql)){

			ps.setString(1, title);
			ps.setInt(2, authId);
			ps.setInt(3, pubId);
			ps.executeUpdate();
		 	
		} catch (SQLException e) {
			System.out.println(e);
		}
	}
	
	//Update record
	public void updateBook(int bookId, String title, int authId, int pubId) {
		String sql = "UPDATE tbl_book"
				+ " SET title = ?, authId = ?, pubId = ? "
				+ "WHERE bookId = ?";
		
		try (Connection con = getCon(); 
				PreparedStatement ps = con.prepareStatement(sql)){
			
			ps.setString(1, title);
			ps.setInt(2, authId);
			ps.setInt(3, authId);
			ps.setInt(4, bookId);
			ps.executeUpdate();
		 	
		} catch (SQLException e) {
			System.out.println(e);
		}
	}
	
	//Delete record
	public void deleteBook(int bookId) {
		String sql = "DELETE FROM tbl_book"
				+ " WHERE bookId = ?";
		
		try (Connection con = getCon();
				PreparedStatement ps = con.prepareStatement(sql)){
			
			ps.setInt(1, bookId);
			ps.executeUpdate();
		 	
		} catch (SQLException e) {
			System.out.println(e);
		}
	}
	
	//Get all records
	public List<Book> getAllBooks() {
		List<Book> bookList = null;
		List<Author> authList = null;
		List<Publisher> pubList = null;
		
		String sql = "SELECT bookId, title, authId, pubId FROM tbl_book";
		
		try (Connection con = getCon();
				PreparedStatement ps = con.prepareStatement(sql)){
			
            ResultSet rs = ps.executeQuery();         

        	bookList = new ArrayList<Book>();
        	authList = authDao.getAllAuthors();
        	pubList = pubDao.getAllPubs();
        	
            while (rs.next()) {
            	Book book = new Book();
            	book.setBookId(rs.getInt("bookId"));
            	book.setTitle(rs.getString("title"));
            	
            	Author author = new Author();
            	int authId = rs.getInt("authId");
            	
            	//Getting the rest of the data for the author object
            	List<Author> aList = authList.stream()
            			.filter(data -> data.getAuthorId().equals(authId))
            			.collect(Collectors.toList());
            	
            	author = aList.get(0);	
            	book.setAuthor(author);
            	
            	Publisher pub = new Publisher();
            	int pubId = rs.getInt("pubId");
            	
            	//Getting the rest of the data for the publisher object
            	List<Publisher> pList = pubList.stream()
            			.filter(data -> data.getPublisherId().equals(pubId))
            			.collect(Collectors.toList());
            	
            	pub = pList.get(0);
            	book.setPublisher(pub);
            	
            	bookList.add(book);
            }
        } catch (SQLException e) {
        	System.out.println(e);
        }
		
		return bookList;
	}
}
