package com.cruds.pos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cruds.pos.db.L1MenuDao;
import com.cruds.pos.entity.L1Menu;
import com.cruds.pos.entity.MenuMaster;


@Service
public class L1MenuService {
	@Autowired
	L1MenuDao dao;
	
	public boolean createL1menu(String name,Long mmId,Long taxId)
	{
		return dao.createL1menu(name, mmId, taxId);
	}
}
