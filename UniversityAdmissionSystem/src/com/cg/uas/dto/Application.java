package com.cg.uas.dto;

import java.time.LocalDate;
import java.util.Date;

public class Application

{

	private int application_Id;
	private String full_Name;
	private LocalDate date_Of_Birth;
	private String highest_Qualification;
	private int marks_Obtained;
	private String goals;
	private String email_Id;
	private String scheduled_Program_Id;
	private String status;
	private LocalDate date_Of_Interview;
	private String address;

	@Override
	public String toString() {
		return "Application [application_Id=" + application_Id + ", full_Name="
				+ full_Name + ", date_Of_Birth=" + date_Of_Birth
				+ ", highest_Qualification=" + highest_Qualification
				+ ", marks_Obtained=" + marks_Obtained + ", goals=" + goals
				+ ", email_Id=" + email_Id + ", scheduled_Program_Id="
				+ scheduled_Program_Id + ", status=" + status
				+ ", date_Of_Interview=" + date_Of_Interview + ", address="
				+ address + "]";
	}

	public String toString2() {
		return application_Id + "," + full_Name + "," + date_Of_Birth + ","
				+ highest_Qualification + "," + marks_Obtained + "," + goals
				+ "," + email_Id + "," + scheduled_Program_Id + "," + status
				+ "," + date_Of_Interview + "," + address;
	}

	public int getApplication_Id() {
		return application_Id;
	}

	public void setApplication_Id(int application_Id) {
		this.application_Id = application_Id;
	}

	public String getFull_Name() {
		return full_Name;
	}

	public void setFull_Name(String full_Name) {
		this.full_Name = full_Name;
	}

	public LocalDate getDate_Of_Birth() {
		return date_Of_Birth;
	}

	public void setDate_Of_Birth(LocalDate dob) {
		this.date_Of_Birth = dob;
	}

	public String getHighest_Qualification() {
		return highest_Qualification;
	}

	public void setHighest_Qualification(String highest_Qualification) {
		this.highest_Qualification = highest_Qualification;
	}

	public int getMarks_Obtained() {
		return marks_Obtained;
	}

	public void setMarks_Obtained(int marks_Obtained) {
		this.marks_Obtained = marks_Obtained;
	}

	public String getGoals() {
		return goals;
	}

	public void setGoals(String goals) {
		this.goals = goals;
	}

	public String getEmail_Id() {
		return email_Id;
	}

	public void setEmail_Id(String email_Id) {
		this.email_Id = email_Id;
	}

	public String getScheduled_Program_Id() {
		return scheduled_Program_Id;
	}

	public void setScheduled_Program_Id(String string) {
		this.scheduled_Program_Id = string;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LocalDate getDate_Of_Interview() {
		return date_Of_Interview;
	}

	public void setDate_Of_Interview(LocalDate date_Of_Interview) {
		this.date_Of_Interview = date_Of_Interview;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
