package com.cg.uas.dto;


public class Programs_Offered 

{

	private String programName	;
	private String description;
	private String applicant_Eligibility   ;
	private int duration ;
	private String degree_Certificate_Offered  ;
	
	
	@Override
	public String toString() {
		return "Programs_offered [programName=" + programName
				+ ", description=" + description + ", applicant_Eligibility="
				+ applicant_Eligibility + ", duration=" + duration
				+ ", degree_Certificate_Offered=" + degree_Certificate_Offered
				+ "]";
	}
	
	public String getProgramName() {
		return programName;
	}
	public void setProgramName(String programName) {
		this.programName = programName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getApplicant_Eligibility() {
		return applicant_Eligibility;
	}
	public void setApplicant_Eligibility(String applicant_Eligibility) {
		this.applicant_Eligibility = applicant_Eligibility;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public String getDegree_Certificate_Offered() {
		return degree_Certificate_Offered;
	}
	public void setDegree_Certificate_Offered(String degree_Certificate_Offered) {
		this.degree_Certificate_Offered = degree_Certificate_Offered;
	}
	
	
	
}
