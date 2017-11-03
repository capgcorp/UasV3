package com.cg.uas.dto;



public class Participant

{

	private String roll_No ;
	private String email_Id  ;
	private int application_Id  ;
	private String scheduled_Program_Id ;
	
	
	
	
	@Override
	public String toString() {
		return "Participant [roll_No=" + roll_No + ", email_Id=" + email_Id
				+ ", application_Id=" + application_Id
				+ ", scheduled_Program_Id=" + scheduled_Program_Id + "]";
	}
	
	public String getRoll_No() {
		return roll_No;
	}
	public void setRoll_No(String roll_No) {
		this.roll_No = roll_No;
	}
	public String getEmail_Id() {
		return email_Id;
	}
	public void setEmail_Id(String email_Id) {
		this.email_Id = email_Id;
	}
	public int getApplication_Id() {
		return application_Id;
	}
	public void setApplication_Id(int application_Id) {
		this.application_Id = application_Id;
	}
	public String getScheduled_Program_Id() {
		return scheduled_Program_Id;
	}
	public void setScheduled_Program_Id(String scheduled_Program_Id) {
		this.scheduled_Program_Id = scheduled_Program_Id;
	}
	
	
	
}
