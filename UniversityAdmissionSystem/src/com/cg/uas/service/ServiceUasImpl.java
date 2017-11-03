package com.cg.uas.service;

import java.time.LocalDate;
import java.util.List;

import com.cg.uas.dao.DaoUasImpl;
import com.cg.uas.dao.IDaoUas;
import com.cg.uas.dto.Application;
import com.cg.uas.dto.Programs_Offered;
import com.cg.uas.dto.Programs_Scheduled;
import com.cg.uas.exception.UasException;

public class ServiceUasImpl implements IServiceUas {

	private IDaoUas dao = null;

	public ServiceUasImpl() throws UasException {
		dao = new DaoUasImpl();
	}

	@Override
	public List<Programs_Scheduled> allPrograms() throws UasException {
		// TODO Auto-generated method stub
		return dao.allPrograms();
	}

	@Override
	public int apply(Application application) throws UasException {
		// TODO Auto-generated method stub
		return dao.apply(application);
	}

	@Override
	public String status(int application_Id) throws UasException {

		return dao.status(application_Id);
	}

	@Override
	public boolean macLogin(String userName, String Password)
			throws UasException {

		return dao.macLogin(userName, Password);
	}

	@Override
	public boolean adminLogin(String userName, String Password)
			throws UasException {
		// TODO Auto-generated method stub
		return dao.adminLogin(userName, Password);
	}

	@Override
	public List<Application> allApplications(String programName)
			throws UasException {
		// TODO Auto-generated method stub
		return dao.allApplications(programName);
	}

	@Override
	public boolean acceptApplication(int application_Id, LocalDate iDate)
			throws UasException {
		// TODO Auto-generated method stub
		return dao.acceptApplication(application_Id, iDate);
	}

	@Override
	public boolean rejectApplication(int application_Id) throws UasException {
		// TODO Auto-generated method stub
		return dao.rejectApplication(application_Id);
	}

	@Override
	public boolean confirmApplication(int application_Id) throws UasException {
		// TODO Auto-generated method stub
		return dao.confirmApplication(application_Id);
	}

	@Override
	public boolean updProgramOffered(Programs_Offered program_offered,
			String ProgramName) throws UasException {
		// TODO Auto-generated method stub
		return dao.updProgramOffered(program_offered, ProgramName);
	}

	@Override
	public boolean addProgramOffered(Programs_Offered program_offered)
			throws UasException {
		// TODO Auto-generated method stub
		return dao.addProgramOffered(program_offered);
	}

	@Override
	public boolean delProgramOffered(String ProgramName) throws UasException {
		// TODO Auto-generated method stub
		return dao.delProgramOffered(ProgramName);
	}

	@Override
	public boolean updProgramScheduled(Programs_Scheduled program_Scheduled,
			String scheduled_Program_Id) throws UasException {
		// TODO Auto-generated method stub
		return dao.updProgramScheduled(program_Scheduled, scheduled_Program_Id);
	}

	@Override
	public boolean addProgramScheduled(Programs_Scheduled program_Scheduled)
			throws UasException {
		// TODO Auto-generated method stub
		return dao.addProgramScheduled(program_Scheduled);
	}

	@Override
	public boolean delProgramScheduled(String scheduled_Program_Id)
			throws UasException {
		// TODO Auto-generated method stub
		return dao.delProgramScheduled(scheduled_Program_Id);
	}

	@Override
	public List<Application> viewApplicants() throws UasException {
		// TODO Auto-generated method stub
		return dao.viewApplicants();
	}

	@Override
	public List<Programs_Scheduled> viewPrograms(LocalDate startDate, LocalDate endDate) throws UasException {
		// TODO Auto-generated method stub
		return dao.viewPrograms(startDate, endDate);
	}

}
