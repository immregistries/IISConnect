package com.cdcnbs.integrateiis.data.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.cdcnbs.integrateiis.data.entity.Enums.PatientIdentifierType;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.cdcnbs.integrateiis.data.entity.Enums.Gender;

@Entity
@Table(name="Patient")
public class Patient {
	
	@Id
	@Column(name="PatientID")	
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	private String PatientIdentifier;
	
	//@Enumerated
	private String PatientIdentifierType;
	
	private String Authority;
	private String FirstName;
	private String LastName;
	private String MiddleName;
	
	//@Enumerated
	private String Sex;
	
	
	private Date DOB;
	
	private String MotherFirstName;
	private String MotherLastName;
	private String MotherMiddleName;
	private String MaidenName;
	private String AddressLine1;
	private String AddressLine2;
	private String City;
	private String State;
	private String ZipCode;
	private String Country; 
	private String AreaCode;
	private String PhoneNumber;
	private String LastModifiedBy; 
	private Date LastModified;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getPatientIdentifier() {
		return PatientIdentifier;
	}
	public void setPatientIdentifier(String patientIdentifier) {
		PatientIdentifier = patientIdentifier;
	}
	public String getPatientIdentifierType() {
		return PatientIdentifierType;
	}
	public void setPatientIdentifierType(String patientIdentifierType) {
		PatientIdentifierType = patientIdentifierType;
	}
	public String getAuthority() {
		return Authority;
	}
	public void setAuthority(String authority) {
		Authority = authority;
	}
	public Date getDOB() {
		return DOB;
	}
	public void setDOB(Date dOB) {
		DOB = dOB;
	}
	public String getFirstName() {
		return FirstName;
	}
	public void setFirstName(String firstName) {
		FirstName = firstName;
	}
	public String getLastName() {
		return LastName;
	}
	public void setLastName(String lastName) {
		LastName = lastName;
	}
	public String getMiddleName() {
		return MiddleName;
	}
	public void setMiddleName(String middleName) {
		MiddleName = middleName;
	}
	public String getSex() {
		return Sex;
	}
	public void setSex(String sex) {
		Sex = sex;
	}
	public String getMotherFirstName() {
		return MotherFirstName;
	}
	public void setMotherFirstName(String motherFirstName) {
		MotherFirstName = motherFirstName;
	}
	public String getMotherLastName() {
		return MotherLastName;
	}
	public void setMotherLastName(String motherLastName) {
		MotherLastName = motherLastName;
	}
	public String getMotherMiddleName() {
		return MotherMiddleName;
	}
	public void setMotherMiddleName(String motherMiddleName) {
		MotherMiddleName = motherMiddleName;
	}
	public String getMaidenName() {
		return MaidenName;
	}
	public void setMaidenName(String maidenName) {
		MaidenName = maidenName;
	}
	public String getAddressLine1() {
		return AddressLine1;
	}
	public void setAddressLine1(String addressLine1) {
		AddressLine1 = addressLine1;
	}
	public String getAddressLine2() {
		return AddressLine2;
	}
	public void setAddressLine2(String addressLine2) {
		AddressLine2 = addressLine2;
	}
	public String getCity() {
		return City;
	}
	public void setCity(String city) {
		City = city;
	}
	public String getState() {
		return State;
	}
	public void setState(String state) {
		State = state;
	}
	public String getZipCode() {
		return ZipCode;
	}
	public void setZipCode(String zipCode) {
		ZipCode = zipCode;
	}
	public String getCountry() {
		return Country;
	}
	public void setCountry(String country) {
		Country = country;
	}
	public String getAreaCode() {
		return AreaCode;
	}
	public void setAreaCode(String areaCode) {
		AreaCode = areaCode;
	}
	public String getPhoneNumber() {
		return PhoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		PhoneNumber = phoneNumber;
	}
	public String getLastModifiedBy() {
		return LastModifiedBy;
	}
	public void setLastModifiedBy(String lastModifiedBy) {
		LastModifiedBy = lastModifiedBy;
	}
	public Date getLastModified() {
		return LastModified;
	}
	public void setLastModified(Date lastModified) {
		LastModified = lastModified;
	}
	
	

}
