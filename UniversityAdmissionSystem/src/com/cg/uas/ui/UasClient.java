package com.cg.uas.ui;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.PropertyConfigurator;

import com.cg.uas.dto.Application;
import com.cg.uas.dto.Programs_Scheduled;
import com.cg.uas.exception.UasException;
import com.cg.uas.service.IServiceUas;
import com.cg.uas.service.ServiceUasImpl;

public class UasClient {

	static IServiceUas service;
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {
				service = new ServiceUasImpl();
			System.out.println("Service started! ");
		} catch (UasException exp) {
			System.err.println(exp.getMessage());
			System.exit(0);
		}
		PropertyConfigurator.configure("resources/log4j.properties");

		/*List<Programs_Scheduled> programsList;*/
		boolean verify;
		String status;
		List<Programs_Scheduled> programsList;
		List<Application> applicationsList;
		List<Application> applicantsList;
		/*
		//------1. all programs-------------------//
		try {
			programsList =service.allPrograms();
			for(Programs_Scheduled program: programsList)
			{	System.out.println("for enter");
				System.out.println(program.toString());
			}
		} catch (UasException exp) {
			System.err.println(exp.getMessage());
		}*/
		
		/*//------------show status-----------------//
		try {
			status =service.status(10);
		} catch (UasException exp) {
			System.err.println(exp.getMessage());
		}
		
		//------------accept application-------------//
		try {
			verify = service.acceptApplication(1, "27-oct-17");
			System.out.println(verify);
		} catch (UasException exp) {
			System.err.println(exp.getMessage());
		}
		
		
		//------------reject application--------------//
		try {
			verify = service.rejectApplication(1);
			System.out.println(verify);
		} catch (UasException exp) {
			System.err.println(exp.getMessage());
		}*/
		
		
		/*//--------------mac login-------------------//
			try {
				verify=service.macLogin("mac", "admin");
				System.out.println(verify);
			} catch (UasException exp) {
				System.err.println(exp.getMessage());
			}
		*/
		
		/*//--------------admin login------------------//
		try {
			verify=service.adminLogin("admin", "admin");
			System.out.println(verify);
		} catch (UasException exp) {
			System.err.println(exp.getMessage());
		}*/
		
		/*//----------------confirm application------------//
		try {
			verify = service.confirmApplication(10);
			System.out.println(verify);
		} catch (UasException exp) {
			System.err.println(exp.getMessage());
		}
		*/
		
		/*
		//-------------program list in time period----------------//
		try {
			programsList = service.viewPrograms("19-oct-17", "31-oct-17");

			for (Programs_Scheduled program : programsList) {
				System.out.println(program.toString());
			}
		} catch (UasException exp) {
			System.err.println(exp.getMessage());
		}*/
		
		/*//------------all applications--------------------//
		try {
			applicationsList = service.allApplications("orcl");
			for (Application application : applicationsList) {
				System.out.println(application.toString());
			}
		} catch (UasException exp) {
			System.err.println(exp.getMessage());
		}
		
		//-----------all applications----------------//
		try {
			applicationsList = service.viewApplicants();
			for (Application applicant : applicationsList) {
				System.out.println(applicant.toString());
			}
		} catch (UasException exp) {
			System.err.println(exp.getMessage());
		}	*/
	}

}
