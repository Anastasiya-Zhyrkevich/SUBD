package by.bsu.var4.dao.impl;

import by.bsu.var4.dao.ResourceDAO;
import by.bsu.var4.entity.Doctor;
import by.bsu.var4.entity.Patient;
import by.bsu.var4.entity.Purchase;
import by.bsu.var4.entity.Resource;
import by.bsu.var4.exception.DAOException;

import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Date;

public class ResourceDAOImpl implements ResourceDAO {

    private static final String SQL_CREATE_RESOURCE = "INSERT INTO RESOURCES(PROJECT_NAME, PROJECT_INFO) VALUES(?,?)";
    private static final String SQL_GOOD_PATIENTS =  	
    	    "select visit.id as id, VISIT_DATE, description, price, "
    	    + "patient.id as patient_id, patient.name as patient_name, "
    	    + "doctor.id as doctor_id, doctor.name as doctor_name from visit "
    	    + "join patient on patient.id = visit.patient_id "
    	    + "join doctor on doctor.id = patient.doctor_id WHERE VISIT.doctor_id is null ";
    private static final String SQL_BAD_PATIENTS =
    		"select visit.id as id, VISIT_DATE, description, price, "
    		+ "patient.id as patient_id, patient.name as patient_name, "
    		+ "visit.doctor_id as doctor_id, doctor.name as doctor_name from visit "
    	    + "join patient on patient.id = visit.patient_id "
    	    + "join doctor on doctor.id = visit.doctor_id WHERE VISIT.doctor_id is not null ";
    private static final String SQL_SELECT_FROM_VISITS_PATTERN = 
    		SQL_GOOD_PATIENTS + "union all " + SQL_BAD_PATIENTS;

    private static final String SQL_SELECT_RESOURCE_BY_ID = 
    		SQL_GOOD_PATIENTS + " and visit.ID=? "+ " union all " + SQL_BAD_PATIENTS + " and visit.ID=?;";
    
    private static final String SQL_TASK2 = 
    		"select * from patient where ID not in (select distinct patient_id from visit where doctor_id is not null);";
    
    
    private static final String SQL_TASK3 = 
    	"select EXTRACT(month from visit_date) as month, sum(price) as sum " + 
    		"from visit where EXTRACT(year from visit_date) = ? " + 
    		  "group by EXTRACT(month from visit_date) " + 
    		  " ORDER BY MONTH;";

    
    private static final String SQL_TASK1 = 
    		"select * from patient where ID in (select distinct "
    		+ "patient_id from visit where visit.VISIT_DATE=str_to_date(?, '%Y/%m/%d'));";
        	
    
    private static final String SQL_SELECT_FROM_VISITS =
    		SQL_SELECT_FROM_VISITS_PATTERN + ";";
    		
    
    private static final String SQL_UPDATE_RESOURCE = 
    		"Update Visit set VISIT_DATE=?, description=?, price=?, patient_id = ?, doctor_id=? WHERE ID=?";
    
    private static final String SQL_DOCTOR = 
    		"select id, name from doctor";
    private static final String SQL_PATIENT = 
    		"select id, name from patient";
    
    private static final String RESOURCE_ID = "RESOURCES_ID";
    private static final String ID = "ID";
    private static final String VISIT_DATE = "VISIT_DATE";
    private static final String DESCRIPTION = "DESCRIPTION";
    

    @Autowired
    private DataSource dataSource;

    @Override
    public void create(Resource resource) throws DAOException {
        /*
            PreparedStatement ps = con.prepareStatement(SQL_CREATE_RESOURCE);) {
            ps.setString(1, resource.getProjectName());
            ps.setString(2, resource.getProjectInfo());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Error while insert new resource.", e);
        }
        */
    }

