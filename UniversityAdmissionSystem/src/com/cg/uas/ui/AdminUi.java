/*package com.cg.uas.ui;

public class AdminUI {

	
	public static void updatePrograms(){}
	public static void manageSchedules(){}
	public static void generateReports(){}
}*/

package com.cg.uas.ui;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.cg.uas.dao.DaoUasImpl;
import com.cg.uas.dto.Application;
import com.cg.uas.dto.Programs_Offered;
import com.cg.uas.dto.Programs_Scheduled;
import com.cg.uas.exception.UasException;
import com.cg.uas.service.IServiceUas;
import com.cg.uas.service.ServiceUasImpl;

public class AdminUi {

	Logger classLogger;
	IServiceUas service = null;
	Scanner scan = new Scanner(System.in);

	public AdminUi() {
		classLogger = Logger.getLogger(DaoUasImpl.class);
		try {
			service = new ServiceUasImpl();
		} catch (UasException e) {
			System.out.println("service creation exception");
			classLogger.error(e);
		}
	}

	public void updatePrograms() {

		System.out
				.println("\n\nWhat would you like to do?\n1.Add program offered\n2.Delete program offered\n3.Return to previous Menu");
		String flag = scan.next();
		while (!(flag.equals("1") ||flag.equals("2")||flag.equals("3"))){
			System.out.println("Please enter a valid choice?");
			flag = scan.next();
		}
		try {

			if (flag.equals("1")) {

				Programs_Offered por = new Programs_Offered();

				System.out.println("\n\nEnter program name");
				scan.nextLine();
				por.setProgramName(scan.nextLine());
				System.out.println("Enter description");
				por.setDescription(scan.nextLine());
				System.out.println("Enter applicant eligibility");
				por.setApplicant_Eligibility(scan.nextLine());
				System.out.println("Enter duration in weeks");
				por.setDuration(scan.nextInt());
				System.out.println("Enter degree certificate offered");
				scan.nextLine();
				por.setDegree_Certificate_Offered(scan.nextLine());

				boolean res = service.addProgramOffered(por);
				if (res)
					System.out.println("Program offered Succesfully added");
				else
					System.out.println("Failed to add Program offered");
			} else if (flag.equals("2")) {

				System.out
						.println("Please note that any Scheduled programs with giving Program Name will also be DELETED");
				System.out.println("Enter program name to delete");
				scan.nextLine();

				String progName = scan.nextLine();
				boolean res = service.delProgramOffered(progName);
				if (res)
					System.out.println("Succesfully Program deleted");
				else
					System.out.println("Program Deletion Failed");
			} else if (flag.equals("3")) {
				return;
			}

		} catch (UasException msg) {
			System.err.println(msg);
		}

		return;
	}

	public void manageSchedules() {

		System.out
				.println("\n\nWhat would you like to do?\n1.Add program schedule\n2.Delete program schedule\n3.Return to previous Menu");
		String flag = scan.next();
		while (!(flag.equals("1") ||flag.equals("2")||flag.equals("3"))) {
			System.out.println("Please enter a valid choice?");
			flag = scan.next();
		}
		try {

			if (flag.equals("1")) {
				Programs_Scheduled por = new Programs_Scheduled();
				System.out.println("\n\nEnter program ID");
				por.setScheduled_Program_Id(scan.next());
				scan.nextLine();
				System.out.println("Enter program name");
				por.setProgramName(scan.nextLine());
				System.out.println("Enter city");
				por.setCity(scan.nextLine());
				System.out.println("Enter pincode");
				por.setPincode(scan.nextInt());
				System.out.println("Enter start date(dd/MM/YYYY)");
				scan.nextLine();
				String dateofbirth = scan.nextLine();
				DateTimeFormatter formatter = DateTimeFormatter
						.ofPattern("dd/MM/yyyy");
				LocalDate dob = LocalDate.parse(dateofbirth, formatter);
				por.setStart_Date(dob);
				System.out.println("Enter end date(dd/MM/YYYY)");
				dateofbirth = scan.nextLine();
				dob = LocalDate.parse(dateofbirth, formatter);
				por.setEnd_Date(dob);
				System.out.println("Enter sessions per week");
				por.setSessions_Per_Week(scan.nextInt());

				System.out.println(por);
				boolean res = service.addProgramScheduled(por);

				if (res)
					System.out.println("Program scheduled Succesfully added");
				else
					System.out.println("Failed to add Scheduled Program");
			}

			else if (flag.equals("2")) {
				scan.nextLine();
				System.out.println("\nEnter program ID to delete");
				String ans = scan.nextLine();
				boolean res = service.delProgramScheduled(ans);
				if (res)
					System.out.println("Program scheduled Succesfully deleted");
				else
					System.out.println("Failed to delete Scheduled Program");
			} else if (flag.equals("3")) {
				return;
			}

		} catch (UasException msg) {
			System.err.println(msg);
		}

		return;
	}

	public void generateReports() {

		System.out
				.println("\n\nEnter what report to generate\n1.View all applicants\n2.View list of programs scheduled to begin in a given period\n3.Return to previous Menu ");
		String flag = scan.next();
		while (!(flag.equals("1") ||flag.equals("2")||flag.equals("3"))) {
			System.out.println("Please enter a valid choice");
			flag = scan.next();
		}

		try {
			if (flag.equals("3")) {
				return;
			} else {
				generateAssetsFile(flag);
			}

		} catch (UasException msg) {
			System.err.println(msg);
		}

		return;
	}

	public void generateAssetsFile(String choice) throws UasException {

		FileWriter fout = null;
		String fileName = null;
		System.out.println("Please enter the file name");
		fileName = scan.next();
		fileName = fileName + ".csv";
		File file = new File(fileName);
		classLogger.debug("writing to file");

		if (choice.equals("1")) {
			List<Application> applicationslist = service.viewApplicants();
			try {
				fout = new FileWriter(file);
				BufferedWriter bw = new BufferedWriter(fout);

				bw.write("application_Id,full_Name,date_Of_Birth,highest_Qualification,marks_Obtained,goals,email_Id,scheduled_Program_Id,status,date_Of_Interview,address\n");

				for (Application application : applicationslist) {
					bw.write(application.toString2());
				}
				bw.close();
			} catch (Exception e) {
				classLogger.error(e);
				throw new UasException(e.getMessage());
			}
			System.out.println("Report Succsefully generated");
			
		} else if (choice.equals("2")) {
			System.out.println("\nEnter Start date of period(dd/mm/yyyy)");
			scan.nextLine();
			String dateofbirth = scan.nextLine();
			DateTimeFormatter formatter = DateTimeFormatter
					.ofPattern("dd/MM/yyyy");
			LocalDate dob = LocalDate.parse(dateofbirth, formatter);
			System.out.println("Enter end date of period(dd/mm/yyyy)");
			dateofbirth = scan.nextLine();
			LocalDate dend = LocalDate.parse(dateofbirth, formatter);
			List<Programs_Scheduled> proglist = service.viewPrograms(dob, dend);

			try {

				fout = new FileWriter(file);
				BufferedWriter bw = new BufferedWriter(fout);
				/*
				 * bw.write(
				 * "Asset Id, Asset Name, Asset Description, Asset Quantity, Asset Status\n"
				 * );
				 */
				for (Programs_Scheduled prog : proglist) {
					bw.write(prog.toString2());
				}
				bw.close();
			} catch (Exception e) {
				classLogger.error(e);
				throw new UasException(e.getMessage());
			}

		}

	}

}