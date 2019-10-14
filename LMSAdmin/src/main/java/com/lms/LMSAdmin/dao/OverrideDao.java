package com.lms.LMSAdmin.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.lms.LMSAdmin.pojo.Book;
import com.lms.LMSAdmin.pojo.BookLoans;
import com.lms.LMSAdmin.pojo.Borrower;
import com.lms.LMSAdmin.pojo.LibraryBranch;

@Component
public class OverrideDao {
	
	//Override the due date
	public void overrideDueDate(int cardNo, int bookId, int branchId, Date currDueDate, int days) {
		String sql = "UPDATE tbl_book_loans "
				+ " SET dueDate = DATE_ADD(?, INTERVAL ? DAY) "
				+ "WHERE cardNo = ? "
				+ "AND bookId = ? "
				+ "AND branchID = ?";
		
		try (Connection con = getCon();
				PreparedStatement ps = con.prepareStatement(sql)){
			
			ps.setDate(1, currDueDate);
			ps.setInt(2, days);
			ps.setInt(3, cardNo);
			ps.setInt(4, bookId);
			ps.setInt(5, branchId);
			ps.executeUpdate();
		 	
		} catch (SQLException e) {
			System.out.println(e);
		}
	}
	
	//Get the current due date
	public Date getDueDate(int cardNo, int bookId, int branchId) {
		Date dueDate = null;
		
		String sql = "SELECT dueDate FROM tbl_book_loans "
				+ "WHERE bookId = ? "
				+ "AND branchId = ? "
				+ "AND cardNo = ?";
		
		try (Connection con = getCon();
				PreparedStatement ps = con.prepareStatement(sql)){
		
			ps.setInt(1, bookId);
			ps.setInt(2, branchId);
			ps.setInt(3, cardNo);
			
	        ResultSet rs = ps.executeQuery();
	        
	        while(rs.next()) {
	        	dueDate = rs.getDate("dueDate");
	        }
	        
		} catch (SQLException e) {
			System.out.println(e);
		}
		
		return dueDate;
	}
	
	//Get all records
	public List<BookLoans> getBookLoans() {
		List<BookLoans> list = null;
		
		String sql = "SELECT bookId, branchId, cardNo, dateOut, dueDate FROM tbl_book_loans"; 
		
		try (Connection con = getCon();
				PreparedStatement ps = con.prepareStatement(sql)){
			
            ResultSet rs = ps.executeQuery();         

            list = new ArrayList<BookLoans>();
        	
            while (rs.next()) {
            	BookLoans bookLoan = new BookLoans();
            	
            	Book book = new Book();
            	book.setBookId(rs.getInt("bookId"));
            	bookLoan.setBook(book);
            	
            	LibraryBranch lb = new LibraryBranch();
            	lb.setBranchId(rs.getInt("branchId"));
            	bookLoan.setBranch(lb);
            	
            	Borrower borr = new Borrower();
            	borr.setCardNo(rs.getInt("cardNo"));
            	bookLoan.setBorrower(borr);
            	
            	bookLoan.setDateOut(rs.getDate("dateOut"));
            	bookLoan.setDueDate(rs.getDate("dueDate"));
            	
            	list.add(bookLoan);
            }
        } catch (SQLException e) {
        	System.out.println(e);
        }
		
		return list;
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
