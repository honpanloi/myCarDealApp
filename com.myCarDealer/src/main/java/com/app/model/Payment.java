package com.app.model;

import java.sql.Date;

public class Payment {
	private long id;
	private double total_amount;
	private String date_generated;
	private String status;
	private double interest;
	private double principal;
	private Date date_due;
	private Date date_completed;
	private boolean is_late;
	private long customer_id;
	private long car_id;
	private long Loan_id;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public double getTotal_amount() {
		return total_amount;
	}
	public void setTotal_amount(double total_amount) {
		this.total_amount = total_amount;
	}
	public String getDate_generated() {
		return date_generated;
	}
	public void setDate_generated(String date_generated) {
		this.date_generated = date_generated;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public double getInterest() {
		return interest;
	}
	public void setInterest(double interest) {
		this.interest = interest;
	}
	public double getPrincipal() {
		return principal;
	}
	public void setPrincipal(double principal) {
		this.principal = principal;
	}
	public Date getDate_due() {
		return date_due;
	}
	public void setDate_due(Date date_due) {
		this.date_due = date_due;
	}
	public Date getDate_completed() {
		return date_completed;
	}
	public void setDate_completed(Date date_completed) {
		this.date_completed = date_completed;
	}
	public boolean getIs_late() {
		return is_late;
	}
	public void setIs_late(boolean is_late) {
		this.is_late = is_late;
	}
	public long getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(long customer_id) {
		this.customer_id = customer_id;
	}
	public long getCar_id() {
		return car_id;
	}
	public void setCar_id(long car_id) {
		this.car_id = car_id;
	}
	public long getLoan_id() {
		return Loan_id;
	}
	public void setLoan_id(long loan_id) {
		Loan_id = loan_id;
	}
	public Payment(long id, double total_amount, String date_generated, String status, double interest,
			double principal, Date date_due, Date date_completed, boolean is_late, long customer_id, long car_id,
			long loan_id) {
		super();
		this.id = id;
		this.total_amount = total_amount;
		this.date_generated = date_generated;
		this.status = status;
		this.interest = interest;
		this.principal = principal;
		this.date_due = date_due;
		this.date_completed = date_completed;
		this.is_late = is_late;
		this.customer_id = customer_id;
		this.car_id = car_id;
		Loan_id = loan_id;
	}
	
	public Payment() {}
	@Override
	public String toString() {
		return "Payment [id=" + id + ", total_amount=" + total_amount + ", date_generated=" + date_generated
				+ ", status=" + status + ", interest=" + interest + ", principal=" + principal + ", date_due="
				+ date_due + ", date_completed=" + date_completed + ", is_late=" + is_late + ", customer_id="
				+ customer_id + ", car_id=" + car_id + ", Loan_id=" + Loan_id + "]";
	}
	
	
}
