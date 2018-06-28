package com.cdcnbs.integrateiis.data.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cdcnbs.integrateiis.data.entity.Patient;

@Repository
public interface PatientRepository extends CrudRepository<Patient, Long>{
	
	//Patient findByPatientIdentifier(String patientIdentifier);

}
