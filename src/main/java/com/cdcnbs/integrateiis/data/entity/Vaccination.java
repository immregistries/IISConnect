package com.cdcnbs.integrateiis.data.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="Vaccination")
public class Vaccination {
	
	//VaccinationID BIGINT AUTO_INCREMENT PRIMARY KEY,
	@Id
	@Column(name="VaccinationID")	
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private long PatientID;
	private Date AdministrationDate;
	private String VaccineLabel;
	private String VaccineCvx;
		
	private String CompletionStatus;
	private String ManufacturerLabel;
	private String ManufacturerMvx;
	private String ActionCode;
	private String Completion; 
	private String InformationSource;
	private String InformationSourceLabel;

	private String RefusalReason;
	private String LotNumber;
	
	private String HL7Response;
	
	private String LastModifiedBy;
	private String AdministeredAmount;
	private String AdministeredAmountUnit;
	private Date LastModified;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public long getPatientID() {
		return PatientID;
	}
	public void setPatientID(long id) {
		this.PatientID = id;
	}
	
	public String getVaccineLabel() {
		return VaccineLabel;
	}
	public void setVaccineLabel(String vaccineLabel) {
		VaccineLabel = vaccineLabel;
	}
	
	public String getCompletion() {
		return Completion;
	}
	public void setCompletion(String completion) {
		Completion = completion;
	}

	public String getRefusalReason() {
		return RefusalReason;
	}
	public void setRefusalReason(String refusal) {
		RefusalReason = refusal;
	}
	public String getLotNumber() {
		return LotNumber;
	}
	public void setLotNumber(String lotNumber) {
		LotNumber = lotNumber;
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
	public String getAdministeredAmount() {
		return AdministeredAmount;
	}
	public void setAdministeredAmount(String administeredAmount) {
		AdministeredAmount = administeredAmount;
	}
	public String getAdministeredAmountUnit() {
		return AdministeredAmountUnit;
	}
	public void setAdministeredAmountUnit(String administeredAmountUnit) {
		AdministeredAmountUnit = administeredAmountUnit;
	}
	public String getInformationSource() {
		return InformationSource;
	}
	public void setInformationSource(String informationSource) {
		InformationSource = informationSource;
	}
	public String getInformationSourceLabel() {
		return InformationSourceLabel;
	}
	public void setInformationSourceLabel(String informationSourceLabel) {
		InformationSourceLabel = informationSourceLabel;
	}
	public Date getAdministrationDate() {
		return AdministrationDate;
	}
	public void setAdministrationDate(Date administrationDate) {
		AdministrationDate = administrationDate;
	}
	public String getActionCode() {
		return ActionCode;
	}
	public void setActionCode(String actionCode) {
		ActionCode = actionCode;
	}
	public String getVaccineCvx() {
		return VaccineCvx;
	}
	public void setVaccineCvx(String vaccineCvx) {
		VaccineCvx = vaccineCvx;
	}
	
	public String getCompletionStatus() {
		return CompletionStatus;
	}
	public void setCompletionStatus(String completionStatus) {
		CompletionStatus = completionStatus;
	}
	public String getManufacturerMvx() {
		return ManufacturerMvx;
	}
	public void setManufacturerMvx(String manufacturerMvx) {
		ManufacturerMvx = manufacturerMvx;
	}
	public String getManufacturerLabel() {
		return ManufacturerLabel;
	}
	public void setManufacturerLabel(String manufacturerLabel) {
		ManufacturerLabel = manufacturerLabel;
	}
	
	public String getHL7Response() {
		return HL7Response;
	}
	public void setHL7Response(String hL7Response) {
		HL7Response = hL7Response;
	}
	
	
}
