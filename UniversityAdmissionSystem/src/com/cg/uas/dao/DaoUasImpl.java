package com.cg.uas.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.cg.uas.dto.Application;
import com.cg.uas.dto.Programs_Offered;
import com.cg.uas.dto.Programs_Scheduled;
import com.cg.uas.exception.UasException;
import com.cg.uas.util.ConnectionProvider;

public class DaoUasImpl implements IDaoUas {

	private Logger classLogger;
	private ConnectionProvider conPro;

	public DaoUasImpl() throws UasException {
		classLogger = Logger.getLogger(DaoUasImpl.class);
		try {
			conPro = ConnectionProvider
					.getInstance("resources/dbconfig.properties");

		} catch (ClassNotFoundException | IOException exp) {
			classLogger.error(exp);
			throw new UasException("Data Acces Initialization failed");
		}
	}

	public Application mapApplication(ResultSet row) throws UasException {
		Application application = new Application();
		try {

			application.setApplication_Id(row.getInt("application_Id"));
			application.setFull_Name(row.getString("full_name"));
			LocalDate date = row.getDate("date_Of_Birth").toLocalDate();
			application.setDate_Of_Birth(date);
			application.setHighest_Qualification(row
					.getString("highest_Qualification"));
			application.setMarks_Obtained(row.getInt("marks_Obtained"));
			application.setGoals(row.getString("goals"));
			application.setEmail_Id(row.getString("email_Id"));
			application.setScheduled_Program_Id(row
					.getString("scheduled_Program_Id"));
			application.setStatus(row.getString("status"));
			if (row.getDate("date_Of_Interview") != null) {
				application.setDate_Of_Interview(row.getDate(
						"date_Of_Interview").toLocalDate());
			}
			application.setAddress(row.getString("address"));

		} catch (SQLException exp) {
			classLogger.error(exp);
			throw new UasException("Failed to retrieve Applicants Data");
		}

		return application;
	}

	public Programs_Scheduled mapProgram(ResultSet row) throws UasException {
		Programs_Scheduled program = new Programs_Scheduled();
		try {

			program.setScheduled_Program_Id(row
					.getString("Scheduled_Program_Id"));
			program.setProgramName(row.getString("programName"));
			program.setCity(row.getString("city"));
			program.setPincode(row.getInt("pincode"));
			program.setStart_Date(row.getDate("start_Date").toLocalDate());
			program.setEnd_Date(row.getDate("end_Date").toLocalDate());
			program.setSessions_Per_Week(row.getInt("sessions_Per_Week"));

		} catch (SQLException exp) {
			classLogger.error(exp);
			throw new UasException("Failed to retrieve Program Scheduled Data");
		}

		return program;
	}

	@Override
	public List<Programs_Scheduled> allPrograms() throws UasException {

		List<Programs_Scheduled> programsList = new ArrayList<>();
		try (Connection con = conPro.getConnection();
				PreparedStatement pstSelectAll = con
						.prepareStatement(IQueryMapper.VIEW_ALL_PROGS)) {
			ResultSet results = pstSelectAll.executeQuery();

			while (results.next()) {
				programsList.add(mapProgram(results));
			}
		} catch (SQLException exp) {
			classLogger.error(exp);
			throw new UasException("Failed to get Programs Schedule!");
		}

		return programsList;

	}

	@Override
	public int apply(Application application) throws UasException {
		int appId = -1;
		try (Connection con = conPro.getConnection();
				PreparedStatement pst = con
						.prepareStatement(IQueryMapper.APPLY);
				PreparedStatement pstAppId = con
						.prepareStatement(IQueryMapper.APPLICATION_ID);) {

			pst.setString(1, application.getFull_Name());
			pst.setDate(2,
					java.sql.Date.valueOf(application.getDate_Of_Birth()));
			pst.setString(3, application.getHighest_Qualification());
			pst.setInt(4, application.getMarks_Obtained());
			pst.setString(5, application.getGoals());
			pst.setString(6, application.getEmail_Id());
			pst.setString(7, application.getScheduled_Program_Id());
			pst.setString(8, application.getStatus());
			pst.setString(9, application.getAddress());

			/* System.out.println(application.toString()); */

			int result = pst.executeUpdate();
			if (result > 0) {
				ResultSet result1 = pstAppId.executeQuery();
				if (result1.next()) {
					appId = result1.getInt("currval");
				}
			} else {
				appId = -1;
			}
		} catch (Exception msg) {
			classLogger.error(msg);
		}

		return appId;
	}

