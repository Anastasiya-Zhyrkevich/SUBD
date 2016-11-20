package by.bsu.var4.dao;

import by.bsu.var4.entity.Doctor;
import by.bsu.var4.entity.Patient;
import by.bsu.var4.entity.Purchase;
import by.bsu.var4.entity.Resource;
import by.bsu.var4.entity.Triggers;
import by.bsu.var4.exception.DAOException;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface ResourceDAO extends BaseDAO<Integer, Resource> {
    List<Resource> getResources() throws DAOException;
    Map<Integer, String> getDoctors() throws DAOException;
    Map<Integer, String> getPatients() throws DAOException;
    List<Patient> getReq1(String date) throws DAOException;
    List<Patient> getReq2() throws DAOException;
    List<Purchase> getReq3(int year) throws DAOException;
    public Triggers getTriggerDescriptions();
    public void setTriggerStates(Triggers triggers);
    public int getNextId(String tableName) throws DAOException;
    public void insertResource(Resource resource) throws DAOException;
}
