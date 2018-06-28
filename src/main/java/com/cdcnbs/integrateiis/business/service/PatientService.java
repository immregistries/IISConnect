package com.cdcnbs.integrateiis.business.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdcnbs.integrateiis.data.entity.Patient;
import com.cdcnbs.integrateiis.data.repository.PatientRepository;
import com.cdcnbs.integrateiis.data.repository.VaccinationRepository;


@Service
public class PatientService {
	private PatientRepository patientRepository;
	private VaccinationRepository vaccinationRepository;
	public PatientRepository getPatientRepository() {
		return patientRepository;
	}
	public void setPatientRepository(PatientRepository patientRepository) {
		this.patientRepository = patientRepository;
	}
	public VaccinationRepository getVaccinationRepository() {
		return vaccinationRepository;
	}
	public void setVaccinationRepository(VaccinationRepository vaccinationRepository) {
		this.vaccinationRepository = vaccinationRepository;
	}
	
	@Autowired
	public PatientService(PatientRepository patientRepository, VaccinationRepository vaccinationRepository) {				
		this.patientRepository = patientRepository;
		this.vaccinationRepository = vaccinationRepository;
	}
	
	public List<Patient> getPatients(){
		
		Iterable<Patient> patients = this.patientRepository.findAll();
		
		List<Patient> ret = new ArrayList<Patient>();
		patients.forEach(ret::add);
		return ret;
		
	}
	

}
