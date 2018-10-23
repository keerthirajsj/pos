package com.cruds.pos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cruds.pos.db.EstablishmentDAO;
import com.cruds.pos.entity.MenuMaster;

@Service
public class EstablishmentService
{
	@Autowired
	EstablishmentDAO edao;
	public boolean createEstablishment(String name,Long mmId)
	{
		return edao.createEstablishment(name, mmId);
	}

}
