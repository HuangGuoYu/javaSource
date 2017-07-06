package com.cqust.chat.dao;

import java.util.List;

import com.cqust.chat.entity.Student;

public interface StudentDao {
	Student findByIdAndPwd(Student s);

	List<Student> findAll();

	Long addStu(Student s);
}