    @Override
    public Resource retrieve(Integer resourceId) throws DAOException {
    	
        Resource resource = null;
        try(Connection con = dataSource.getConnection();
            PreparedStatement ps = con.prepareStatement(SQL_SELECT_RESOURCE_BY_ID);) {
            ps.setInt(1, resourceId);
            ps.setInt(2, resourceId);
            try(ResultSet rs = ps.executeQuery();){
                while (rs.next()){
                    Date visitDate = rs.getDate(VISIT_DATE);
                    
                    resource = new Resource();
                    resource.setVisitDate(new java.util.Date(visitDate.getTime()));
                    resource.setResourceId(resourceId);
                    
                    String description = rs.getString(DESCRIPTION);
                    int price = rs.getInt("PRICE");
                    
                    resource.setDescription(description);
                    resource.setPrice(price);
                    
                    int patientId = rs.getInt("patient_id");
                    int doctorId = rs.getInt("doctor_id");
                    resource.setPatientId(patientId);
                    resource.setDoctorId(doctorId);
                    
                    Map<Integer, String> doctors = getDoctors();
                    resource.setDoctors(doctors);
                    
                    Map<Integer, String> patients = getPatients();
                    resource.setPatients(patients);
                    
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Error while retrieve user from db.", e);
        }
        return resource;
    }

    @Override
    public List<Resource> retrieveAll() throws DAOException {
        List<Resource> resources = new ArrayList<>();
        Resource resource = null;
        /*
        try(Connection con = dataSource.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(SQL_SELECT_ALL_RESOURCES);) {
            while (rs.next()){
                int resourceId = rs.getInt(RESOURCE_ID);
                String projectName = rs.getString(PROJECT_NAME);
                String projectInfo = rs.getString(PROJECT_INFO);
                resource = new Resource(resourceId, projectName, projectInfo);
                resources.add(resource);
            }
        } catch (SQLException e) {
            throw new DAOException("Error while retrive list of users from db.", e);
        }
		*/
        return resources;
    }

    @Override
    public void update(Resource resource) throws DAOException {
        try(Connection con = dataSource.getConnection();
            PreparedStatement ps = con.prepareStatement(SQL_UPDATE_RESOURCE);) {
            ps.setDate(1, new java.sql.Date(resource.getVisitDate().getTime()));
            ps.setString(2, resource.getDescription());
            ps.setInt(3, resource.getPrice());
            ps.setInt(4, resource.getPatientId());
            ps.setInt(5, resource.getDoctorId());
            ps.setInt(6, resource.getResourceId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Error while update user.", e);
        }
        
    }

    @Override
    public void delete(Integer resourceId) throws DAOException {
    	/*
        try(Connection con = dataSource.getConnection();
            PreparedStatement ps = con.prepareStatement(SQL_DELETE_RESOURCE0);
            PreparedStatement ps1 = con.prepareStatement(SQL_DELETE_RESOURCE)) {
            ps.setInt(1, resourceId);
            ps.executeUpdate();
            ps1.setInt(1, resourceId);
            ps1.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Error while delete user.", e);
        }
        */
    }

    @Override
    public List<Resource> getResources() throws DAOException {
        List<Resource> resources = new ArrayList<>();
        Resource resource = null;
        
        
        try(Connection con = dataSource.getConnection();
        	PreparedStatement ps = con.prepareStatement(SQL_SELECT_FROM_VISITS);) {
            try(ResultSet rs = ps.executeQuery();){
                while (rs.next()){
                    int resourceId = rs.getInt(ID);
                    resource = new Resource();
                    resource.setResourceId(resourceId);
                    
                    Date visitDate = rs.getDate(VISIT_DATE);
                    
                    String description = rs.getString(DESCRIPTION);
              
                    int price = rs.getInt("price");
                    String patientName = rs.getString("patient_name");
                    String doctorName = rs.getString("doctor_name");
                    
                    
                    resource.setVisitDate(new java.util.Date(visitDate.getTime()));
                    
                    resource.setDescription(description);
                    resource.setPrice(price);
                    resource.setPatientName(patientName);
                    resource.setDoctorName(doctorName);
                    
                    resources.add(resource);
                    
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Error while getResources from db.", e);
        }
        return resources;
    }
    
    @Override
    public List<Patient> getReq1(String date) throws DAOException {
    	try(Connection con = dataSource.getConnection();
                PreparedStatement ps = con.prepareStatement(SQL_TASK1);) {
                ps.setString(1, date);
                return getPatientsForRequest(ps);
            } catch (SQLException e) {
                throw new DAOException("Error while getReq1", e);
            }
    }    
    
    @Override
    public List<Patient> getReq2() throws DAOException {
    	try(Connection con = dataSource.getConnection();
             	PreparedStatement ps = con.prepareStatement(SQL_TASK2);) {
    				return getPatientsForRequest(ps);
             	} catch (SQLException e) {
                    throw new DAOException("Error while" + SQL_TASK2 + " from db.", e);
                }
    }
    
    public List<Purchase> getReq3(int year) throws DAOException {
    	List<Purchase> resources = new ArrayList<>();

    	try(Connection con = dataSource.getConnection();
    			PreparedStatement ps = con.prepareStatement(SQL_TASK3);) {
    				ps.setInt(1, year);
		    		try(ResultSet rs = ps.executeQuery();){
		                while (rs.next()){ 
		               	 Purchase purchase = new Purchase();
		               	 purchase.setMonth(rs.getInt("month"));
		               	 purchase.setSum(rs.getInt("sum"));
		               	 resources.add(purchase);
		                }
		    		}
             	} catch (SQLException e) {
                    throw new DAOException("Error while" + SQL_TASK3 + " from db.", e);
                }
		 return resources;
    }
    
    public List<Patient> getPatientsForRequest(PreparedStatement ps) throws DAOException {
    	 List<Patient> resources = new ArrayList<>();
    	 Patient patient = null;
         
         try(ResultSet rs = ps.executeQuery();){
             while (rs.next()){ 
            	 patient = new Patient();
            	 patient.setPatientId(rs.getInt("id"));
            	 patient.setPatientName(rs.getString("name"));
            	 patient.setAddress(rs.getString("address"));
            	 patient.setBirthdate(rs.getDate("birth_date"));
                 resources.add(patient);
             }
         } catch (SQLException e) {
			e.printStackTrace();
		}
         
         return resources; 
    }
    
    
    @Override
    public Map<Integer, String> getDoctors() throws DAOException {
        Map<Integer, String> resources = new HashMap<>();
        Doctor resource = null;
        
        try(Connection con = dataSource.getConnection();
        	PreparedStatement ps = con.prepareStatement(SQL_DOCTOR);) {
            try(ResultSet rs = ps.executeQuery();){
                while (rs.next()){
                    int resourceId = rs.getInt(ID);
                    //resource = new Doctor();
                    //resource.setDoctorId(resourceId);
                    
                    String name = rs.getString("name");
                    //resource.setDoctorName(name);
                    resources.put(new Integer(resourceId), name);
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Error while getdoctors from db.", e);
        }
        return resources;
    }
    
    @Override
    public Map<Integer, String> getPatients() throws DAOException {
    	Map<Integer, String> resources = new HashMap<>();
        //Patient resource = null;
        
        try(Connection con = dataSource.getConnection();
        	PreparedStatement ps = con.prepareStatement(SQL_PATIENT);) {
            try(ResultSet rs = ps.executeQuery();){
                while (rs.next()){
                    int resourceId = rs.getInt(ID);
                    String name = rs.getString("name");
                    resources.put(resourceId, name);
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Error while getpatients from db.", e);
        }
        return resources;
    }
    
}
