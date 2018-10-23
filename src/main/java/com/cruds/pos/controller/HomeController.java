package com.cruds.pos.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cruds.pos.entity.L1Menu;
import com.cruds.pos.entity.MenuMaster;
import com.cruds.pos.entity.Tax;
import com.cruds.pos.entity.User;
import com.cruds.pos.formbean.L1FormBean;
import com.cruds.pos.formbean.L2FormBean;
import com.cruds.pos.service.L1MenuService;
import com.cruds.pos.service.MenuMasterService;
import com.cruds.pos.service.TaxService;
import com.cruds.pos.service.UserService;



@Controller
public class HomeController {
	@Autowired
	UserService userService;
	
	@Autowired
	TaxService taxService;
	
	@Autowired
	MenuMasterService menuMasterService;
	
	@Autowired
	L1MenuService l1menuservice;

	
	@RequestMapping(value="/users", method=RequestMethod.GET)
	public ModelAndView showStudentForm()
	{
		ModelAndView mv = new ModelAndView("users", "user", new User());
		mv.addObject("USERLIST", userService.getAllUser());
		return mv;		
	}
	
	@RequestMapping(value="/createuser", method = RequestMethod.POST)
	public String createUserPage(@ModelAttribute("user") User user)
	{
		userService.create(user);
		return "redirect:home.html";
	}
	
	@RequestMapping(value="/taxes", method=RequestMethod.GET)
	public ModelAndView addTaxesget()
	{
		ModelAndView mv = new ModelAndView("taxes", "taxes", new Tax());
		mv.addObject("TAXLIST", taxService.getAllActiveTax());
		return mv;		
	}
	
	@RequestMapping(value="/taxes", method=RequestMethod.POST)
	public ModelAndView addTaxespost(@RequestParam("form_check") String check)
	{
		ModelAndView mv = new ModelAndView("taxes", "taxes", new Tax());
		System.out.println(check);
	    if(check.equals("checked"))
		{
			mv.addObject("TAXLIST", taxService.getAllTax1());	
		}
	    else if(check.equals("unchecked"))
		{
			mv.addObject("TAXLIST", taxService.getAllActiveTax());	
		}
		return mv;		
	}
	
	@RequestMapping(value="/addTax", method = RequestMethod.POST)
	public String addTaxespost(@RequestParam("taxname") String taxname,@RequestParam("taxrate") Double taxrate )
	{
		LocalDate startDate = LocalDate.now();	
		Tax tax=new Tax(taxname,taxrate,startDate);
		taxService.addTax(tax);
		return "redirect:home.html";
	}
	
	@RequestMapping(value="/inactive", method=RequestMethod.POST)
	public String inactive(@RequestParam("form_id") int id)
	{
		System.out.println(id);
		LocalDate endDate = LocalDate.now();	
		taxService.setInactive(id, endDate);
		return "redirect:taxes.html";
	}
	
	@RequestMapping(value = "/menu", method = RequestMethod.GET) 
	public String menuget() {  
	    return "menu"; 
	}
	
	@RequestMapping(value="/menu", method=RequestMethod.POST)
	public String menupost(@RequestParam("Add Menu Master") String menumaster)
	{
		if(menumaster.equals("Add Menu Master"))
		{
		return "menumaster";
		}
		else
		{
		return "menu";
		}
	}
	
	@RequestMapping(value="/menumaster", method=RequestMethod.GET)
	public ModelAndView menumasterget()
	{
		ModelAndView mv = new ModelAndView("menumaster", "menumaster", new MenuMaster());
		mv.addObject("MENUMASTERLIST",menuMasterService.getAllMenu());
		return mv;		
	}
	
	@RequestMapping(value="/menumaster", method=RequestMethod.POST)
	public String menumasterpost(@RequestParam("menumaster") String menumaster)
	{
	
		MenuMaster menu = new MenuMaster(menumaster);
		menuMasterService.createmenumaster(menu);
		return "redirect:menumaster.html";
		
	}
	@RequestMapping(value="/l1menu", method=RequestMethod.GET)
	public ModelAndView l1menuget()
	{	
			ModelAndView mv = new ModelAndView("l1menu", "l1FormBean", new L1FormBean());
		    Map<Long, String> mmMap = menuMasterService.getAllMenu().stream().collect(Collectors.toMap(MenuMaster :: getId, MenuMaster :: getName));
			
			mv.addObject("MENUMASTERMAP",mmMap);
			
		    Map<Long, String> taxMap = taxService.getAllTaxList().stream().collect(Collectors.toMap(Tax :: getId, Tax :: getName));

			mv.addObject("TAXMAP",taxMap);
			
			mv.addObject("L1MENULIST", l1menuservice.getAllL1menulist());
			return mv;
		
	}
	@RequestMapping(value="/l1menu", method=RequestMethod.POST)
	public String l1menupost(@ModelAttribute("l1FormBean") L1FormBean l1FormBean)
	{
		//System.out.println(l1FormBean.getMmId());
		//System.out.println(l1FormBean.getTaxId());
		//System.out.println(l1FormBean.getL1MenuName());
		//MenuMaster menu=new MenuMaster(menumaster);
		l1menuservice.createL1menu(l1FormBean.getL1MenuName(),l1FormBean.getMmId(),l1FormBean.getTaxId());
		return "redirect:l1menu.html";
		
		
	}
	
	@RequestMapping(value="/l2menu", method=RequestMethod.GET)
	public ModelAndView l2menuget()
	{	
			ModelAndView mv = new ModelAndView("l2menu", "l2FormBean", new L2FormBean());
		    Map<Long, String> mmMap = menuMasterService.getAllMenu().stream().collect(Collectors.toMap(MenuMaster :: getId, MenuMaster :: getName));
			
			mv.addObject("MENUMASTERMAP",mmMap);
			
		    Map<Long, String> taxMap = taxService.getAllTaxList().stream().collect(Collectors.toMap(Tax :: getId, Tax :: getName));

			mv.addObject("TAXMAP",taxMap);
			
			
			return mv;
		
	}
	
	@RequestMapping(value="/l2menupost", method=RequestMethod.POST)
	public ModelAndView l2menuhandler(@RequestParam("mmId") Long mmid)
	{
		ModelAndView mv = new ModelAndView("l2menu", "l2FormBean", new L2FormBean());
		Map<Long, String> l1menumap = l1menuservice.getAllL1menuList(mmid).stream().collect(Collectors.toMap(L1Menu :: getId, L1Menu :: getName));
		System.out.println(mmid);
		mv.addObject("L1MENULIST",l1menumap );
		Map<Long, String> mmMap = menuMasterService.getAllMenu().stream().collect(Collectors.toMap(MenuMaster :: getId, MenuMaster :: getName));
		
		mv.addObject("MENUMASTERMAP",mmMap);
		
	    Map<Long, String> taxMap = taxService.getAllTaxList().stream().collect(Collectors.toMap(Tax :: getId, Tax :: getName));

		mv.addObject("TAXMAP",taxMap);
		return mv;
	}
	
	@RequestMapping(value="/l2menu", method=RequestMethod.POST)
	public String l2menupost(@ModelAttribute("l2FormBean") L2FormBean l2FormBean)
	{
	
		if(l2FormBean.getL2MenuName()!=null)
		{
		l1menuservice.createl2menu(l2FormBean.getL2MenuName(),l2FormBean.getL1mmId(),l2FormBean.getPrice(),l2FormBean.getTaxId());
		}
		return "redirect:l2menu.html";
		
		
	}
	
}
