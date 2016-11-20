package by.bsu.var4.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import by.bsu.var4.entity.Patient;
import by.bsu.var4.entity.Resource;
import by.bsu.var4.exception.DAOException;

@Controller
public class PatientController extends BaseController{
	
	@RequestMapping(value = "/Patient", method = RequestMethod.GET)
    public String patientTable(HttpServletRequest req, HttpServletResponse resp, Model model) throws IOException, SQLException, DAOException {
        System.out.println("Patient");
		model.addAttribute("resources", patientDAO.retrieveAll());
        return "patients";
    }

	
	@RequestMapping(value = "/deletePatient", method = RequestMethod.GET)
    public String editResourceToDb(@RequestParam("id") Integer id,
                               HttpServletRequest req, HttpServletResponse resp, Model model) throws IOException, SQLException, DAOException {
        resourceDAO.delete(id);
        model.addAttribute("resources", patientDAO.retrieveAll());
        return "patients";
    }
    /*
    @RequestMapping(value = "/editPatient", method = RequestMethod.GET)
    public ModelAndView editResource(@RequestParam("id") Integer id, HttpServletRequest req, Model model) throws DAOException {
        if (id == 0){
        	Map<Integer, String> doctors = resourceDAO.getDoctors();
        	Patient resource = new Patient(0, doctors);        	
            return new ModelAndView("addResource", "resource", resource);
        }
    	Resource resource = resourceDAO.retrieve(id);
        return new ModelAndView("addResource", "resource", resource);
    }
    
    @RequestMapping(value = "/editPatient", method = RequestMethod.POST)
    public String editResourceToDb(@ModelAttribute("resource") Resource resource,
                               HttpServletRequest req, HttpServletResponse resp, Model model) throws IOException, SQLException, DAOException {
        if (resource.getResourceId()== 0){
        	resource.setResourceId(resourceDAO.getNextId("visit"));
        	try{
        		resourceDAO.insertResource(resource);
            } catch (DAOException e) {
                System.out.println("Error while update user. " + e.toString());
                model.addAttribute("error", e.toString());
                model.addAttribute("type", "Error");
                if (e.toString().equals("by.bsu.var4.exception.DAOException: Error while update user. Other doctor"))
                	model.addAttribute("type", "Warning");
                return "error";
            }
        }
        else
        {
	    	try{
	        	resourceDAO.update(resource);
	        } catch (DAOException e) {
	            System.out.println("Error while update user. " + e.toString());
	            model.addAttribute("error", e.toString());
	            model.addAttribute("type", "Error");
	            if (e.toString().equals("by.bsu.var4.exception.DAOException: Error while update user. Other doctor"))
	            	model.addAttribute("type", "Warning");
	            return "error";
	        }
        }
        return manageRequests(req, resp, model);
    }
	*/
}
