package com.cqust.chat.dao.imp;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.cqust.chat.dao.MessageDao;
import com.cqust.chat.entity.MyMessage;

public class MessageDaoImpl extends HibernateDaoSupport implements MessageDao{

	public boolean insertMessage(MyMessage message) {
		try {
			this.getHibernateTemplate().save(message);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("添加失败");
			return false;
		}
	}
	
}
