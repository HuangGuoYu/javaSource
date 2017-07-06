package com.cqust.chat.dao.imp;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.cqust.chat.dao.StudentDao;
import com.cqust.chat.entity.Student;

public class StudentDaoImpl extends HibernateDaoSupport implements StudentDao{

	public Student findByIdAndPwd(Student s) {
		String hql = "from Student where id=? and password=?";
		List<Student> lists = this.getHibernateTemplate().find(hql, s.getId(),s.getPassword());
		if(lists.size() > 0)
			return lists.get(0);
		return null;
	}

	public List<Student> findAll() {
		String hql = "from Student";
		List<Student> lists = this.getHibernateTemplate().find(hql);
		if(lists != null)
			return lists;
		return null;
	}
	/**
	 * 注册学生
	 */
	public Long addStu(Student s) {
		this.getHibernateTemplate().save(s);
		return s.getId();
	}

}
