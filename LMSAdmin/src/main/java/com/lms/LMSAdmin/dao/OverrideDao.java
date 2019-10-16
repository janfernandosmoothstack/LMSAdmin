package com.lms.LMSAdmin.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lms.LMSAdmin.pojo.Book;
import com.lms.LMSAdmin.pojo.BookLoans;
import com.lms.LMSAdmin.pojo.Borrower;
import com.lms.LMSAdmin.pojo.LibraryBranch;
import com.lms.LMSAdmin.pojo.Override;

@Component
public class OverrideDao extends Dao {
	
	@Autowired
	BookDao bookDao;
	
	@Autowired
	LibraryBranchDao branchDao;
	
	@Autowired
	BorrowerDao borrDao;
	
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
			ps.setInt(3, override.getCardNo());
			ps.setInt(4, override.getBookId());
			ps.setInt(5, override.getBranchId());
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
		
			ps.setInt(1, override.getBookId());
			ps.setInt(2, override.getBranchId());
			ps.setInt(3, override.getCardNo());
			
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
		List<BookLoans> loansList = null;
		List<Book> bookList = null;
		List<LibraryBranch> branchList = null;
		List<Borrower> borrList = null;
		
		String sql = "SELECT bookId, branchId, cardNo, dateOut, dueDate FROM tbl_book_loans"; 
		
		try (Connection con = getCon();
				PreparedStatement ps = con.prepareStatement(sql)){
			
            ResultSet rs = ps.executeQuery();
            
            bookList = bookDao.getAllBooks();
            branchList = branchDao.getAllBranches();
            borrList = borrDao.getAllBorrs();

            loansList = new ArrayList<BookLoans>();
        	
            while (rs.next()) {
            	BookLoans bookLoan = new BookLoans();
            	
            	Book book = new Book();
            	int bookId = rs.getInt("bookId");
            	
            	//Getting the rest of the data for the book object
            	List<Book> bkList = bookList.stream()
            			.filter(data -> data.getBookId().equals(bookId))
            			.collect(Collectors.toList());
            	
            	book = bkList.get(0);
            	bookLoan.setBook(book);
            	
            	LibraryBranch branch = new LibraryBranch();
            	int branchId = rs.getInt("branchId");
            	
            	//Getting the rest of the data for the branch object
            	List<LibraryBranch> bchList = branchList.stream()
            			.filter(data -> data.getBranchId().equals(branchId))
            			.collect(Collectors.toList());
            	
            	branch = bchList.get(0);
            	bookLoan.setBranch(branch);
            	
            	Borrower borr = new Borrower();
            	int cardNo = rs.getInt("cardNo");
            	
            	//Getting the rest of the data for the branch object
            	List<Borrower> bwList = borrList.stream()
            			.filter(data -> data.getCardNo().equals(cardNo))
            			.collect(Collectors.toList());
            	
            	borr = bwList.get(0);
            	bookLoan.setBorrower(borr);
            	
            	bookLoan.setDateOut(rs.getDate("dateOut"));
            	bookLoan.setDueDate(rs.getDate("dueDate"));
            	
            	loansList.add(bookLoan);
            }
        } catch (SQLException e) {
        	System.out.println(e);
        }
		
		return loansList;
	}
}
