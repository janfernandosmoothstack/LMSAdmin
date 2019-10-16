package com.lms.LMSAdmin.dao;

import java.sql.Connection;
import java.sql.Date;
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
import com.lms.LMSAdmin.pojo.Override;

@Component
public class OverrideDao extends Dao {
	
	//Override the due date
	public void overrideDueDate(Override override, Date currDueDate) {
		String sql = "UPDATE tbl_book_loans "
				+ " SET dueDate = DATE_ADD(?, INTERVAL ? DAY) "
				+ "WHERE cardNo = ? "
				+ "AND bookId = ? "
				+ "AND branchID = ?";
		
		try (Connection con = getCon();
				PreparedStatement ps = con.prepareStatement(sql)){
			
			ps.setDate(1, currDueDate);
			ps.setInt(2, override.getDays());
			ps.setInt(3, override.getBorrower().getCardNo());
			ps.setInt(4, override.getBook().getBookId());
			ps.setInt(5, override.getBranch().getBranchId());
			ps.executeUpdate();
		 	
		} catch (SQLException e) {
			System.out.println(e);
		}
	}
	
	//Get the current due date
	public Date getDueDate(Override override) {
		Date dueDate = null;
		
		String sql = "SELECT dueDate FROM tbl_book_loans "
				+ "WHERE bookId = ? "
				+ "AND branchId = ? "
				+ "AND cardNo = ?";
		
		try (Connection con = getCon();
				PreparedStatement ps = con.prepareStatement(sql)){
		
			ps.setInt(1, override.getBook().getBookId());
			ps.setInt(2, override.getBranch().getBranchId());
			ps.setInt(3, override.getBorrower().getCardNo());
			
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
}
