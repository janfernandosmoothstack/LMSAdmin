package com.lms.LMSAdmin.pojo;

public class Override {
	private Integer cardNo;
	private Integer branchId;
	private Integer bookId;
	private Integer days;
	
	public Override() {}

	public Override(Integer cardNo, Integer branchId, Integer bookId, Integer days) {
		super();
		this.cardNo = cardNo;
		this.branchId = branchId;
		this.bookId = bookId;
		this.days = days;
	}

	public Integer getCardNo() {
		return cardNo;
	}

	public void setCardNo(Integer cardNo) {
		this.cardNo = cardNo;
	}

	public Integer getBranchId() {
		return branchId;
	}

	public void setBranchId(Integer branchId) {
		this.branchId = branchId;
	}

	public Integer getBookId() {
		return bookId;
	}

	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}

	public Integer getDays() {
		return days;
	}

	public void setDays(Integer days) {
		this.days = days;
	}
}
