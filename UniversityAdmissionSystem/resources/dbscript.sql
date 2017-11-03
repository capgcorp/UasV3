DROP TABLE 	Programs_Scheduled  ;
DROP TABLE 	Participant  ;
DROP TABLE 	Application;
DROP TABLE 	Programs_Offered ;
DROP TABLE 	Users  ;

CREATE TABLE Programs_Offered ( 
	ProgramName					   VARCHAR2(5) 	 PRIMARY KEY, 
	description			 		   VARCHAR2(20), 
	applicant_eligibility          VARCHAR2(40) , 
	duration                       NUMBER (2), 
	degree_certificate_offered     VARCHAR2(10)
); 


CREATE TABLE  Programs_Scheduled ( 
Scheduled_program_id 				VARCHAR2(5)  PRIMARY KEY,
ProgramName 						VARCHAR2(5), 
City     							VARCHAR2(10),
Pincode								Number(6),
start_date							DATE, 
end_date 							DATE, 
sessions_per_week					NUMBER (2),

FOREIGN KEY (ProgramName) REFERENCES Programs_Offered(ProgramName) ON DELETE CASCADE
); 



CREATE TABLE Application ( 
		Application_id    			NUMBER(6)      PRIMARY KEY, 
		full_name        		 	VARCHAR2(20)   NOT NULL, 
		date_of_birth     			DATE,
	 	highest_qualification 		VARCHAR2(10), 
	 	marks_obtained 				NUMBER(6),
		goals 						VARCHAR2(20), 
	 	email_id  					VARCHAR2(20),
	 	Scheduled_program_id  		VARCHAR2(5), 
	 	status  					VARCHAR2(10),
	 	Date_Of_Interview 			DATE,
		address       				VARCHAR2(30),
		FOREIGN KEY (Scheduled_program_id) REFERENCES  Programs_Scheduled(Scheduled_program_id) ON DELETE CASCADE
); 



CREATE TABLE Participant ( 
Roll_no 							VARCHAR2(5) PRIMARY KEY, 
email_id 							VARCHAR2(20),
Application_id 						NUMBER(6),
Scheduled_program_id				VARCHAR2(5),
FOREIGN KEY (Application_id) REFERENCES Application(Application_id)
);


CREATE TABLE Users ( 
login_id							VARCHAR2(5) PRIMARY KEY,
password							VARCHAR2(10) NOT NULL, 
role								VARCHAR2(5) NOT NULL	
);




DROP SEQUENCE Application_id ;

CREATE SEQUENCE Application_id
START WITH 1000
INCREMENT BY 1;