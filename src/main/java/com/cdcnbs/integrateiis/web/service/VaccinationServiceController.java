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
	public VaccinationData getVaccinationsByID(@PathVariable(value="id") Long patientID){
		
		VaccinationData ret = new VaccinationData();
		String responseMessage = "";
		Optional<Patient> p = patientRepository.findById(patientID);
		Patient patient = p.get();
		
		// Include patient
		ret.setPatient(patient);

		// Map patient record to query request object;
		QueryRequest queryRequest = Messaging.getQueryRequestFromPatient(patient);
		
		TestCaseMessage testCaseMessage = Messaging.createTestCaseMessage(queryRequest);

        // Request query type to get vaccine records.
		QueryConverter queryConverter = new QueryConverterQBPZ34();
              
        // Get HL7 representation of patient data.  
        TestCaseMessage queryTestCaseMessage = new TestCaseMessage();
        queryTestCaseMessage.setMessageText(queryConverter.convert(testCaseMessage.getMessageText()));
    
        // Clean the text
        String message = Messaging.cleanMessage(queryTestCaseMessage.getMessageText());
           
        
		try {
			 
			Boolean debug = true;
			
			// Create connector from Web Service configuration information stored in application.properties.
			Connector connector = Connector.makeConnectors(readIISConnection()).get(0);
			
			// custom transformations required by state IIS obtained from the connector.
			String transforms = connector.getCustomTransformations();
			Transformer transformer = new Transformer();
			
			message = transformer.transformForSubmitServlet(connector,message, transforms, null, null, null);
			
			// Include request message
			ret.setRequestHL7(message);
			
			// Get HL7 response from State IIS
			responseMessage = connector.submitMessage(message, debug);
			
			ret.setResponseHL7(responseMessage);
			
			// Get immunization objects
			ImmunizationMessage immunizationMessage = ResponseReader.readMessage(responseMessage);
			
			// Immunization Message includes properties for patient and patientList but they are null.
			// Discuss how to get these records returned.
			
			// get immunizations and map to Vaccination
			QueryResponse queryResponse = (QueryResponse) immunizationMessage;
			
			// Include 
			ret.setQueryResponse(queryResponse);
			
			
				
			return ret;
			
			
			
		} catch (Exception e1) {
			
			log.error(e1.getLocalizedMessage());
		}
	    
	    // return empty
		return ret;
		
		
		
		
	}
	
	@RequestMapping(value = "search", method = RequestMethod.POST)
	//public List<Vaccination> search(@RequestBody Patient patient) {
	public VaccinationData search(@RequestBody Patient patient) {
		String responseMessage = "";
		VaccinationData ret = new VaccinationData();
		
		QueryResponse queryResponse = new QueryResponse();
	    if (patient != null) {
	    	
	    	// Include the patient in the return object
	    	ret.setPatient(patient);
	    	
	    	// Map patient record to query request object;
			QueryRequest queryRequest = Messaging.getQueryRequestFromPatient(patient);
			
			TestCaseMessage testCaseMessage = Messaging.createTestCaseMessage(queryRequest);

	        // Request query type to get vaccine records.
			QueryConverter queryConverter = new QueryConverterQBPZ34();
	              
	        // Get HL7 representation of patient data.  
	        TestCaseMessage queryTestCaseMessage = new TestCaseMessage();
	        queryTestCaseMessage.setMessageText(queryConverter.convert(testCaseMessage.getMessageText()));
	    
	        // Clean the text
	        String message = Messaging.cleanMessage(queryTestCaseMessage.getMessageText());
	        
	         
	        
			try {
				 
				Boolean debug = true;
				
				// Create connector from Web Service configuration information stored in application.properties.
				Connector connector = Connector.makeConnectors(readIISConnection()).get(0);
				
				// custom transformations required by state IIS obtained from the connector.
				String transforms = connector.getCustomTransformations();
				Transformer transformer = new Transformer();
				
				message = transformer.transformForSubmitServlet(connector,message, transforms, null, null, null);
			
				// Include message in returned object.
		        ret.setRequestHL7(message);  
		        
				// Get HL7 response from State IIS and include in returned object
				responseMessage = connector.submitMessage(message, debug);
				ret.setResponseHL7(responseMessage);
				
				
				// Get immunization objects
				ImmunizationMessage immunizationMessage = ResponseReader.readMessage(responseMessage);
				
				// Immunization Message includes properties for patient and patientList but they are null.
				// Discuss how to get these records returned.
				
				// get immunizations and map to Vaccination
				queryResponse = (QueryResponse) immunizationMessage;
				
				// Include the query response 
				ret.setQueryResponse(queryResponse);
				return ret;

				
			} catch (Exception e1) {
				// Log error	
				log.error(e1.getLocalizedMessage());
			}		    
	    	
	    }

	    // return empty
	    return ret;
	}
}