	@Override
	public String status(int application_Id) throws UasException {
		String status = null;
		try (Connection con = conPro.getConnection();
				PreparedStatement pstStatus = con
						.prepareStatement(IQueryMapper.VIEW_STATUS)) {

			pstStatus.setInt(1, application_Id);
			ResultSet result = pstStatus.executeQuery();
			if (result.next()) {
				status = result.getString("status");
			}
		} catch (SQLException exp) {
			classLogger.error(exp);
			throw new UasException("Failed to get Application status");
		}
		return status;
	}

	@Override
	public boolean macLogin(String userName, String Password)
			throws UasException {
		boolean valid = false;

		try (Connection con = conPro.getConnection();
				PreparedStatement pst = con
						.prepareStatement(IQueryMapper.LOGIN_VERIFY_MAC)) {

			pst.setString(1, userName);
			pst.setString(2, Password);
			ResultSet result = pst.executeQuery();

			if (result.next()) {
				valid = true;
			}

		} catch (SQLException exp) {
			classLogger.error(exp);
			throw new UasException("Failed to authenticate ");
		}
		return valid;
	}

	@Override
	public boolean adminLogin(String userName, String Password)
			throws UasException {
		boolean valid = false;

		try (Connection con = conPro.getConnection();
				PreparedStatement pst = con
						.prepareStatement(IQueryMapper.LOGIN_VERIFY_ADMIN)) {

			pst.setString(1, userName);
			pst.setString(2, Password);
			ResultSet result = pst.executeQuery();
			if (result.next()) {
				valid = true;
			}
		} catch (SQLException exp) {
			classLogger.error(exp);
			throw new UasException("Failed to authenticate ");
		}
		return valid;
	}

	@Override
	public List<Application> allApplications(String programName)
			throws UasException {
		List<Application> applicationsList = new ArrayList<>();
		try (Connection con = conPro.getConnection();
				PreparedStatement pst = con
						.prepareStatement(IQueryMapper.ALL_APPLICATIONS_ONE_PROG)) {

			pst.setString(1, programName);
			ResultSet results = pst.executeQuery();

			while (results.next()) {
				applicationsList.add(mapApplication(results));
			}

		} catch (SQLException exp) {
			classLogger.error(exp);
			throw new UasException("Failed to get applications!");
		}

		return applicationsList;

	}

	@Override
	public boolean acceptApplication(int application_Id, LocalDate iDate)
			throws UasException {
		boolean valid = false;

		try (Connection con = conPro.getConnection();
				PreparedStatement pst = con
						.prepareStatement(IQueryMapper.ACCEPT_APPLICANT)) {

			pst.setDate(1, java.sql.Date.valueOf(iDate));
			pst.setInt(2, application_Id);

			int result = pst.executeUpdate();

			if (result > 0) {
				valid = true;
			} else {
				valid = false;
				System.out.println("No applicant found with entered Id");
			}
		} catch (SQLException exp) {
			classLogger.error(exp);
			throw new UasException("Failed to update accept status ");
		}
		return valid;
	}

	@Override
	public boolean rejectApplication(int application_Id) throws UasException {
		boolean valid = false;

		try (Connection con = conPro.getConnection();
				PreparedStatement pst = con
						.prepareStatement(IQueryMapper.REJECT_APPLICANT)) {

			pst.setInt(1, application_Id);

			int result = pst.executeUpdate();
			if (result > 0) {
				valid = true;
			} else {
				valid = false;
				System.out.println("No applicant found with entered Id");
			}

		} catch (SQLException exp) {
			classLogger.error(exp);
			throw new UasException("Failed to update reject status ");
		}
		return valid;
	}

