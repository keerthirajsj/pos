package com.cruds.pos.controller;

import java.time.LocalDate;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cruds.pos.entity.Tax;
import com.cruds.pos.entity.User;
import com.cruds.pos.service.TaxService;
import com.cruds.pos.service.UserService;



@Controller
public class HomeController {
	@Autowired
	UserService ser;
	
	@Autowired
	TaxService taxser;
	
	@RequestMapping(value="/users", method=RequestMethod.GET)
	public ModelAndView showStudentForm()
	{
		ModelAndView mv = new ModelAndView("users", "user", new User());
		mv.addObject("USERLIST", ser.getAllUser());
		return mv;		
	}
	
	@RequestMapping(value="/createuser", method = RequestMethod.POST)
	public String createUserPage(@ModelAttribute("user") User user)
	{
		ser.create(user);
		return "redirect:home.html";
	}
	
	@RequestMapping(value="/taxes", method=RequestMethod.GET)
	public ModelAndView addTaxesget()
	{
		ModelAndView mv = new ModelAndView("taxes", "taxes", new Tax());
		mv.addObject("TAXLIST", taxser.getAllActiveTax());
		return mv;		
	}
	
	@RequestMapping(value="/taxes", method=RequestMethod.POST)
	public ModelAndView addTaxespost(@RequestParam("form_check") String check)
	{
		ModelAndView mv = new ModelAndView("taxes", "taxes", new Tax());
	    if(check.equals("checked"))
		{
			mv.addObject("TAXLIST", taxser.getAllTax1());	
		}
		return mv;		
	}
	
	@RequestMapping(value="/addTax", method = RequestMethod.POST)
	public String addTaxespost(@RequestParam("taxname") String taxname,@RequestParam("taxrate") Double taxrate )
	{
		LocalDate startDate = LocalDate.now();	
		Tax tax=new Tax(taxname,taxrate,startDate);
		taxser.addTax(tax);
		return "redirect:home.html";
	}
	
	@RequestMapping(value="/inactive", method=RequestMethod.POST)
	public String inactive(@RequestParam("form_id") int id)
	{
		System.out.println(id);
		LocalDate endDate = LocalDate.now();	
		taxser.setInactive(id, endDate);
		return "redirect:taxes.html";
	}
	
	
	
	
	

}
