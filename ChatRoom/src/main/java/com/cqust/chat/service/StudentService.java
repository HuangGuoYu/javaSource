package com.cqust.chat.service;

import java.util.List;

import com.cqust.chat.entity.Student;

public interface StudentService {
	public Student doLogin(Student s);
	public List<Student> findAll();
	public Long addStu(Student s);
}
