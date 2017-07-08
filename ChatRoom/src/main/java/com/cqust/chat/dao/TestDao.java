package com.cqust.chat.dao;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import com.cqust.chat.entity.Student;

public class TestDao extends HibernateDaoSupport{
	
	@Transactional
	public void insert(Student s){
		this.getHibernateTemplate().saveOrUpdate(s);
		
	}
	
	
}