	@Override
	public boolean confirmApplication(int application_Id) throws UasException {
		boolean valid = false;

		try (Connection con = conPro.getConnection();
				PreparedStatement pst = con
						.prepareStatement(IQueryMapper.CONFIRM_APPLICANT)) {

			pst.setInt(1, application_Id);

			int result = pst.executeUpdate();

			if (result > 0) {
				valid = true;
			} else {
				valid = false;
				System.out.println("No applicant found with entered Id");
			}
		} catch (SQLException exp) {
			classLogger.error(exp);
			throw new UasException("Failed to update confirm status ");
		}
		return valid;
	}

	@Override
	public boolean updProgramOffered(Programs_Offered program_offered,
			String ProgramName) throws UasException {

		boolean valid = false;

		try (Connection con = conPro.getConnection();
				PreparedStatement pst = con
						.prepareStatement(IQueryMapper.UPD_PROG_OFFERED)) {

			pst.setString(1, program_offered.getProgramName());
			pst.setString(2, program_offered.getDescription());
			pst.setString(3, program_offered.getApplicant_Eligibility());
			pst.setInt(4, program_offered.getDuration());
			pst.setString(5, program_offered.getDegree_Certificate_Offered());
			pst.setString(6, ProgramName);

			int result = pst.executeUpdate();
			if (result > 0) {
				valid = true;
			} else {
				System.out.println("Invalid ProgramName Entered to update");
				valid = false;
			}
		} catch (SQLException msg) {
			classLogger.error(msg);
			throw new UasException("Failed to Update Programs offered");
		}

		return valid;
	}

	@Override
	public boolean addProgramOffered(Programs_Offered program_offered)
			throws UasException {
		boolean valid = false;

		try (Connection con = conPro.getConnection();
				PreparedStatement pst = con
						.prepareStatement(IQueryMapper.ADD_PROG_OFFERED)) {

			pst.setString(1, program_offered.getProgramName());
			pst.setString(2, program_offered.getDescription());
			pst.setString(3, program_offered.getApplicant_Eligibility());
			pst.setInt(4, program_offered.getDuration());
			pst.setString(5, program_offered.getDegree_Certificate_Offered());

			/* System.out.println(program_offered.toString()); */
			int result = pst.executeUpdate();

			if (result > 0) {
				valid = true;
			} else {
				System.out
						.println("Invalid deta entered to add programs offered");
				valid = false;
			}
		} catch (SQLException msg) {
			classLogger.error(msg);
			throw new UasException("Failed to Add Programs offered");
		}

		return valid;
	}

	@Override
	public boolean delProgramOffered(String programName) throws UasException {

		boolean valid = false;
		try (Connection con = conPro.getConnection();
				PreparedStatement pst = con
						.prepareStatement(IQueryMapper.DEL_PROG_OFFERED)) {

			pst.setString(1, programName);
			int result = pst.executeUpdate();
			if (result > 0) {
				valid = true;
			} else {
				valid = false;
			}
		} catch (SQLException exp) {
			classLogger.error(exp);
			throw new UasException("Failed to delete programs offered");
		}

		return valid;
	}

