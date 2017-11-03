package com.cg.uas.ui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import com.cg.uas.dto.Application;
import com.cg.uas.dto.Programs_Scheduled;
import com.cg.uas.exception.UasException;
import com.cg.uas.service.IServiceUas;
import com.cg.uas.service.ServiceUasImpl;

public class ApplicantUi {

	Scanner scan = new Scanner(System.in);
	IServiceUas service = null;

	public ApplicantUi() {
		try {
			service = new ServiceUasImpl();
		} catch (UasException e) {
			System.out.println("service creation exception");
			e.printStackTrace();
		}
	}

	public void viewAllPrograms() {
		try {
			List<Programs_Scheduled> list = service.allPrograms();
			System.out.println("\nThe scheduled programs are:  ");
			for (Programs_Scheduled s : list)
				System.out.println(s);
		} catch (UasException e) {
			e.printStackTrace();
		}
	}

	public void applyForProgram() {

		Application applicant = new Application();
	
		for (int i = 0; i < 90; i++)
			System.out.println("\n");
		System.out.println("Welcome to the applicant portal");
		System.out.println("Please provide necerssary information");
		System.out.println("Enter Full Name");
		applicant.setFull_Name(scan.nextLine());
		System.out.println("Enter date of birth(dd/MM/YYYY)");
		String dateofbirth = scan.nextLine();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate dob = LocalDate.parse(dateofbirth, formatter);
		applicant.setDate_Of_Birth(dob);
		System.out.println("Select Highest Qualification");
		System.out
				.println("1.Btech/Be\n2.BCA\n3.MBA\n4.Mtech/ME\n5.Other degree");
		String flag = scan.next();
		String degree = null;
		if (flag == "1")
			degree = "Btech";
		else if (flag == "2")
			degree = "BCA";
		else if (flag == "3")
			degree = "MBA";
		else if (flag == "4")
			degree = "Mtech";
		else if (flag == "5") {
			System.out.println("Enter the degree");
			scan.nextLine();
			degree = scan.nextLine();
		}
		applicant.setHighest_Qualification(degree);
		System.out.println("Enter marks obtained");
		applicant.setMarks_Obtained(scan.nextInt());
		System.out
				.println("Enter your goals in short paragraph of 3-4 sentences");
		scan.nextLine();
		applicant.setGoals(scan.nextLine());
		System.out.println("Enter email ID");
		applicant.setEmail_Id(scan.nextLine());
		System.out.println("Enter Scheduled program ID");
		applicant.setScheduled_Program_Id(scan.nextLine());
		applicant.setStatus("applied");
		System.out.println("Enter your address");
		applicant.setAddress(scan.nextLine());

		try {
			int apply = service.apply(applicant);
			if (apply != -1) {
				System.out
						.println("\nApplication successfully submitted.Please note the following application number for future reference ");
				System.out.println(apply);
				System.out.println("\nEnter a key to return to main menu");
				scan.nextLine();
			} else {
				System.out.println("Application submission failed");
			}
			
			Client.main(null);
		} catch (UasException e) {
			e.printStackTrace();
		}

	}

	public void viewApplicationStatus() {


		System.out.println("Enter the applicant ID to view status:  ");
		int application_Id = scan.nextInt();
		try {
			String status = service.status(application_Id);
			if (status == null) {
				System.out.println("Enterd Application Id is Invalid");
			} else {
				System.out.println("\nApplication Status: " + status);
			}

			scan.nextLine();
			Client.applicantfunction();
		} catch (UasException e) {
			e.printStackTrace();
		}

	}
}