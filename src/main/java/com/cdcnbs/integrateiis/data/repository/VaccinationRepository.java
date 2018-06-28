package com.cdcnbs.integrateiis.data.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cdcnbs.integrateiis.data.entity.Vaccination;

@Repository
public interface VaccinationRepository extends CrudRepository<Vaccination, Long> {
	
	//List<Vaccination> findByPatientID(Long PatientID);

}
