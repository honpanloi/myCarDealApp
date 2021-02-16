package com.app.model;

import java.sql.Date;

public class Loan {
	private long id;
	private double amount_started;
	private String loan_provider;
	private Date date_started;
	private Date next_payment_date;
	private double total_principal_remain;
	private double monthly_payment;
	private double ann_interest_rate;
	private boolean has_late_payment;
	private Date projected_closing_date;
	private double total_amount_paid;
	private double total_interest_paid;
	private int total_payment_num;
	private int total_payment_completed;
	private long car_id;
	private long customer_id;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public double getAmount_started() {
		return amount_started;
	}
	public void setAmount_started(double amount_started) {
		this.amount_started = amount_started;
	}
	public String getLoan_provider() {
		return loan_provider;
	}
	public void setLoan_provider(String loan_provider) {
		this.loan_provider = loan_provider;
	}
	public Date getDate_started() {
		return date_started;
	}
	public void setDate_started(Date date_started) {
		this.date_started = date_started;
	}
	public Date getNext_payment_date() {
		return next_payment_date;
	}
	public void setNext_payment_date(Date next_payment_date) {
		this.next_payment_date = next_payment_date;
	}
	public double getTotal_principal_remain() {
		return total_principal_remain;
	}
	public void setTotal_principal_remain(double total_principal_remain) {
		this.total_principal_remain = total_principal_remain;
	}
	public double getMonthly_payment() {
		return monthly_payment;
	}
	public void setMonthly_payment(double monthly_payment) {
		this.monthly_payment = monthly_payment;
	}
	public double getAnn_interest_rate() {
		return ann_interest_rate;
	}
	public void setAnn_interest_rate(double ann_interest_rate) {
		this.ann_interest_rate = ann_interest_rate;
	}
	public boolean isHas_late_payment() {
		return has_late_payment;
	}
	public void setHas_late_payment(boolean has_late_payment) {
		this.has_late_payment = has_late_payment;
	}
	public Date getProjected_closing_date() {
		return projected_closing_date;
	}
	public void setProjected_closing_date(Date projected_closing_date) {
		this.projected_closing_date = projected_closing_date;
	}
	public double getTotal_amount_paid() {
		return total_amount_paid;
	}
	public void setTotal_amount_paid(double total_amount_paid) {
		this.total_amount_paid = total_amount_paid;
	}
	public double getTotal_interest_paid() {
		return total_interest_paid;
	}
	public void setTotal_interest_paid(double total_interest_paid) {
		this.total_interest_paid = total_interest_paid;
	}
	public int getTotal_payment_num() {
		return total_payment_num;
	}
	public void setTotal_payment_num(int total_payment_num) {
		this.total_payment_num = total_payment_num;
	}
	public int getTotal_payment_completed() {
		return total_payment_completed;
	}
	public void setTotal_payment_completed(int total_payment_completed) {
		this.total_payment_completed = total_payment_completed;
	}
	
	public long getCar_id() {
		return car_id;
	}
	public void setCar_id(long car_id) {
		this.car_id = car_id;
	}
	public long getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(long customer_id) {
		this.customer_id = customer_id;
	}
	
	public Loan(long id, double amount_started, String loan_provider, Date date_started, Date next_payment_date,
			double total_principal_remain, double monthly_payment, double ann_interest_rate, boolean has_late_payment,
			Date projected_closing_date, double total_amount_paid, double total_interest_paid, int total_payment_num,
			int total_payment_completed, long car_id, long customer_id) {
		super();
		this.id = id;
		this.amount_started = amount_started;
		this.loan_provider = loan_provider;
		this.date_started = date_started;
		this.next_payment_date = next_payment_date;
		this.total_principal_remain = total_principal_remain;
		this.monthly_payment = monthly_payment;
		this.ann_interest_rate = ann_interest_rate;
		this.has_late_payment = has_late_payment;
		this.projected_closing_date = projected_closing_date;
		this.total_amount_paid = total_amount_paid;
		this.total_interest_paid = total_interest_paid;
		this.total_payment_num = total_payment_num;
		this.total_payment_completed = total_payment_completed;
		this.car_id = car_id;
		this.customer_id = customer_id;
	}
	public Loan() {}
	@Override
	public String toString() {
		return "Loan [id=" + id + ", amount_started=" + amount_started + ", loan_provider=" + loan_provider
				+ ", date_started=" + date_started + ", next_payment_date=" + next_payment_date
				+ ", total_principal_remain=" + total_principal_remain + ", monthly_payment=" + monthly_payment
				+ ", ann_interest_rate=" + ann_interest_rate + ", has_late_payment=" + has_late_payment
				+ ", projected_closing_date=" + projected_closing_date + ", total_amount_paid=" + total_amount_paid
				+ ", total_interest_paid=" + total_interest_paid + ", total_payment_num=" + total_payment_num
				+ ", total_payment_completed=" + total_payment_completed + ", car_id=" + car_id + ", customer_id="
				+ customer_id + "]";
	}
	
	
}
