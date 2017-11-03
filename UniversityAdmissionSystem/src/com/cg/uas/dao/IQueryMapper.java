package com.cg.uas.dao;

public interface IQueryMapper {

	// ----------------admin----------------//
	public static final String UPD_PROG_OFFERED = "UPDATE Programs_offered SET ProgramName=?,description=?,applicant_eligibility=?,duration=?,degree_certificate_offered=? WHERE ProgramName=?";
	public static final String ADD_PROG_OFFERED = "INSERT INTO Programs_offered (ProgramName,description,applicant_eligibility,duration,degree_certificate_offered) VALUES(?,?,?,?,?)";
	public static final String DEL_PROG_OFFERED = "DELETE FROM Programs_offered WHERE ProgramName=?";

	public static final String UPD_PROG_SCHEDULED = "UPDATE Programs_Scheduled SET Scheduled_program_id=?,ProgramName=?,City=?,Pincode=?,start_date=?,end_date=?,sessions_per_week=? WHERE Scheduled_program_id=?";
	public static final String ADD_PROG_SCHEDULED = "INSERT INTO Programs_Scheduled (Scheduled_program_id,ProgramName,City,Pincode,start_date,end_date,sessions_per_week) VALUES(?,?,?,?,?,?,?)";
	public static final String DEL_PROG_SCHEDULED = "DELETE FROM Programs_Scheduled WHERE Scheduled_program_id=?";

	public static final String VIEW_ALL_APPLICANTS = "SELECT * FROM Application WHERE status!='applied'";
	public static final String VIEW_PROGS_TIME_PERIOD = "SELECT * FROM Programs_Scheduled WHERE Start_date>? AND End_date<?";

	// ------------login---------------------//
	public static final String LOGIN_VERIFY_ADMIN = "SELECT login_Id,password FROM USERS WHERE login_Id=? AND password=? AND role='admin'";
	public static final String LOGIN_VERIFY_MAC = "SELECT login_Id,password FROM USERS WHERE login_Id=? AND password=? AND role='mac'";

	// ------------applicant-----------------//
	public static final String VIEW_ALL_PROGS = "SELECT * FROM Programs_Scheduled";
	public static final String APPLY = "INSERT INTO Application (Application_id,full_name,date_of_birth,highest_qualification,marks_obtained,goals,email_id,Scheduled_program_id,status,address)	VALUES(Application_id.nextval,?,?,?,?,?,?,?,?,?)";
	public static final String APPLICATION_ID= "SELECT application_Id.Currval FROM dual";
	public static final String VIEW_STATUS = "SELECT Status FROM Application WHERE application_id=?";

	// ------------MAC-----------------------//

	public static final String ALL_APPLICATIONS_ONE_PROG = "SELECT * FROM Application App INNER JOIN Programs_Scheduled Prog ON App.Scheduled_program_id = Prog.Scheduled_program_id WHERE programName=?";
	public static final String ACCEPT_APPLICANT = "UPDATE Application SET Status='accepted',Date_Of_Interview=? WHERE application_Id=?";
	public static final String REJECT_APPLICANT = "UPDATE Application SET Status='rejected' WHERE application_Id=?";
	public static final String CONFIRM_APPLICANT = "UPDATE Application SET Status='confirmed' WHERE application_Id=?";
}
