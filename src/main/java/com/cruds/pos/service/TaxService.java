package com.cruds.pos.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cruds.pos.db.TaxDao;
import com.cruds.pos.entity.Tax;
@Service
public class TaxService {
	
	@Autowired
	TaxDao dao;
	
	public boolean addTax(Tax tax)
	{
		return dao.addTax(tax);
	}
	
	public List<Tax> getAllTax()
	{
		
		return dao.getAllTax();
	}
	
	public boolean setInactive(int id,LocalDate endDate)
	{
		return dao.setInactive(id, endDate);
	}

}
