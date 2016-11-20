package by.bsu.var4.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;

import by.bsu.var4.dao.PatientDAO;
import by.bsu.var4.entity.Patient;
import by.bsu.var4.entity.Resource;
import by.bsu.var4.exception.DAOException;


public class PatientDAOImpl implements PatientDAO {
	@Autowired
	private DataSource dataSource;
	
	private static final String SQL_DELETE = 
	    		"Delete from patient where id = ?;";
	
	private static String SQL_SELECT = "select id, name, address, birth_date, "
			+ "tooth_formula, payment_type, doctor_id from patient";

	@Override
	public void create(Patient entity) throws DAOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Patient retrieve(Integer key) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Patient> retrieveAll() throws DAOException {
		
		List<Patient> resources = new ArrayList<>();
        
        Patient resource = null; 
        try(Connection con = dataSource.getConnection();
        	PreparedStatement ps = con.prepareStatement(SQL_SELECT);) {
            try(ResultSet rs = ps.executeQuery();){
                while (rs.next()){
                    int resourceId = rs.getInt("id");
                    resource = new Patient();
                    resource.setPatientId(resourceId);
                    String name = rs.getString("name");
                    Date birthDate = rs.getDate("birth_date");
                    String toothFormula = rs.getString("tooth_formula");
                    String paymentType = rs.getString("payment_type");
                    int doctorId = rs.getInt("doctor_id");
                   
                    resource.setPatientName(name);
                    resource.setBirthdate(new java.util.Date(birthDate.getTime()));
                    resource.setToothFormula(toothFormula);
                    resource.setPaymentType(paymentType);
                    resource.setDoctorId(doctorId);
                    resources.add(resource);
                    
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Error while getPatients from db.", e);
        }
        
        return resources;
	}

	@Override
	public void update(Patient entity) throws DAOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Integer id) throws DAOException {
		try(Connection con = dataSource.getConnection();
                PreparedStatement ps = con.prepareStatement(SQL_DELETE);) {
                ps.setInt(1, (int)id);
                ps.executeUpdate();
            } catch (SQLException e) {
                throw new DAOException("Error while delete user.", e);
            }
		
	}

	
}
