package com.app.model;

import java.sql.Date;

public class Customer {
	private long id;
	private String first_name;
	private String last_name;
	private int ssn;
	private String login_user_name;
	private String login_password;
	private String salutation;
	private Date dob;
	private long phone1;
	private long phone2;
	private long register_by_employee;
	private int credit_score;
	private String address;
	private String email;
	private String dl;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public int getSsn() {
		return ssn;
	}
	public void setSsn(int ssn) {
		this.ssn = ssn;
	}
	public String getLogin_user_name() {
		return login_user_name;
	}
	public void setLogin_user_name(String login_user_name) {
		this.login_user_name = login_user_name;
	}
	public String getLogin_password() {
		return login_password;
	}
	public void setLogin_password(String login_password) {
		this.login_password = login_password;
	}
	public String getSalutation() {
		return salutation;
	}
	public void setSalutation(String salutation) {
		this.salutation = salutation;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public long getPhone1() {
		return phone1;
	}
	public void setPhone1(long phone1) {
		this.phone1 = phone1;
	}
	public long getPhone2() {
		return phone2;
	}
	public void setPhone2(long phone2) {
		this.phone2 = phone2;
	}
	public long getRegister_by_employee() {
		return register_by_employee;
	}
	public void setRegister_by_employee(long register_by_employee) {
		this.register_by_employee = register_by_employee;
	}
	public int getCredit_score() {
		return credit_score;
	}
	public void setCredit_score(int credit_score) {
		this.credit_score = credit_score;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getDl() {
		return dl;
	}
	public void setDl(String dl) {
		this.dl = dl;
	}
	public Customer() {
		
	}
	
	public Customer(long id, String first_name, String last_name, int ssn, String login_user_name,
			String login_password, String salutation, Date dob, long phone1, long phone2, long register_by_employee,
			int credit_score, String address, String email, String dl) {
		super();
		this.id = id;
		this.first_name = first_name;
		this.last_name = last_name;
		this.ssn = ssn;
		this.login_user_name = login_user_name;
		this.login_password = login_password;
		this.salutation = salutation;
		this.dob = dob;
		this.phone1 = phone1;
		this.phone2 = phone2;
		this.register_by_employee = register_by_employee;
		this.credit_score = credit_score;
		this.address = address;
		this.email = email;
		this.dl = dl;
	}
	@Override
	public String toString() {
		return "Customer [id=" + id + ", first_name=" + first_name + ", last_name=" + last_name + ", ssn=" + ssn
				+ ", login_user_name=" + login_user_name + ", login_password=" + login_password + ", salutation="
				+ salutation + ", dob=" + dob + ", phone1=" + phone1 + ", phone2=" + phone2 + ", register_by_employee="
				+ register_by_employee + ", credit_score=" + credit_score + ", address=" + address + ", email=" + email
				+ ", dl=" + dl + "]";
	}
	
	
	
	
}
