/*package com.cg.uas.ui;

public class MacUi {

	
	public static void viewApplications(){}
	public static void processApplication(){}
	public static void updateApplicationStatus(){}
}
 */

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

public class MacUi {

	Scanner scan = new Scanner(System.in);
	IServiceUas service = null;

	public MacUi() {
		try {
			service = new ServiceUasImpl();
		} catch (UasException e) {
			System.out.println("service creation exception");
		}
	}

	public void viewApplications() {
		String programName = null;
		System.out.println("Enter the program name to check applications ");
		programName = scan.nextLine();

		List<Application> applcaitonList = null;
		try {
			applcaitonList = service.allApplications(programName);
		} catch (UasException e) {
			System.out.println("service function exception");
		}

		System.out.println("\nApplications for choosen Program: ");
		if(!applcaitonList.isEmpty()){
		for (Application application : applcaitonList) {
			System.out.println(application);
		}
		}else{
			System.out.println("No applications found for given Program Name");
		}

		return;
	}

	public void acceptApplication() {
		System.out.println("Enter the applicant ID to accept: ");
		int appId = scan.nextInt();
		scan.nextLine();

		boolean ans = false;
		try {

			System.out.println("Enter interview date(dd/mm/yyyy)");
			String date = scan.nextLine();
			DateTimeFormatter formatter = DateTimeFormatter
					.ofPattern("dd/MM/yyyy");
			LocalDate iDate = LocalDate.parse(date, formatter);

			ans = service.acceptApplication(appId, iDate);
		} catch (UasException e) {
			System.out.println("service function exception");
		}
		if (ans == true) {
			System.out.println("Application status succesfully updated");
		} else {
			System.out.println("Failed to Update Applcation Status");
		}
		return;
	}

	public void rejectApplication() {
		System.out.println("Enter the applicant ID to accept: ");
		int appId = scan.nextInt();

		boolean ans = false;
		try {
			ans = service.rejectApplication(appId);
		} catch (UasException e) {
			System.out.println("service function exception");
		}
		if (ans == true) {
			System.out.println("Application status succesfully updated");
		} else {
			System.out.println("Failed to Update Applcation Status");
		}
		return;

	}

	public void updateApplicationStatus() {
		int appId = 0;
		boolean ans = false;

		System.out.println("Enter the application ID: ");
		appId = scan.nextInt();

		try {
			ans = service.confirmApplication(appId);
		} catch (UasException e) {
			System.out.println("service function exception");
		}

		if (ans == true) {
			System.out.println("Application status succesfully updated");
		} else {
			System.out.println("Failed to Update Applcation Status");
		}
		return;

	}
}