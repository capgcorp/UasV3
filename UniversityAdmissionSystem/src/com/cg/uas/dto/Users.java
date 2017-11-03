package com.cg.uas.dto;

public class Users 

{

	private String login_Id	 ;
	private String password  ;
	private int role  ;
	
	public Users() {
	
	}

	public Users(String login_id, String password, int role) {
		super();
		this.login_Id = login_id;
		this.password = password;
		this.role = role;
	}

	public String getLogin_id() {
		return login_Id;
	}

	public void setLogin_id(String login_id) {
		this.login_Id = login_id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "Users [login_id=" + login_Id + ", password=" + password
				+ ", role=" + role + "]";
	}
	
	
	
}
