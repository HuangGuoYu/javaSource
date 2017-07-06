package com.cqust.chat.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cqust.chat.dao.StudentDao;
import com.cqust.chat.entity.Student;
import com.cqust.chat.service.StudentService;

@Component
public class StudentServiceImpl implements StudentService{

	@Autowired
	private StudentDao studentDao;
	/**
	 * 登录逻辑
	 */
	public Student doLogin(Student s) {
		return studentDao.findByIdAndPwd(s);
	}
	public List<Student> findAll() {
		List<Student> lists = studentDao.findAll();
		if(lists.size() > 0)
			return lists;
		return null;
	}
	public Long addStu(Student s) {
		return studentDao.addStu(s);
	}

}
