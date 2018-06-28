package com.cdcnbs.integrateiis.data.entity;

import java.util.HashMap;

import com.sun.javafx.collections.MappingChange.Map;

public class Enums {
	
	public enum PatientIdentifierType{
		MR(1),SSN(2),OTH(3);
		
		private int numVal;

	    private static HashMap<Integer, PatientIdentifierType> map = new HashMap<Integer, PatientIdentifierType>();

	    static {
	        for (PatientIdentifierType patientIdentifierTypeEnum : PatientIdentifierType.values()) {
	            map.put(patientIdentifierTypeEnum.numVal, patientIdentifierTypeEnum);
	        }
	    }

	    private PatientIdentifierType(final int numVal) { 
	    	this.numVal = numVal; 
	    }

	    public static PatientIdentifierType valueOf(int numVal) {
	        return map.get(numVal);
	    }		
		
	}
	
	public enum Gender{
		M(1),F(2),U(3);
		
		private int numVal;

	    private static HashMap<Integer, Gender> map = new HashMap<Integer, Gender>();

	    static {
	        for (Gender genderEnum : Gender.values()) {
	            map.put(genderEnum.numVal, genderEnum);
	        }
	    }

	    private Gender(final int numVal) { 
	    	this.numVal = numVal; 
	    }

	    public static Gender valueOf(int numVal) {
	        return map.get(numVal);
	    }		
		
	}
	
	
}
