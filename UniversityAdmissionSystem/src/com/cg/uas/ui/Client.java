package com.cg.uas.ui;

import java.util.Scanner;

import org.apache.log4j.PropertyConfigurator;

import com.cg.uas.exception.UasException;
import com.cg.uas.service.IServiceUas;
import com.cg.uas.service.ServiceUasImpl;

public class Client {

	// Main function showing the first page of UI
	public static void main(String[] args) {

		PropertyConfigurator.configure("resources/log4j.properties");
		Scanner scan = new Scanner(System.in);
		String opt=null;
		while (true) {

			System.out
					.println("                                                University Admission System");
			System.out
					.println("                                               ---------------------------");
			/* System.out.println("Choose from the following options"); */
			System.out.println("1.Applicant");
			System.out.println("2.Members of Admission Committee(MAC)");
			System.out.println("3.Administrators");
			System.out.println("4.Exit");

			opt = scan.next();
			switch (opt) {
			case "1":
				applicantfunction();
				break;
			case "2":
				macfunction();
				break;
			case "3":
				adminfunction();
				break;
			case "4":
				System.out.println("Thank you!! Exiting...");
				System.exit(0);
			default:
				System.out.println("\nPlease choose a valid option");
				break;

			} // switch closing
		} // while closing
	} // main closing

	// The console page showing applicant entry
	public static void applicantfunction() {
		Scanner scan = new Scanner(System.in);
		String opt = null;

		ApplicantUi appUi = new ApplicantUi();
		do {
			System.out.println("\n");
			System.out
					.println("                                                University Admission System");
			System.out
					.println("                                               ---------------------------");
			System.out
					.println("                                                  Applicant Interface");
			System.out
					.println("                                                  -------------------");
			/* System.out.println("Choose from the following options"); */
			System.out.println("1.View all Scheduled Programs");
			System.out.println("2.Apply for a Scheduled Program");
			System.out.println("3.View Application Status");
			System.out.println("4.Return to Main Menu");
			System.out.println("5.Exit");
			opt = scan.next();
			switch (opt) {
			case "1":
				appUi.viewAllPrograms();
				break;
			case "2":
				appUi.applyForProgram();
				break;
			case "3":
				appUi.viewApplicationStatus();
				break;
			case "4":
				System.out.println("\n\n");
				System.out.println("Thank you!! Returning to Main Menu...");
				main(null);
				break;
			case "5":
				System.out.println("Thank you!! Exiting...");
				System.exit(0);
				break;
			default:
				System.out.println("Please choose a valid option");
				break;
			}
		} while (opt != "4");
	}

	// The console page showing MAC entry
	public static void macfunction() {
		Scanner scan = new Scanner(System.in);
		String opt = null;
		System.out
				.println("\n                                               University Admission System");
		System.out
				.println("                                               ---------------------------");
		System.out.println("\n					Member of Admission Committee (MAC) Login");
		boolean valid;
		IServiceUas loginService = null;
		try {
			loginService = new ServiceUasImpl();
		} catch (UasException msg) {
			System.err.println("Login Service failed");
		}

		do {
			System.out.println("Please Enter the userID: ");
			String userName = scan.nextLine();
			System.out.println("Please Enter the password: ");
			String password = scan.nextLine();
			valid = false;
			try {
				valid = loginService.macLogin(userName, password);
			} catch (UasException msg) {
				System.err.println(msg);
			}
			if (!valid) {
				System.out.println("The userID password combination is wrong.");
				System.out.println("1. Try again");
				System.out.println("2. Exit to Main Menu");
				String choice = scan.next();
				if (choice.equals("1")) {
					scan.nextLine();
					valid = false;
				} else {
					valid = false;
					break;
				}
			}
		} while (!valid);

		if (valid) {
			do {

				System.out.println("\n");
				System.out
						.println("                                               University Admission System");
				System.out
						.println("                                               ---------------------------");
				System.out.println("							MAC Interface");
				System.out.println("							-------------");
				System.out
						.println("1.View applications for a specific program");
				System.out.println("2.Accept an application");
				System.out.println("3.Reject an application");
				System.out
						.println("4.Confirm the status of an applicant post interview");
				System.out.println("5.Return to main menu");
				System.out.println("6.Exit");
				opt = scan.next();
				
				MacUi macUi=new MacUi();
				switch (opt) {
				case "1":
					macUi.viewApplications();
					break;
				case "2":
					macUi.acceptApplication();
					break;
				case "3":
					macUi.rejectApplication();
					break;
				case "4":
					macUi.updateApplicationStatus();
					break;
				case "5":
					System.out.println("\n\n");
					System.out.println("Thank you!! Returning to Main Menu...");
					main(null);
					break;
				case "6":
					System.out.println("Thank you!! Exiting...");
					System.exit(0);
					break;
				default:
					System.out.println("Please choose a valid option");
				}
			} while (!opt.equals("5"));
		} else {
			main(null);
		}
	}


