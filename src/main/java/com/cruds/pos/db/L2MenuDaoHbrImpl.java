package com.cruds.pos.db;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cruds.pos.entity.L1Menu;
import com.cruds.pos.entity.L2Menu;
import com.cruds.pos.entity.MenuMaster;
import com.cruds.pos.entity.Tax;

@Repository
@Transactional
public class L2MenuDaoHbrImpl implements L2MenuDao{
	
	@Autowired
	SessionFactory sessionFactory;

	@Override
	public boolean createL2menu(String name,Long l1mmId,Double price,Long taxId) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		L1Menu mm = session.load(L1Menu.class, l1mmId);
		Tax dbTax = session.load(Tax.class, taxId);
		L2Menu l2Menu = new L2Menu(name, mm,price, dbTax);
		session.saveOrUpdate(l2Menu);
		
		tx.commit();
		session.close();
		System.out.println("Hibernate DAO Create user Method");
		return true;
	}

}
