package by.bsu.var4.controllers;

import by.bsu.var4.dao.ResourceDAO;
import by.bsu.var4.exception.DAOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Asus on 06.10.2016.
 */
@Controller
public class BaseController {
    @Autowired
    protected ResourceDAO resourceDAO;

   
    public String manageRequests(HttpServletRequest req, HttpServletResponse resp, Model model) throws IOException, DAOException {
        model.addAttribute("resources", resourceDAO.getResources());
        return "index";
    }
    
    @InitBinder
    private void dateBinder(WebDataBinder binder) {
                //The date format to parse or output your dates
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                //Create a new CustomDateEditor
        CustomDateEditor editor = new CustomDateEditor(dateFormat, true);
                //Register it as custom editor for the Date type
        binder.registerCustomEditor(Date.class, editor);
    }
    
}
