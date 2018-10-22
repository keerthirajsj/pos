package com.cruds.pos.db;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cruds.pos.entity.L1Menu;
import com.cruds.pos.entity.MenuMaster;
import com.cruds.pos.entity.Tax;

@Repository
@Transactional
public class L1MenuDaoHbrImpl implements L1MenuDao {
	
	@Autowired
	SessionFactory sessionFactory;

	@Override
	public boolean createL1menu(String name,Long mmId,Long taxId) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		MenuMaster mm = session.load(MenuMaster.class, mmId);
		Tax dbTax = session.load(Tax.class, taxId);
		L1Menu l1Menu = new L1Menu(name, mm, dbTax);
		session.saveOrUpdate(l1Menu);
		
		tx.commit();
		session.close();
		System.out.println("Hibernate DAO Create user Method");
		return true;
	}

	
	
	

}
