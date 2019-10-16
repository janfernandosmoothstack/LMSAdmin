package com.lms.LMSAdmin.pojo;

public class Override {
	private Borrower borrower;
	private LibraryBranch branch;
	private Book book;
	private Integer days;
	
	public Override() {}

	public Override(Borrower borrower, LibraryBranch branch, Book book, Integer days) {
		super();
		this.borrower = borrower;
		this.branch = branch;
		this.book = book;
		this.days = days;
	}

	public Borrower getBorrower() {
		return borrower;
	}

	public void setBorrower(Borrower borrower) {
		this.borrower = borrower;
	}

	public LibraryBranch getBranch() {
		return branch;
	}

	public void setBranch(LibraryBranch branch) {
		this.branch = branch;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public Integer getDays() {
		return days;
	}

	public void setDays(Integer days) {
		this.days = days;
	}
}
