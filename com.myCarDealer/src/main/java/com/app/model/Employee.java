package com.app.model;

public class Employee {
	private long id;
	private String first_name;
	private String last_name;
	private String login_user_name;
	private String login_password;
	
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
	
	public Employee() {}
	
	public Employee(long id, String first_name, String last_name, String login_user_name, String login_password) {
		super();
		this.id = id;
		this.first_name = first_name;
		this.last_name = last_name;
		this.login_user_name = login_user_name;
		this.login_password = login_password;
	}
	
	@Override
	public String toString() {
		return "Employee [id=" + id + ", first_name=" + first_name + ", last_name=" + last_name + ", login_user_name="
				+ login_user_name + ", login_password=" + login_password + "]";
	}
	
	
}
