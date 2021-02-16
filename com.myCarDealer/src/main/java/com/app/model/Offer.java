package com.app.model;

public class Offer {
	private long id;
	private long offer_by_customer;
	private String offer_created_time;
	private String offer_completed_time;
	private double offer_amount;
	private long car_id;
	private String status;
	private long accepted_by;
	private long rejected_by;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getOffer_by_customer() {
		return offer_by_customer;
	}
	public void setOffer_by_customer(long offer_by_customer) {
		this.offer_by_customer = offer_by_customer;
	}
	public String getOffer_created_time() {
		return offer_created_time;
	}
	public void setOffer_created_time(String offer_created_time) {
		this.offer_created_time = offer_created_time;
	}
	public String getOffer_completed_time() {
		return offer_completed_time;
	}
	public void setOffer_completed_time(String offer_completed_time) {
		this.offer_completed_time = offer_completed_time;
	}
	public double getOffer_amount() {
		return offer_amount;
	}
	public void setOffer_amount(double offer_amount) {
		this.offer_amount = offer_amount;
	}
	public long getCar_id() {
		return car_id;
	}
	public void setCar_id(long car_id) {
		this.car_id = car_id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public long getAccept_by() {
		return accepted_by;
	}
	public void setAccept_by(long accept_by) {
		this.accepted_by = accept_by;
	}
	public long getRejected_by() {
		return rejected_by;
	}
	public void setRejected_by(long rejected_by) {
		this.rejected_by = rejected_by;
	}
	public Offer(long id, long offer_by_customer, String offer_created_time, String offer_completed_time,
			double offer_amount, long car_id, String status, long accepted_by, long rejected_by) {
		super();
		this.id = id;
		this.offer_by_customer = offer_by_customer;
		this.offer_created_time = offer_created_time;
		this.offer_completed_time = offer_completed_time;
		this.offer_amount = offer_amount;
		this.car_id = car_id;
		this.status = status;
		this.accepted_by = accepted_by;
		this.rejected_by = rejected_by;
	}
	
	public Offer() {}
	@Override
	public String toString() {
		return "Offer [id=" + id + ", offer_by_customer=" + offer_by_customer + ", offer_created_time="
				+ offer_created_time + ", offer_completed_time=" + offer_completed_time + ", offer_amount="
				+ offer_amount + ", car_id=" + car_id + ", status=" + status + ", accepted_by=" + accepted_by
				+ ", rejected_by=" + rejected_by + "]";
	}
	
	
	
	
}
