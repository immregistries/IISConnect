package com.cdcnbs.integrateiis.web.application;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.immregistries.smm.SoftwareVersion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.cdcnbs.integrateiis.business.service.PatientService;
import com.cdcnbs.integrateiis.data.entity.Enums.PatientIdentifierType;
import com.cdcnbs.integrateiis.data.entity.Patient;
import com.cdcnbs.integrateiis.data.repository.PatientRepository;



@Controller
@RequestMapping("/patient")
public class PatientController {
	
	@Autowired
    private PatientRepository patientRepository;
	
	@RequestMapping(value="/list",method = RequestMethod.GET)
	public String getPatients(Model model) {
		
		Iterable<Patient> iPatients = this.patientRepository.findAll();
		
		List<Patient> patients = new ArrayList<Patient>();
		iPatients.forEach(patients::add);
		
		model.addAttribute("patients", patients);
		
		return "patients";
	}
	
	@RequestMapping(value="/detail/{id}",method = RequestMethod.GET)
	public String getPatient(@PathVariable(name="id") Long patientID, Model model)  {
		
		Optional<Patient> patient = this.patientRepository.findById(patientID);
		
		
		SoftwareVersion v = new SoftwareVersion();
		model.addAttribute("patient", patient.get());
		
		return "patient";
	}
	
	@RequestMapping(value="/search",method = RequestMethod.GET)
	public String getSearch(Model model)  {
		
		
		return "search";
	}
	
	
	
}
