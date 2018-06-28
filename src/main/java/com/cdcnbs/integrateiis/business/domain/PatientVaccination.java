package com.cdcnbs.integrateiis.business.domain;

import java.util.Date;

public class PatientVaccination {

	private long patientID;
	private long vaccinationID;
	
	private String firstName;
	
	private String lastName;
	
	private Date dob;
	
	private Date vaccinationDate;
	
	private String vaccinationDescription;
	
	private String vaccinationCode;

	
	
	public long getPatientID() {
		return patientID;
	}

	public void setPatientID(long patientID) {
		this.patientID = patientID;
	}

	public long getVaccinationID() {
		return vaccinationID;
	}

	public void setVaccinationID(long vaccinationID) {
		this.vaccinationID = vaccinationID;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public Date getVaccinationDate() {
		return vaccinationDate;
	}

	public void setVaccinationDate(Date vaccinationDate) {
		this.vaccinationDate = vaccinationDate;
	}

	public String getVaccinationDescription() {
		return vaccinationDescription;
	}

	public void setVaccinationDescription(String vaccinationDescription) {
		this.vaccinationDescription = vaccinationDescription;
	}

	public String getVaccinationCode() {
		return vaccinationCode;
	}

	public void setVaccinationCode(String vaccinationCode) {
		this.vaccinationCode = vaccinationCode;
	}
	
	
	
}
