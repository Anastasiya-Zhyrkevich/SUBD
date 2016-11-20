package by.bsu.var4.entity;

import java.util.Date;
import java.util.Map;

public class Patient {
	private int patientId;
	private String patientName;
	private String address;
	private Date birthdate;
	
	private String toothFormula;
	private String paymentType;
	private int doctorId;
	private String doctorName;
	
	private Map<Integer, String> doctors;
	
		
	public Map<Integer, String> getDoctors() {
		return doctors;
	}
	public void setDoctors(Map<Integer, String> doctors) {
		this.doctors = doctors;
	}
	
	public String getToothFormula() {
		return toothFormula;
	}
	public void setToothFormula(String toothFormula) {
		this.toothFormula = toothFormula;
	}
	public String getPaymentType() {
		return paymentType;
	}
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}
	public int getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(int doctorId) {
		this.doctorId = doctorId;
	}
	public String getDoctorName() {
		return doctorName;
	}
	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Date getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}
	public int getPatientId() {
		return patientId;
	}
	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}
	public String getPatientName() {
		return patientName;
	}
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}
}
