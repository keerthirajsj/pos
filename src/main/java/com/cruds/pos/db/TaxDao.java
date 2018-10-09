package com.cruds.pos.db;

import java.time.LocalDate;
import java.util.List;

import com.cruds.pos.entity.Tax;

public interface TaxDao {

	public boolean addTax(Tax tax);
	public List<Tax> getAllTax();
	public boolean setInactive(int id,LocalDate endDate);
}
