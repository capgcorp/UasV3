package com.cg.uas.service;

import java.time.LocalDate;
import java.util.List;

import com.cg.uas.dto.Application;
import com.cg.uas.dto.Programs_Offered;
import com.cg.uas.dto.Programs_Scheduled;
import com.cg.uas.exception.UasException;

public interface IServiceUas {
	public List<Programs_Scheduled> allPrograms() throws UasException;
	public int apply(Application application) throws UasException;
	public String status(int application_Id) throws UasException;
	
	public boolean macLogin(String userName, String Password) throws UasException;
	public boolean adminLogin(String userName, String Password) throws UasException;
	
	public List<Application> allApplications(String programName) throws UasException;
	public boolean acceptApplication(int application_Id,LocalDate iDate) throws UasException;
	public boolean rejectApplication(int application_Id) throws UasException;
	public boolean confirmApplication(int application_Id) throws UasException;
	
	public boolean updProgramOffered(Programs_Offered program_offered, String ProgramName)  throws UasException;
	public boolean addProgramOffered(Programs_Offered program_offered)	throws UasException;
	public boolean delProgramOffered(String ProgramName)  throws UasException;
	
	public boolean updProgramScheduled(Programs_Scheduled program_Scheduled, String scheduled_Program_Id)  throws UasException;
	public boolean addProgramScheduled(Programs_Scheduled program_Scheduled)  throws UasException;
	public boolean delProgramScheduled(String scheduled_Program_Id)  throws UasException;
	
	public List<Application> viewApplicants() throws UasException;
	public List<Programs_Scheduled> viewPrograms(LocalDate dob, LocalDate dend) throws UasException; 
	
}
