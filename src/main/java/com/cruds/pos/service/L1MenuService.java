package com.cruds.pos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cruds.pos.db.L1MenuDao;
import com.cruds.pos.db.L2MenuDao;
import com.cruds.pos.entity.L1Menu;
import com.cruds.pos.entity.MenuMaster;
import com.cruds.pos.entity.Tax;


@Service
public class L1MenuService {
	@Autowired
	L1MenuDao dao;
	
	@Autowired
	L2MenuDao dao1;
	
	public boolean createL1menu(String name,Long mmId,Long taxId)
	{
		return dao.createL1menu(name, mmId, taxId);
	}
	
	public List<L1Menu> getAllL1menulist()
	{
		
		return dao.getAllL1menu();
	}
	
	public List<L1Menu> getAllL1menuList(Long mmId)
	{
		return dao.getAllL1menuList(mmId);
	}
	
	public boolean createl2menu(String name,Long l1mmId,Double price,Long taxId)
	{
		return dao1.createL2menu(name, l1mmId, price, taxId);
	}
}
