package by.bsu.var4.controllers;

import by.bsu.var4.entity.Patient;
import by.bsu.var4.entity.Purchase;
import by.bsu.var4.entity.Question;
import by.bsu.var4.entity.Resource;
import by.bsu.var4.exception.DAOException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.servlet.ModelAndView;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;









//import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
//import java.sql.SQLException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Asus on 04.10.2016.
 */
@Controller
public class HomeController extends BaseController{
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getHome(HttpServletRequest req, HttpServletResponse resp, Model model) throws IOException, DAOException {
        return manageRequests(req,resp,model);
    }
    
    @RequestMapping(value = "/editResource", method = RequestMethod.GET)
    public ModelAndView editResource(@RequestParam("id") Integer id, HttpServletRequest req, Model model) throws DAOException {
        Resource resource = resourceDAO.retrieve(id);
        return new ModelAndView("addResource", "resource", resource);
    }

    @RequestMapping(value = "/editResource", method = RequestMethod.POST)
    public String editResourceToDb(@ModelAttribute("resource") Resource resource,
                               HttpServletRequest req, HttpServletResponse resp, Model model) throws IOException, SQLException, DAOException {
        resourceDAO.update(resource);
        return manageRequests(req, resp, model);
    }
    
    @RequestMapping(value = "/Req", method = RequestMethod.GET)
    public String reqGet(@ModelAttribute("question") Question question, HttpServletRequest req, Model model) throws DAOException {
        return "request";
    }

    @RequestMapping(value = "/Req", method = RequestMethod.POST)
    public String reqPost(@ModelAttribute("question") Question question,
                               HttpServletRequest req, HttpServletResponse resp, Model model) throws IOException, SQLException, DAOException {
	
		//DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
    	//Date date = formatter.parse(question.getDate());
		List<Patient> task1 = resourceDAO.getReq1(question.getDate());
		model.addAttribute("answer1", task1);
	
    	
    	List<Patient> task2 = resourceDAO.getReq2();
        model.addAttribute("answer2", task2);
        
        String s = question.getDate2();
        List<Purchase> task3 = resourceDAO.getReq3(new Integer(s.substring(0, 4)));
        
        model.addAttribute("answer3", task3);
        
        model.addAttribute("question", question);
        return "request";
    }
    
    
    
}