	@Override
	public boolean updProgramScheduled(Programs_Scheduled program_Scheduled,
			String scheduled_Program_Id) throws UasException {
		// TODO Auto-generated method stub
		boolean valid = false;

		try (Connection con = conPro.getConnection();
				PreparedStatement pst = con
						.prepareStatement(IQueryMapper.UPD_PROG_SCHEDULED)) {

			pst.setString(1, program_Scheduled.getScheduled_Program_Id());
			pst.setString(2, program_Scheduled.getProgramName());
			pst.setString(3, program_Scheduled.getCity());
			pst.setInt(4, program_Scheduled.getPincode());
			pst.setString(5, program_Scheduled.getStart_Date().toString());
			pst.setString(6, program_Scheduled.getEnd_Date().toString());
			pst.setInt(7, program_Scheduled.getSessions_Per_Week());
			pst.setString(8, scheduled_Program_Id);

			int result = pst.executeUpdate();
			if (result > 0) {
				valid = true;
			} else {
				System.out
						.println("Invalid deta entered to update programs scheduled");
				valid = false;
			}
		} catch (SQLException msg) {
			classLogger.error(msg);
			throw new UasException("Failed to update Programs scheduled");
		}

		return valid;
	}

	@Override
	public boolean addProgramScheduled(Programs_Scheduled program_Scheduled)
			throws UasException {
		// TODO Auto-generated method stub
		boolean valid = false;

		try (Connection con = conPro.getConnection();
				PreparedStatement pst = con
						.prepareStatement(IQueryMapper.ADD_PROG_SCHEDULED)) {
			/* System.out.println(program_Scheduled.toString()); */

			pst.setString(1, program_Scheduled.getScheduled_Program_Id());
			pst.setString(2, program_Scheduled.getProgramName());
			pst.setString(3, program_Scheduled.getCity());
			pst.setInt(4, program_Scheduled.getPincode());
			pst.setDate(5,
					java.sql.Date.valueOf(program_Scheduled.getStart_Date()));
			pst.setDate(6,
					java.sql.Date.valueOf(program_Scheduled.getEnd_Date()));
			pst.setInt(7, program_Scheduled.getSessions_Per_Week());

			int result = pst.executeUpdate();
			if (result > 0) {
				valid = true;
			} else {
				System.out
						.println("Invalid data entered to add programs scheduled");
				valid = false;
			}
		} catch (SQLException msg) {
			classLogger.error(msg);
			throw new UasException("Failed to add Programs scheduled");
		}
		return valid;
	}

	@Override
	public boolean delProgramScheduled(String scheduled_Program_Id)
			throws UasException {
		boolean valid = false;
		try (Connection con = conPro.getConnection();
				PreparedStatement pst = con
						.prepareStatement(IQueryMapper.DEL_PROG_SCHEDULED)) {

			pst.setString(1, scheduled_Program_Id);
			int result = pst.executeUpdate();
			if (result > 0) {
				valid = true;
			} else {
				valid = false;
			}
		} catch (SQLException exp) {
			classLogger.error(exp);
			throw new UasException("Failed to delete programs scheduled");
		}

		return valid;
	}

	@Override
	public List<Application> viewApplicants() throws UasException {

		List<Application> applicantsList = new ArrayList<>();
		try (Connection con = conPro.getConnection();
				PreparedStatement pst = con
						.prepareStatement(IQueryMapper.VIEW_ALL_APPLICANTS)) {

			ResultSet results = pst.executeQuery();

			while (results.next()) {
				applicantsList.add(mapApplication(results));
			}
		} catch (SQLException exp) {
			classLogger.error(exp);
			throw new UasException("Failed to get Applicants List!");
		}

		return applicantsList;

	}

	@Override
	public List<Programs_Scheduled> viewPrograms(LocalDate startDate,
			LocalDate endDate) throws UasException {

		List<Programs_Scheduled> programsList = new ArrayList<>();

		try (Connection con = conPro.getConnection();
				PreparedStatement pst = con
						.prepareStatement(IQueryMapper.VIEW_PROGS_TIME_PERIOD)) {

			pst.setDate(1, java.sql.Date.valueOf(startDate));
			pst.setDate(2, java.sql.Date.valueOf(endDate));

			ResultSet results = pst.executeQuery();

			while (results.next()) {
				programsList.add(mapProgram(results));
			}
		} catch (SQLException msg) {
			classLogger.error(msg);
			throw new UasException("Failed to get program lists");
		}

		return programsList;
	}

}
