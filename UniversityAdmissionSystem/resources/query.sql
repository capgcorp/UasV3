DESC Programs_offered;
DESC Programs_Scheduled;
DESC Application;

SELECT * FROM Programs_offered;
SELECT * FROM Programs_Scheduled;
SELECT * FROM Application;
SELECT * FROM Application WHERE Application_Id=2;



INSERT INTO Users VALUES('admin','admin','admin');
INSERT INTO Users VALUES('mac','mac','mac');
SELECT login_Id,password FROM USERS WHERE login_Id='admin' AND password='admin' AND role='admin';
SELECT login_Id,password FROM USERS WHERE login_Id='mac' AND password='mac' AND role='mac';
select 


INSERT INTO Application (Application_id,full_name,date_of_birth,highest_qualification,marks_obtained,
		goals,email_id,Scheduled_program_id,status,Date_Of_Interview,address)
		VALUES(2,'darwin','21-MAR-1996','B.tech',87,'developer','darwin2@gmail.com','101','accepted','23-OCT-2017','Hyd');

INSERT INTO Application (Application_id,full_name,date_of_birth,highest_qualification,marks_obtained,
		goals,email_id,Scheduled_program_id,status,Date_Of_Interview,address)
		VALUES(3,'darwin','21-MAR-1996','B.tech',87,'developer','darwin3@gmail.com','101','confirmed','23-OCT-2017','Hyd');
INSERT INTO Application (Application_id,full_name,date_of_birth,highest_qualification,marks_obtained,
		goals,email_id,Scheduled_program_id,status,Date_Of_Interview,address)
		VALUES(4,'darwin','21-MAR-1996','B.tech',87,'developer','darwin4@gmail.com','101','rejected','23-OCT-2017','Hyd');
		
INSERT INTO Application (Application_id,full_name,date_of_birth,highest_qualification,marks_obtained,
		goals,email_id,Scheduled_program_id,status,Date_Of_Interview,address)
		VALUES(5,'karan','21-MAR-1996','B.tech',87,'developer','darwin4@gmail.com','103','rejected','23-OCT-2017','Hyd');
INSERT INTO Application (Application_id,full_name,date_of_birth,highest_qualification,marks_obtained,
		goals,email_id,Scheduled_program_id,status,Date_Of_Interview,address)
		VALUES(6,'deep','21-MAR-1996','B.tech',87,'developer','darwin4@gmail.com','103','rejected','23-OCT-2017','Hyd');


//----------------------app------------------		
SELECT * FROM Programs_Scheduled;
INSERT INTO Application (Application_id,full_name,date_of_birth,highest_qualification,marks_obtained,
		goals,email_id,Scheduled_program_id,status,Date_Of_Interview,address)
		VALUES(1,'darwin','21-MAR-1996','B.tech',87,'developer','darwin@gmail.com','101','applied','23-OCT-2017','Hyd');
SELECT application_Id.Currval FROM dual
SELECT Status FROM Application WHERE application_id=1;



//----------------mac------------------
SELECT * FROM Application App INNER JOIN Programs_Scheduled Prog ON App.Scheduled_program_id = Prog.Scheduled_program_id WHERE programName='java';
UPDATE Application SET Status='accepted',Date_Of_Interview='25-oct-10' WHERE application_Id=5;


//----------------admin-----------------
INSERT INTO Programs_offered (ProgramName,description,applicant_eligibility,duration,degree_certificate_offered) VALUES('java','language','B.Tech',60,'OCJP');
UPDATE Programs_offered SET ProgramName='orcl',description='dbms',applicant_eligibility='b tech',duration='60',degree_certificate_offered='oracle' WHERE ProgramName='java';
DELETE FROM Programs_offered WHERE ProgramName='java';


INSERT INTO Programs_Scheduled (Scheduled_program_id,ProgramName,City,Pincode,start_date,end_date,sessions_per_week) VALUES(103,'java','bnglr',560066,'20-oct-17','30-oct-17',4); 
UPDATE Programs_Scheduled SET Scheduled_program_id='101',ProgramName='orcl',City='hyd',Pincode='522006',start_date='20-oct-17',end_date='30-oct-17',sessions_per_week=5 WHERE Scheduled_program_id=100;
DELETE FROM Programs_Scheduled WHERE Scheduled_program_id=100;
SELECT * FROM Application WHERE status!='applied'; 

SELECT * FROM Programs_Scheduled WHERE Start_date>'18-OCT-2017' AND End_date<'31-oct-2017';



//------------------------------login------------------------------------//
SELECT login_Id,password FROM USERS WHERE login_Id=? AND password=? AND role='admin';
SELECT login_Id,password FROM USERS WHERE login_Id=? AND password=? AND role='mac';


//------------------------Applicant-------------------------------------//
SELECT * FROM Programs_Scheduled;
INSERT INTO Application (Application_id.nextval,full_name,date_of_birth,highest_qualification,marks_obtained,
		goals,email_id,Scheduled_program_id,status,Date_Of_Interview,address)
		VALUES(Application_id.nextval,?,?,?,?,?,?,?,?,?,?);
SELECT application_Id.Currval FROM dual;
SELECT Status FROM Application WHERE application_id=?;

//--------------------------MAC-------------------------------------//
SELECT * FROM Application App INNER JOIN Programs_Scheduled Prog ON App.Scheduled_program_id = Prog.Scheduled_program_id WHERE programName=?;
UPDATE Application SET Status='accepted',Date_Of_Interview=? WHERE application_Id=?;
UPDATE Application SET Status='rejected' WHERE application_Id=?
UPDATE Application SET Status='confirmed' WHERE application_Id=?


//----------------------------------admin -----------------------------//

UPDATE Programs_offered SET ProgramName=?,description=?,applicant_eligibility=?,duration=?,degree_certificate_offered=? WHERE ProgramName=?;
INSERT INTO Programs_offered (ProgramName,description,applicant_eligibility,duration,degree_certificate_offered) VALUES(?,?,?,?,?);
DELETE FROM Programs_offered WHERE ProgramName=?;

UPDATE Programs_Scheduled SET Scheduled_program_id=?,ProgramName=?,City=?,Pincode=?,start_date=?,end_date=?,sessions_per_week=? WHERE Scheduled_program_id=?;
INSERT INTO Programs_Scheduled (Scheduled_program_id,ProgramName,City,Pincode,start_date,end_date,sessions_per_week) VALUES(?,?,?,?,?,?,?);
DELETE FROM Programs_Scheduled WHERE Scheduled_program_id=?;

SELECT * FROM Application WHERE status!='applied';

SELECT * FROM Programs_Scheduled WHERE Start_date>? AND End_date<?;



