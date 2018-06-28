package com.cdcnbs.integrateiis.business.domain;
import org.immregistries.smm.tester.manager.query.QueryResponse;
import com.cdcnbs.integrateiis.data.entity.Patient;
public class VaccinationData {

	private String RequestHL7;	
	private String ResponseHL7;	
	private QueryResponse QueryResponse;	
	private Patient Patient;
	public String getRequestHL7() {
		return RequestHL7;
	}
	public void setRequestHL7(String requestHL7) {
		RequestHL7 = requestHL7;
	}
	public String getResponseHL7() {
		return ResponseHL7;
	}
	public void setResponseHL7(String responseHL7) {
		ResponseHL7 = responseHL7;
	}
	public QueryResponse getQueryResponse() {
		return QueryResponse;
	}
	public void setQueryResponse(QueryResponse queryResponse) {
		QueryResponse = queryResponse;
	}
	public Patient getPatient() {
		return Patient;
	}
	public void setPatient(Patient patient) {
		Patient = patient;
	}
	
	
	
}
