package by.bsu.var4.entity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;


public class Resource {
    private int resourceId;
    private Date visitDate;
    private String description;
    private String tooth_formula;
    
    private int price;
    private String patientName;
    private String doctorName;
    
    private int doctorId;
    private int patientId;
    
    
    
    public String getTooth_formula() {
		return tooth_formula;
	}

	public void setTooth_formula(String tooth_formula) {
		this.tooth_formula = tooth_formula;
	}

	public int getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(int doctor) {
		this.doctorId = doctor;
	}
	

	public int getPatientId() {
		return patientId;
	}

	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}


	private Map<Integer, String> doctors;
    private Map<Integer, String> patients;
    

	public Map<Integer, String> getDoctors() {
		return doctors;
	}

	public void setDoctors(Map<Integer, String> doctors) {
		this.doctors = doctors;
	}


	public Map<Integer, String> getPatients() {
		return patients;
	}

	public void setPatients(Map<Integer, String> patients) {
		this.patients = patients;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public String getDoctorName() {
		return doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	

    public Resource() {
        super();
    }

    public Date getVisitDate() {
		return visitDate;
	}

    public String getStringVisitDate() {
    	DateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    	String reportDate = "";
    	if (this.getVisitDate()!= null)
    		reportDate = df.format(this.getVisitDate());
		return reportDate;
	}
    
	public void setVisitDate(Date visitDate) {
		this.visitDate = visitDate;
	}


	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}



	public int getPrice() {
		return price;
	}



	public void setPrice(int price) {
		this.price = price;
	}



	public Resource(int resourceId) {
        this.resourceId = resourceId;
    }

    public int getResourceId() {
        return resourceId;
    }

    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Resource resource = (Resource) o;

        if (resourceId != resource.resourceId) return false;
        return true;

    }

    @Override
    public int hashCode() {
        int result = resourceId;
        //result = 31 * result + (projectName != null ? projectName.hashCode() : 0) + (projectInfo != null ? projectInfo.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Resource{" + resourceId;           
    }

   
}
