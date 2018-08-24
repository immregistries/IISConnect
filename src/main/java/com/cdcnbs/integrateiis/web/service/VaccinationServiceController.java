	package com.cdcnbs.integrateiis.web.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.commons.io.FileUtils;
import org.immregistries.smm.mover.ConnectionManager;
import org.immregistries.smm.mover.SendData;
import org.immregistries.smm.mover.ShutdownInterceptor;
import org.immregistries.smm.tester.connectors.Connector;
import org.immregistries.smm.tester.manager.query.PatientIdType;
import org.immregistries.smm.tester.manager.query.QueryConverter;
import org.immregistries.smm.tester.manager.query.QueryConverterQBPZ34;
import org.immregistries.smm.tester.manager.query.QueryRequest;
import org.immregistries.smm.tester.manager.query.QueryResponse;
import org.immregistries.smm.tester.manager.response.ImmunizationMessage;
import org.immregistries.smm.tester.manager.response.ResponseReader;
import org.immregistries.smm.transform.PatientType;
import org.immregistries.smm.transform.ScenarioManager;
import org.immregistries.smm.transform.TestCaseMessage;
import org.immregistries.smm.transform.TransformRequest;
import org.immregistries.smm.transform.Transformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cdcnbs.IISCore.Data.IISData;
import com.cdcnbs.IISCore.Data.Person;
import com.cdcnbs.IISCore.Service.IISService;
import com.cdcnbs.integrateiis.business.domain.VaccinationData;
import com.cdcnbs.integrateiis.data.entity.Patient;
import com.cdcnbs.integrateiis.data.entity.Vaccination;
import com.cdcnbs.integrateiis.data.repository.PatientRepository;
import com.cdcnbs.integrateiis.utils.Messaging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



@RestController
@RequestMapping(value="/api")
@Component

public class VaccinationServiceController {

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	private  String readIISConnection() {
		try {
			return FileUtils.readFileToString(new File(IISConfig));
		} catch (IOException e) {
			e.printStackTrace();
			log.error(e.getLocalizedMessage());
			return null;
		}
	}
	
	@Autowired
    private PatientRepository patientRepository;
	
	@Value("${iisservice.Config}")
    private String IISConfig;
	
	
	@RequestMapping(method=RequestMethod.GET,value="vaccinations/{id}")
	public IISData getVaccinationsByID(@PathVariable(value="id") Long patientID){
		
		// Use the IISCore project to communicate with the State IIS
		Optional<Patient> p = patientRepository.findById(patientID);
		Patient patient = p.get();
		
		// Use constructor to load connection information.
		Person person = new Person(readIISConnection());
		
		person.setAddressLine1(patient.getAddressLine1());
		person.setAddressLine2(patient.getAddressLine2());
		person.setAreaCode(patient.getAreaCode());
		person.setAuthority(patient.getAuthority());
		person.setCity(patient.getCity());
		person.setCountry(patient.getCountry());
		person.setDOB(patient.getDOB());
		person.setFirstName(patient.getFirstName());
		person.setLastName(patient.getLastName());
		person.setMaidenName(patient.getMaidenName());
		person.setMotherFirstName(patient.getMotherFirstName());
		person.setMotherLastName(patient.getMotherLastName());
		person.setPhoneNumber(patient.getPhoneNumber());
		person.setPatientIdentifier(patient.getPatientIdentifier());
		person.setPatientIdentifierType(patient.getPatientIdentifierType());
		
		// Include patient
		try {
			IISData ret = IISService.queryIIS(person);	
			System.out.println(ret.getPatient().toString());
			System.out.println(ret.getRequestHL7());
			System.out.println(ret.getResponseHL7());
			System.out.println(ret.getQueryResponse().toString());
			return ret;
		} 
		catch (Exception e1) {
			// Log error	
			log.error(e1.getLocalizedMessage());			
		}
		
		// return null
		return null;
		
	}
	
	
	@RequestMapping(value = "search", method = RequestMethod.POST)	
	public IISData search(@RequestBody Person person) {
		
		// Use the IISCore project to communicate with the State IIS
		
		// set the connection credentials.
		person.setIISConfig(readIISConnection());
		try {
			IISData ret = IISService.queryIIS(person);		
			return ret;
		} 
		catch (Exception e1) {
			// Log error	
			log.error(e1.getLocalizedMessage());			
		}
		
		// return null
		return null;
	}
}

