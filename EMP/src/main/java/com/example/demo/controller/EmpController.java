package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.example.demo.model.EmpEntity;
import com.example.demo.model.User;
import com.example.demo.service.EmpService;

@Controller
public class EmpController {

	 @Autowired
	  private EmpService empService;
	
	 // display list of employees
		/*
		 * @GetMapping("/") public String viewHomePage(Model model) {
		 * model.addAttribute("listEmployees", empService.getAllEmployees()); return
		 * "index"; }
		 */
	 
	 @GetMapping("/")
	    public String viewHomePage() {
	        return "index2";
	    }
	 
	 @GetMapping("/viewData")
	    public String viewData(Model model) {
		 model.addAttribute("listEmployees", empService.getAllEmployees());
	        return "index";
	    }
	 
	 
	    
	    @GetMapping("/seeList")
	    public String viewListPage(Model model) {
	        model.addAttribute("listEmployees", empService.getAllEmployees());
	        return "List";
	    }
	
	 
	 @GetMapping("/showNewEmployeeForm")
	    public String showNewEmployeeForm(Model model) {
	               // create model attribute to bind form dat
	    	EmpEntity empEntity=new EmpEntity();
	        model.addAttribute("employee", empEntity);
	        return "new_employee";
	    }
	 

	    @PostMapping("/saveEmployee")
	    public String saveEmployee(@ModelAttribute("empEntity") EmpEntity empEntity) {
	        // save employee to database
	    	empService.saveEmployee(empEntity);
	        return "redirect:/";
	    }
	    
	    @GetMapping("/showFormForUpdate/{id}")
	    public String showFormForUpdate(@PathVariable ( value = "id") long id, Model model) {
	     
	     // get employee from the service
	     EmpEntity empEntity = empService.getEmployeeById(id);
	     
	     // set employee as a model attribute to pre-populate the form
	     model.addAttribute("employee", empEntity);
	     return "update_employee";
	    }
	    
	    @GetMapping("/deleteEmployee/{id}")
	    public String deleteEmployee(@PathVariable (value="id") long id) {
	     
	     // call delete employee method 
	     this.empService.deleteEmployeeById(id);
	     return "redirect:/";
	    }
	    
		/*
		 * @GetMapping("/page/{pageNo}") public String findPaginated(@PathVariable(value
		 * = "pageNo") int pageNo, Model model) { int pageSize = 5;
		 * 
		 * Page < EmpEntity > page = empService.findPaginated(pageNo, pageSize); List <
		 * EmpEntity > listEmployees = page.getContent();
		 * 
		 * model.addAttribute("currentPage", pageNo); model.addAttribute("totalPages",
		 * page.getTotalPages()); model.addAttribute("totalItems",
		 * page.getTotalElements()); model.addAttribute("listEmployees", listEmployees);
		 * return "index"; }
		 */
	    

	    
	    
	    
	    
}
