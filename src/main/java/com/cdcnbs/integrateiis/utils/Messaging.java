package com.cdcnbs.integrateiis.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import org.immregistries.smm.tester.manager.query.PatientIdType;
import org.immregistries.smm.tester.manager.query.QueryRequest;
import org.immregistries.smm.transform.ScenarioManager;
import org.immregistries.smm.transform.TestCaseMessage;
import org.immregistries.smm.transform.Transformer;

import com.cdcnbs.integrateiis.data.entity.Patient;

public class Messaging {
	
	public static QueryRequest getQueryRequestFromPatient(Patient patient) {
		
		QueryRequest ret = new QueryRequest();
		//TODO: Transpose values of enums for gender and id type.
		//TODO handle null values for birth date.
		ret.setIdNumber(patient.getPatientIdentifier());
		ret.setIdAuthority(patient.getAuthority());
		ret.setIdType(PatientIdType.valueOf(patient.getPatientIdentifierType()));
		ret.setNameLast(patient.getLastName());
		ret.setNameFirst(patient.getFirstName());
		ret.setNameMiddle(Optional.ofNullable(patient.getMiddleName()).orElse(""));
		ret.setMotherNameMaiden(patient.getMaidenName());
		ret.setMotherNameLast(patient.getMotherLastName());
		ret.setMotherNameFirst(patient.getMotherFirstName());
		ret.setMotherNameMiddle(Optional.ofNullable(patient.getMotherMiddleName()).orElse(""));
		
		
		//SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");	
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		try {
			Date bDate = sdf.parse(sdf.format(patient.getDOB()));
			ret.setBirthDate(bDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
			    
	    //ret.setSex(patient.getGender().name());
	    ret.setSex(patient.getSex());
		ret.setAddressStreet1(patient.getAddressLine1());
	    ret.setAddressStreet2(patient.getAddressLine2());
	    ret.setAddressCity(patient.getCity());
	    ret.setAddressState(patient.getState());
	    ret.setAddressZip(patient.getZipCode());
	    ret.setAddressCountry(patient.getCountry());
	    ret.setPhoneArea(patient.getAreaCode());
	    ret.setPhoneLocal(patient.getPhoneNumber());
	    
	    //TODO Add these values in database.
	    ret.setMultipleBirthIndicator(false);
	    ret.setMultipleBirthOrder(0);
		return ret;
		
	}
	
	public static TestCaseMessage createTestCaseMessage(QueryRequest queryRequest) {
	    SimpleDateFormat sdfDateOnly = new SimpleDateFormat("yyyyMMdd");
	    SimpleDateFormat sdfDateAndTime = new SimpleDateFormat("yyyyMMddHHmmssZ");
	    TestCaseMessage testCaseMessage =
	        ScenarioManager.createTestCaseMessage(ScenarioManager.SCENARIO_1_1_ADMIN_CHILD);
	    testCaseMessage.appendCustomTransformation("PID-3.1=" + queryRequest.getIdNumber());
	    testCaseMessage.appendCustomTransformation("PID-3.4=" + queryRequest.getIdAuthority());
	    testCaseMessage.appendCustomTransformation("PID-3.5=" + queryRequest.getIdType());
	    testCaseMessage.appendCustomTransformation("PID-5.1=" + queryRequest.getNameLast());
	    testCaseMessage.appendCustomTransformation("PID-5.2=" + queryRequest.getNameFirst());
	    testCaseMessage.appendCustomTransformation("PID-5.3=" + queryRequest.getNameMiddle());
	    testCaseMessage.appendCustomTransformation("PID-6.1=" + queryRequest.getMotherNameMaiden());
	    testCaseMessage.appendCustomTransformation("PID-6.2=" + queryRequest.getMotherNameFirst());
	    testCaseMessage.appendCustomTransformation("PID-6.3=" + queryRequest.getMotherNameMiddle());
	    testCaseMessage.appendCustomTransformation("NK1-2.1=" + queryRequest.getMotherNameLast());
	    testCaseMessage.appendCustomTransformation("NK1-2.2=" + queryRequest.getMotherNameFirst());
	    testCaseMessage.appendCustomTransformation("NK1-2.3=" + queryRequest.getMotherNameMiddle());
	    if (queryRequest.getBirthDate() == null) {
	      testCaseMessage.appendCustomTransformation("PID-7=");
	    } else {
	      testCaseMessage
	          .appendCustomTransformation("PID-7=" + sdfDateOnly.format(queryRequest.getBirthDate()));
	    }
	    testCaseMessage.appendCustomTransformation("PID-8=" + queryRequest.getSex());
	    testCaseMessage.appendCustomTransformation("PID-11.1=" + queryRequest.getAddressStreet1());
	    testCaseMessage.appendCustomTransformation("PID-11.2=" + queryRequest.getAddressStreet2());
	    testCaseMessage.appendCustomTransformation("PID-11.3=" + queryRequest.getAddressCity());
	    testCaseMessage.appendCustomTransformation("PID-11.4=" + queryRequest.getAddressState());
	    testCaseMessage.appendCustomTransformation("PID-11.5=" + queryRequest.getAddressZip());
	    testCaseMessage.appendCustomTransformation("NK1-4.6=" + queryRequest.getAddressCountry());
	    testCaseMessage.appendCustomTransformation("NK1-4.1=" + queryRequest.getAddressStreet1());
	    testCaseMessage.appendCustomTransformation("NK1-4.2=" + queryRequest.getAddressStreet2());
	    testCaseMessage.appendCustomTransformation("NK1-4.3=" + queryRequest.getAddressCity());
	    testCaseMessage.appendCustomTransformation("NK1-4.4=" + queryRequest.getAddressState());
	    testCaseMessage.appendCustomTransformation("NK1-4.5=" + queryRequest.getAddressZip());
	    testCaseMessage.appendCustomTransformation("NK1-4.6=" + queryRequest.getAddressCountry());
	    testCaseMessage.appendCustomTransformation("PID-13.6=" + queryRequest.getPhoneArea());
	    testCaseMessage.appendCustomTransformation("PID-13.7=" + queryRequest.getPhoneLocal());
	    testCaseMessage.appendCustomTransformation("NK1-5.6=" + queryRequest.getPhoneArea());
	    testCaseMessage.appendCustomTransformation("NK1-5.7=" + queryRequest.getPhoneLocal());
	    testCaseMessage.appendCustomTransformation(
	        "PID-24=" + (queryRequest.getMultipleBirthIndicator() ? "Y" : "N"));
	    if (queryRequest.getMultipleBirthOrder() == 0) {
	      testCaseMessage.appendCustomTransformation("PID-25=");
	    } else {
	      testCaseMessage.appendCustomTransformation("PID-25=" + queryRequest.getMultipleBirthOrder());
	    }
	    Date now = new Date();
	    testCaseMessage.appendCustomTransformation("RXA-3=" + sdfDateOnly.format(now));
	    testCaseMessage.appendCustomTransformation("OBX#4-5=" + sdfDateOnly.format(now));
	    testCaseMessage.appendCustomTransformation("MSH-7=" + sdfDateAndTime.format(now));

	    Transformer transformer = new Transformer();
	    transformer.transform(testCaseMessage);
	    return testCaseMessage;
	  }
	
	public static String cleanMessage(String message) {
	    if (message != null) {
	      StringBuilder sb = new StringBuilder();
	      BufferedReader reader = new BufferedReader(new StringReader(message));
	      String line;
	      try {
	        while ((line = reader.readLine()) != null) {
	          sb.append(line);
	          sb.append("\r");
	        }
	      } catch (IOException ioe) {
	        sb.append(ioe.getMessage());
	      }
	      return sb.toString();
	    }

	    return message;

	 }
	

}
