package com.cruds.pos.controller;

import java.time.LocalDate;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
		mv.addObject("TAXLIST", taxser.getAllTax());
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
	
	@RequestMapping(value="/inactive-{id}", method=RequestMethod.GET)
	public String inactive(@PathVariable int id,HttpSession session)
	{
		session.setAttribute("id", id);
		return "redirect:taxes.html";
	}
	
	
	@RequestMapping(value="/setinactive")
	public String inactive1(@ModelAttribute("user") User user,HttpSession session)
	{
		int id = (int) session.getAttribute("id");
		LocalDate endDate = LocalDate.now();	
		taxser.setInactive(id, endDate);
		session.removeAttribute("id");
		return "redirect:taxes.html";
	}
	
	

}