	public static void adminfunction() {

		Scanner scan = new Scanner(System.in);
		String opt = null;
		System.out
				.println("                                                University Admission System");
		System.out
				.println("                                               ---------------------------");
		System.out.println("\n						Welcome to Admin Login");

		boolean valid;
		IServiceUas loginService = null;
		try {
			loginService = new ServiceUasImpl();
		} catch (UasException msg) {
			System.err.println("Login Service failed");
		}

		do {
			System.out.println("Please Enter the userID: ");
			String userName = scan.nextLine();
			System.out.println("Please Enter the password: ");
			String password = scan.nextLine();
			valid = false;
			try {
				valid = loginService.adminLogin(userName, password);
			} catch (UasException msg) {
				System.err.println(msg);
			}
			if (!valid) {
				System.out.println("The userID password combination is wrong.");
				System.out.println("1. Try again");
				System.out.println("2. Exit to Main Menu");
				String choice = scan.next();
				if (choice.equals("1")) {
					scan.nextLine();
					valid = false;
				} else {
					valid = false;
					break;
				}
			}
		} while (!valid);

		if (valid) {
			do {
				System.out.println("\n");
				System.out
						.println("                                                University Admission System");
				System.out
						.println("                                               ---------------------------");
				System.out.println("						   Admin Interface");
				System.out.println("						   ---------------");
				System.out.println("Choose from the following options");
				System.out.println("1.Update and manage programs");
				System.out.println("2.Manage schedules of the programs");
				System.out.println("3.Generate reports");
				System.out.println("4.Return to main menu");
				System.out.println("5.Exit");
				opt = scan.next();
				
				AdminUi adminUi = new AdminUi();
				
				switch (opt) {
				case "1":
					adminUi.updatePrograms();
					break;
				case "2":
					adminUi.manageSchedules();
					break;
				case "3":
					adminUi.generateReports();
					break;
				case "4":
					System.out.println("\n\n\n");
					System.out.println("Thank you!! returning to main menu...");
					main(null);
				case "5":
					System.out.println("Thank you!! Exiting...");
					System.exit(0);
					break;
				default:
					System.out.println("Please choose a valid option");
				}
			} while (!opt.equals("5"));
		} else {
			main(null);
		}
	}

	
	
/*	@Override
	public void generateAssetsFile() throws AssetException {
		classLogger.debug("writing to assets file");
		FileWriter fout = null;
		File file = new File(ASSETS_FILE);
		List<Asset> assets = viewAllAssets();
		try {
			fout = new FileWriter(file);
			BufferedWriter bw = new BufferedWriter(fout);
			bw.write("Asset Id, Asset Name, Asset Description, Asset Quantity, Asset Status\n");
			for (Asset asset : assets) {
				bw.write(asset.toString2());
			}
			bw.close();
		} catch (Exception e) {
			classLogger.error("DAO" + e);
			throw new AssetException(e.getMessage());
		}
	}
	*/
	
	
}
